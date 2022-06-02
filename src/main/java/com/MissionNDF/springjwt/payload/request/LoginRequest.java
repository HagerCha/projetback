package com.MissionNDF.springjwt.payload.request;

import javax.validation.constraints.NotBlank;

public class LoginRequest {

  private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
