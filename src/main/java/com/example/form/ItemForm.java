package com.example.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ItemForm {

	/** 商品ID */
	private Integer id;
	/** 商品名 */
	@NotBlank(message = "入力必須項目です")
	@Size(max = 255, message = "最大255文字までです。")
	private String inputName;
	/** 価格 */
	@NotBlank(message = "入力必須項目です")
	private String price;
	/** 親カテゴリ */
	@NotNull(message = "選択必須項目です")
	private Integer parentCategoryId;
	/** 子カテゴリ */
	@NotNull(message = "選択必須項目です")
	private Integer childCategoryId;
	/** 孫カテゴリ */
	@NotNull(message = "選択必須項目です")
	private Integer grandChildCategoryId;
	/** ブランドID */
	private Integer brandId;
	/** ブランド名 */
	private String brandName;
	/** 商品状態 */
	@NotNull(message = "選択必須項目です")
	private Integer condition;
	/** 商品説明 */
	@NotBlank(message = "入力必須項目です")
	private String description;

	public void setBrandName(String brandName) {
		if ("".equals(brandName)) {
			this.brandName = null;
		} else {
			this.brandName = brandName;
		}

	}

}
