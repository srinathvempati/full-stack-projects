package com.srinathprojects.models;

public enum RoleBasedAuthority {

	ROLE_USER("USER"),
	ROLE_ADMIN("ADMIN");

	private final String role;

	public String getRole() {
		return role;
	}

	RoleBasedAuthority(String role) {
		this.role = role;
	}
	

}
