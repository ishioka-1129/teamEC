//担当:石岡

package com.internousdev.kagiya.action;

import java.sql.SQLException;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.kagiya.dao.CartInfoDAO;
import com.internousdev.kagiya.dao.ProductInfoDAO;
import com.opensymphony.xwork2.ActionSupport;

public class ProductDeleteCompleteAction extends ActionSupport implements SessionAware {
	public Map<String, Object> session;

	public String execute() throws SQLException {
		String result = ERROR;

		//ステータスが無い場合エラーを返す
		if(!session.containsKey("status")) {
			return ERROR;
		}

		//もしsessionにproductNameがない場合エラーを返す
		if (!session.containsKey("productId")) {
			return ERROR;
		}

		//選択した商品を削除
		ProductInfoDAO productInfoDAO = new ProductInfoDAO();
		int count1 = productInfoDAO.productDeleteInfo(session.get("productId").hashCode());

		//選択した商品をカートから削除
		CartInfoDAO cartInfoDao = new CartInfoDAO();
		cartInfoDao.deleteProduct(session.get("productId").hashCode());

		//一件でも削除したらSUCCESSを返す
		if(count1 > 0){
			result = SUCCESS;
			session.remove("productInfoDTOList");
			return result;

		}

		return result;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
