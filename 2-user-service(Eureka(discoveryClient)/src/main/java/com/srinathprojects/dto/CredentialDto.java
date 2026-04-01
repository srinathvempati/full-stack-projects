package com.srinathprojects.dto;

import com.srinathprojects.models.RoleBasedAuthority;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CredentialDto {

	private String userName;
	private String password;
	private RoleBasedAuthority roleBasedAuthority;
}
