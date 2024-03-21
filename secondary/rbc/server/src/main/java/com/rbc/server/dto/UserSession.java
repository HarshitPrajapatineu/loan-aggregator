package com.rbc.server.dto;


public class UserSession {

	private String username;
	private String email;
	private String role;
	private String currentPage;
	private String errMsg;

	public UserSession() {
		// TODO Auto-generated constructor stub
	}
	
	public UserSession(String username, String email, String role) {
		super();
		this.username = username;
		this.email = email;
		this.role = role;
	}

	public UserSession(String username, String email, String role, String currentPage) {
		super();
		this.username = username;
		this.email = email;
		this.role = role;
		this.currentPage = currentPage;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public String getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	

}
