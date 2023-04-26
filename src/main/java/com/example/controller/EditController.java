package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.common.CategoryLevel;
import com.example.common.NullValue;
import com.example.domain.Category;
import com.example.domain.Item;
import com.example.form.ItemForm;
import com.example.service.EditService;

@Controller
@RequestMapping("/edit")
public class EditController {

	@Autowired
	private EditService service;

	@GetMapping("")
	public String showEditPage(Model model, ItemForm form, Integer itemId, BindingResult br) {

		model.addAttribute("itemId", itemId);
		Item item = service.load(itemId);

		if (!br.hasErrors()) {
			form.setInputName(item.getName());
			form.setPrice(String.valueOf(item.getPrice()));
			form.setParentCategoryId(itemId);
			form.setChildCategoryId(itemId);
			form.setGrandChildCategoryId(itemId);
			if(item.getBrand() != null) {
				form.setBrand(item.getBrand().getName());
			}
			form.setCondition(item.getCondition());
			form.setDescription(item.getDescription());

		}

		// 親カテゴリの処理
		List<Category> parentCategoryList = service.pickUpCategoryListByAncestorIdAndLevel(
				NullValue.CATEGORY_ID.getValue(), CategoryLevel.PARENT.getLevel());
		model.addAttribute("parentCategoryList", parentCategoryList);

		if (form.getParentCategoryId() != null) {
			List<Category> childCategoryList = service.pickUpCategoryListByAncestorIdAndLevel(form.getParentCategoryId(),
					CategoryLevel.CHILD.getLevel());
			model.addAttribute("childCategoryList", childCategoryList);
		}
		if (form.getChildCategoryId() != null) {
			List<Category> grandChildCategoryList = service.pickUpCategoryListByAncestorIdAndLevel(form.getChildCategoryId(),
					CategoryLevel.GRAND_CHILD.getLevel());
			model.addAttribute("grandChildCategoryList", grandChildCategoryList);
		}

		return "edit";
	}

	@PostMapping("/update")
	public String insert(Model model, @Validated ItemForm form, BindingResult br, Integer itemId) {

		// カテゴリの入力値チェック
		if (form.getParentCategoryId() == NullValue.CATEGORY_ID.getValue()) {
			br.rejectValue("parentId", null, "選択必須項目です");
		} else if (form.getChildCategoryId() == NullValue.CATEGORY_ID.getValue()) {
			br.rejectValue("parentId", null, "選択必須項目です(子カテゴリ、孫カテゴリも選択必須)");
		} else if (form.getGrandChildCategoryId() == NullValue.CATEGORY_ID.getValue()) {
			br.rejectValue("parentId", null, "選択必須項目です(孫カテゴリも選択必須)");
		}

		// 金額のチェック
		if (!("".equals(form.getPrice()))) {
			try {
				Double.parseDouble(form.getPrice());
			} catch (Exception e) {
				br.rejectValue("price", null, "価格を数値で入力してください(単位は$です)");
			}
		}

		// エラーがあれば入力画面に遷移
		if (br.hasErrors()) {
			return showEditPage(model, form, itemId, br);
		}

		service.upDateItem(form, itemId); // updateに書き換え

		return "redirect:/";
	}
}
