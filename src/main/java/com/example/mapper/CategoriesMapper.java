package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.domain.Category;

@Mapper
public interface CategoriesMapper {

	List<Category> findByAncestorIdAndLevel(@Param("ancestorId") int ancestorId, @Param("level") int level);

	List<Category> findByDescendantId(int descendantId);

	/**
	 * カテゴリ名の重複チェックを行う.
	 * 
	 * @param name  重複をチェックするカテゴリ名(ブラウザにて入力された値)
	 * @param level チェックを行う階層
	 * @return 重複があれば[false]、重複がなければ[true]
	 * 
	 */
	Boolean checkCategoryNameDuplication(@Param("name") String name, @Param("ancestorId") Integer ancestorId,
			@Param("level") int level);

	boolean existsDescendantCategory(int id);

//	List<Category>

	/**
	 * 該当カテゴリを親に持つ一つ下の階層のカテゴリリストを取得.
	 * 
	 * @param id カテゴリID
	 * @return 該当カテゴリリスト
	 */
	List<Category> pickUpSubordinateCategoryList(int id);

	int pickUpLevelById(int id);

	int pickUpLatestCategoryId();

	void insert(@Param("category") Category category);
}
