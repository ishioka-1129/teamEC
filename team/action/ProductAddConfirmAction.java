//担当:石岡

package com.internousdev.kagiya.action;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.kagiya.dao.ProductInfoDAO;
import com.internousdev.kagiya.dto.ProductInfoDTO;
import com.internousdev.kagiya.util.CommonUtility;
import com.internousdev.kagiya.util.InputChecker;
import com.opensymphony.xwork2.ActionSupport;

public class ProductAddConfirmAction extends ActionSupport implements SessionAware {

	private String productId;
	private String productName;
	private String productNameKana;
	private String productDescription;
	private String categoryId;
	private String price;
	private String imageFilePath;
	private String releaseCompany;

	private File userImage;
	private String userImageContentType;
	private String userImageFileName;
	private String fileExtension;

	public Map<String, Object> session;

	private String productIdCheckError;
	private String productNameCheckError;
	private String productIdError;
	private String productNameError;
	private String productNameKanaError;
	private String productDescriptionError;
	private String priceError;
	private String imageFilePathError;
	private String releaseCompanyError;
	private String productNameKanaCheckError;

	public String execute() throws SQLException {
		String result = ERROR;

		//ステータスが無ければLOGINを返す
		if(!session.containsKey("status")) {
			return LOGIN;
		}

		//入力された内容が正常かどうか判定する

		productIdError = InputChecker.productIdChk(productId);
 		productNameError = InputChecker.productNameChk(productName);
 		productNameKanaError = InputChecker.productNameKanaChk(productNameKana);
 		productDescriptionError = InputChecker.productDescriptionChk(productDescription);
 		priceError = InputChecker.priceChk(price);
 		releaseCompanyError = InputChecker.releaseCompanyChk(releaseCompany);

		//重複IDがないか確認する
		ArrayList<ProductInfoDTO> productInfo = new ArrayList<ProductInfoDTO>();
		ProductInfoDAO productInfoDAO = new ProductInfoDAO();

		productInfo = productInfoDAO.productIdCheck();

		for (int i = 0; i < productInfo.size(); i++) {
			String productIdCheck = String.valueOf(productInfo.get(i).getProductId());

			if (productIdCheck.equals(productId)) {
				productIdCheckError = "既に同じIDが存在します";
			}
		}

		for(int i = 0; i < productInfo.size(); i++) {
			String productIdCheck = String.valueOf(productInfo.get(i).getProductName());

			if (productIdCheck.equals(productName)) {
				productNameCheckError = "既に同じ名前の商品が存在します";
			}
		}

		for(int i = 0; i < productInfo.size(); i++) {
			String productIdCheck = String.valueOf(productInfo.get(i).getProductNameKana());

			if (productIdCheck.equals(productNameKana)) {
				setProductNameKanaCheckError("既に同じ名前（かな）の商品が存在します");
			}
		}

		//画像ファイルが選択されているか確認する
		if(userImage != null){
			imageFilePathError = null;
		}else{
			imageFilePathError = "画像ファイルを選んでください";
		}

		//特定の拡張子以外の画像ファイルを選択できないようにする
		//最後の.の位置を取得します
		if (userImageFileName != null) {
				int point = userImageFileName.lastIndexOf(".");
				System.out.println(point);
				//.が存在する場合.以降の文字を返します
				if(point != -1){
					fileExtension = userImageFileName.substring(point + 1);
				}

				//jpegかjpgではないときエラー文を挿入
				if(!fileExtension.equals("jpeg") && !fileExtension.equals("jpg")){
					imageFilePathError = "使用できるファイルは'.jpeg''.jpg'です";
				}
		}

		//各項目に異常があった場合エラーを返す
		if(productIdError != null || productIdCheckError != null || productNameError != null || productNameCheckError != null || productNameKanaError != null || productDescriptionError != null || priceError != null || imageFilePathError != null || releaseCompanyError != null || productNameKanaCheckError != null){
			return ERROR;
		}

		if(categoryId != null) {
			if(!(categoryId.equals("2") || categoryId.equals("3") || categoryId.equals("4"))) {
				return ERROR;
			}
		} else {
			return ERROR;
		}

		//入力された値を保存する
		session.put("productId", productId);
		session.put("productName", productName);
		session.put("productNameKana", productNameKana);
		session.put("productDescription", productDescription);
		session.put("categoryId", categoryId);
		session.put("price", price);
		session.put("releaseCompany", releaseCompany);


		//選択した画像ファイル名をコンソールに表示する
		System.out.println(userImageFileName);

		//選択した画像ファイルをサーバーに保存する
		String filePath = ServletActionContext.getServletContext().getRealPath("/").concat("images/");

			System.out.println("Image Location:"+ filePath);

			CommonUtility commonUtility = new CommonUtility();
			userImageFileName = commonUtility.getRamdomValue() + userImageFileName;

			//サーバー上に保存した画像をimageフォルダにコピーする
			File fileToCreate = new File(filePath,userImageFileName);
			try{
				FileUtils.copyFile(userImage, fileToCreate);
				session.put("imageFileName", userImageFileName);
				session.put("imageFilePath", "./images/"+userImageFileName);
				session.put("image_flg", userImageFileName);
			}catch(IOException e){
				e.printStackTrace();
			}

		session.put("checked", 1);
		result = SUCCESS;
		return result;
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

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getImageFilePath() {
		return imageFilePath;
	}

	public void setImageFilePath(String imageFilePath) {
		this.imageFilePath = imageFilePath;
	}

	public String getReleaseCompany() {
		return releaseCompany;
	}

	public void setReleaseCompany(String releaseCompany) {
		this.releaseCompany = releaseCompany;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public String getProductIdCheckError() {
		return productIdCheckError;
	}

	public void setProductIdCheckError(String productIdCheckError) {
		this.productIdCheckError = productIdCheckError;
	}

	public String getProductIdError() {
		return productIdError;
	}

	public void setProductIdError(String productIdError) {
		this.productIdError = productIdError;
	}

	public String getProductNameError() {
		return productNameError;
	}

	public void setProductNameError(String productNameError) {
		this.productNameError = productNameError;
	}

	public String getProductNameKanaError() {
		return productNameKanaError;
	}

	public void setProductNameKanaError(String productNameKanaError) {
		this.productNameKanaError = productNameKanaError;
	}

	public String getProductDescriptionError() {
		return productDescriptionError;
	}

	public void setProductDescriptionError(String productDescriptionError) {
		this.productDescriptionError = productDescriptionError;
	}

	public String getPriceError() {
		return priceError;
	}

	public void setPriceError(String priceError) {
		this.priceError = priceError;
	}

	public String getImageFilePathError() {
		return imageFilePathError;
	}

	public void setImageFilePathError(String imageFilePathError) {
		this.imageFilePathError = imageFilePathError;
	}

	public String getReleaseCompanyError() {
		return releaseCompanyError;
	}

	public void setReleaseCompanyError(String releaseCompanyError) {
		this.releaseCompanyError = releaseCompanyError;
	}

	public File getUserImage() {
		return userImage;
	}

	public void setUserImage(File userImage) {
		this.userImage = userImage;
	}

	public String getUserImageContentType() {
		return userImageContentType;
	}

	public void setUserImageContentType(String userImageContentType) {
		this.userImageContentType = userImageContentType;
	}

	public String getUserImageFileName() {
		return userImageFileName;
	}

	public void setUserImageFileName(String userImageFileName) {
		this.userImageFileName = userImageFileName;
	}

	public String getProductNameCheckError() {
		return productNameCheckError;
	}

	public void setProductNameCheckError(String productNameCheckError) {
		this.productNameCheckError = productNameCheckError;
	}

	public String getProductNameKanaCheckError() {
		return productNameKanaCheckError;
	}

	public void setProductNameKanaCheckError(String productNameKanaCheckError) {
		this.productNameKanaCheckError = productNameKanaCheckError;
	}




}
