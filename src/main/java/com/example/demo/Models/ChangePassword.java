package com.example.demo.Models;

public class ChangePassword {
	private Integer id;
	private String passwordold;
	private String password;

	public ChangePassword(Integer id, String passwordold, String password) {
		this.id = id;
		this.passwordold = passwordold;
		this.password = password;
	}

	public String getPasswordold() {
		return passwordold;
	}

	public void setPasswordold(String passwordold) {
		this.passwordold = passwordold;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getId() {
		return id;
	}

}
