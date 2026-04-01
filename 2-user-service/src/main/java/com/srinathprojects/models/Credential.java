package com.srinathprojects.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Credential {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer credentialId;
	
	@Column(unique = true)
	private String userName;
	private String password;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "role")
	private RoleBasedAuthority roleBasedAuthority;
	
	@JsonIgnore  //Tells Jackson not to include this field in Json output
	@OneToOne (fetch= FetchType.LAZY)
	@JoinColumn(name = "userId", unique = true)
	private User user;

	public Integer getCredentialId() {
		return credentialId;
	}

	public void setCredentialId(Integer credentialId) {
		this.credentialId = credentialId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public RoleBasedAuthority getRoleBasedAuthority() {
		return roleBasedAuthority;
	}

	public void setRoleBasedAuthority(RoleBasedAuthority roleBasedAuthority) {
		this.roleBasedAuthority = roleBasedAuthority;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
