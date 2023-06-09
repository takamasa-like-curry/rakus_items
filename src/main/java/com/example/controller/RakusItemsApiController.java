package com.example.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Brand;
import com.example.domain.Category;
import com.example.service.RakusItemsApiService;

@RestController
@RequestMapping("/api")
public class RakusItemsApiController {

	@Autowired
	private RakusItemsApiService service;

	@GetMapping("/check-category-name")
	public ResponseEntity<Boolean> checkChildCategory(String categoryName, Integer categoryId) {
		Boolean result = service.checkCategoryNameDuplication(categoryName, categoryId);
		return ResponseEntity.ok(result);
	}

	@GetMapping("/get-subordinate-branch-category-list")
	public Map<String, List<Category>> getSubordinateBranchCategoryList(Integer categoryId) {

		Map<String, List<Category>> map = new HashMap<>();
		if (categoryId != null) {
			map.put("categoryList", service.getSubordinateBranchCategoryList(categoryId));
		}
		return map;

	}
	@GetMapping("/get-subordinate-category-list")
	public Map<String, List<Category>> getSubordinateCategoryList(Integer categoryId) {
		
		Map<String, List<Category>> map = new HashMap<>();
		if (categoryId != null) {
			map.put("categoryList", service.getSubordinateCategoryList(categoryId));
		}
		return map;
		
	}

	@GetMapping("pick-up-brand-list")
	public Map<String, List<Brand>> pickUpBrandList(String brandName) {

		Map<String, List<Brand>> map = new HashMap<>();
		if (brandName != null) {
			map.put("brandList", service.pickUpBrandListByBrandName(brandName));
		}
		return map;

	}

	@GetMapping("/input-brand-name-is-exists")
	public ResponseEntity<Boolean> inputBrandNameIsExists(String brandName) {
		Boolean result = service.brandNameExistsInDb(brandName);
		return ResponseEntity.ok(result);
	}

}
