// 担当: 石岡

package com.internousdev.kagiya.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.kagiya.dao.MCategoryDAO;
import com.internousdev.kagiya.dto.MCategoryDTO;
import com.opensymphony.xwork2.ActionSupport;

public class ProductAddAction extends ActionSupport implements SessionAware {

	private String productId;
	private String productName;
	private String productNameKana;
	private String productDescription;
	private int categoryId;
	private String price;
	private String releaseCompany;


	private Map<String, Object> session;

	private List<MCategoryDTO> adminCategoryDTOList;

	//商品追加画面に遷移する
	public String execute() {

		//ステータスがなければエラーを返す
		if(!session.containsKey("status")) {
			return ERROR;
		}

		//商品追加画面で商品カテゴリーを選択できるようにカテゴリーIDを取得
		MCategoryDAO mCategoryDAO = new MCategoryDAO();
		adminCategoryDTOList = mCategoryDAO.getAdminCategoryList();
		session.put("adminCategoryDTOList", adminCategoryDTOList);
		return SUCCESS;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public List<MCategoryDTO> getAdminCategoryDTOList() {
		return adminCategoryDTOList;
	}

	public void setAdminCategoryDTOList(List<MCategoryDTO> adminCategoryDTOList) {
		this.adminCategoryDTOList = adminCategoryDTOList;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductNameKana() {
		return productNameKana;
	}

	public void setProductNameKana(String productNameKana) {
		this.productNameKana = productNameKana;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getReleaseCompany() {
		return releaseCompany;
	}

	public void setReleaseCompany(String releaseCompany) {
		this.releaseCompany = releaseCompany;
	}



}
