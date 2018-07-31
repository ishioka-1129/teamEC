// 担当:石岡

package com.internousdev.kagiya.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class AdminAction extends ActionSupport implements SessionAware{

	private Map<String, Object> session;
	//管理者ページに戻る
	public String execute(){

	//ステータスがnullならエラーを返す
	if(!session.containsKey("status")) {
		return ERROR;
	}

	return SUCCESS;
	}

	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
