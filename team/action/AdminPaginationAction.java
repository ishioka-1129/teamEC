package com.internousdev.kagiya.action;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.kagiya.dao.ProductInfoDAO;
import com.internousdev.kagiya.dto.PaginationDTO;
import com.internousdev.kagiya.dto.ProductInfoDTO;
import com.internousdev.kagiya.util.Pagination;
import com.opensymphony.xwork2.ActionSupport;

public class AdminPaginationAction extends ActionSupport implements SessionAware{

	private int pageNo = 1;
	private int type;
	public List<ProductInfoDTO> productInfoDTOList;
	public Map<String, Object> session;

	public String execute() throws SQLException{
		String result = ERROR;

		//ステータスがnullならエラーを返す
		if(!session.containsKey("status")) {
			return ERROR;
		}

		if(type == 1){
			result = "delete";
		}

		//全ての商品データを取得
		ProductInfoDAO productInfoDAO = new ProductInfoDAO();

		productInfoDTOList = productInfoDAO.selectAll();
		session.put("productInfoDTOList", productInfoDTOList);

		//商品データが取得できた場合
		if(!(productInfoDTOList == null)) {

			//現在のページが１ページ目のときのページデータを取得
			Pagination pagination = new Pagination();
			PaginationDTO paginationDTO = new PaginationDTO();
			int totalPageSize = pagination.getTotalPageSize(productInfoDTOList, 9);
			if(pageNo == 1) {
				paginationDTO = pagination.initialize(productInfoDTOList, 9);

				//sessionにページデータを挿入する
				session.put("totalPageSize", paginationDTO.getTotalPageSize());
				session.put("currentPageNo", paginationDTO.getCurrentPageNo());
				session.put("totalRecordSize", paginationDTO.getTotalPageSize());
				session.put("startRecordNo", paginationDTO.getStartRecordNo());
				session.put("endRecordNo", paginationDTO.getEndRecordNo());
				session.put("pageNumberList", paginationDTO.getPageNumberList());
				session.put("productInfoDTOList", paginationDTO.getCurrentProductInfoPage());
				session.put("hasNextPage", paginationDTO.hasNextPage());
				session.put("hasPreviousPage", paginationDTO.hasPreviousPage());
				session.put("nextPageNo", paginationDTO.getNextPageNo());
				session.put("previousPageNo", paginationDTO.getPreviousPageNo());

				//現在のページが２ページ目以降のときのページデータを取得
			} else if (pageNo > 1 && pageNo <= totalPageSize) {
				paginationDTO = pagination.getPage(productInfoDTOList, 9, pageNo);

				session.put("totalPageSize", paginationDTO.getTotalPageSize());
				session.put("currentPageNo", paginationDTO.getCurrentPageNo());
				session.put("totalRecordSize", paginationDTO.getTotalPageSize());
				session.put("startRecordNo", paginationDTO.getStartRecordNo());
				session.put("endRecordNo", paginationDTO.getEndRecordNo());
				session.put("pageNumberList", paginationDTO.getPageNumberList());
				session.put("productInfoDTOList", paginationDTO.getCurrentProductInfoPage());
				session.put("hasNextPage", paginationDTO.hasNextPage());
				session.put("hasPreviousPage", paginationDTO.hasPreviousPage());
				session.put("nextPageNo", paginationDTO.getNextPageNo());
				session.put("previousPageNo", paginationDTO.getPreviousPageNo());
			} else {
				return ERROR;
			}

			//商品データがない場合nullを挿入する
		}else{
			session.put("productInfoDTOList", null);
		}
		return result;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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
