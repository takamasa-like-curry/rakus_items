package com.example.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * カテゴリ追加に関する情報を取得するフォーム.
 * 
 * @author sugaharatakamasa
 *
 */
@Data
public class AddCategoryForm {

	/** 親カテゴリID */
	@NotNull(message = "選択必須項目です")
	private Integer parentCategoryId;
	/** 子カテゴリID */
	@NotNull(message = "選択必須項目です")
	private Integer childCategoryId;
	/** カテゴリ名 */
	@NotBlank(message = "入力必須項目です")
	private String categoryName;
	/** カテゴリの概要 */
	@NotBlank(message = "入力必須項目です")
//	@Size(max=255,message="255文字以内で入力してください")
	private String description;

	public AddCategoryForm() {

	}

}
