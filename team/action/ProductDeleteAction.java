// 担当:石岡

package com.internousdev.kagiya.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.kagiya.dao.ProductInfoDAO;
import com.internousdev.kagiya.dto.PaginationDTO;
import com.internousdev.kagiya.dto.ProductInfoDTO;
import com.internousdev.kagiya.util.Pagination;
import com.opensymphony.xwork2.ActionSupport;

public class ProductDeleteAction extends ActionSupport implements SessionAware {
	private int count;
	private int pageNo = 1;
	public List<ProductInfoDTO> productInfoDTOList;
	public Map<String, Object> session;

	//商品一覧を表示するためにページデータを取得
	public String execute() {

		String result = SUCCESS;

		//ステータスがなければエラーを返す
		if(!session.containsKey("status")) {
			return ERROR;
		}

		//商品情報を取得
		ProductInfoDAO productInfoDAO = new ProductInfoDAO();

		//商品情報をsessionに格納
		productInfoDTOList = productInfoDAO.selectAll();
		session.put("productInfoDTOList", productInfoDTOList);

		//商品情報を取得できている場合、商品を１ページに９個表示させる
		if(!(productInfoDTOList == null)) {
			Pagination pagination = new Pagination();
			PaginationDTO paginationDTO = new PaginationDTO();

			paginationDTO = pagination.getPage(productInfoDTOList, 9, pageNo);

			//ページ情報を格納
			session.put("productInfoDTOList", paginationDTO.getCurrentProductInfoPage());
			session.put("totalPageSize", paginationDTO.getTotalPageSize());
			session.put("currentPageNo", paginationDTO.getCurrentPageNo());
			session.put("totalRecordSize", paginationDTO.getTotalRecordSize());
			session.put("startRecordNo", paginationDTO.getStartRecordNo());
			session.put("endRecordNo", paginationDTO.getEndRecordNo());
			session.put("hasPreviousPage", paginationDTO.hasPreviousPage());
			session.put("previousPageNo", paginationDTO.getPreviousPageNo());
			session.put("hasNextPage", paginationDTO.hasNextPage());
			session.put("nextPageNo", paginationDTO.getNextPageNo());
		}else{
			session.put("productInfoDTOList", null);
		}

		System.out.println(pageNo);
		session.put("pageNo", pageNo);

		return result;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public List<ProductInfoDTO> getProductInfoDTOList() {
		return productInfoDTOList;
	}

	public void setProductInfoDTOList(List<ProductInfoDTO> productInfoDTOList) {
		this.productInfoDTOList = productInfoDTOList;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}