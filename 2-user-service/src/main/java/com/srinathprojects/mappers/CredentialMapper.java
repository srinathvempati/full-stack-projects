package com.srinathprojects.mappers;

import org.mapstruct.Mapper;

import com.srinathprojects.dto.CredentialDto;
import com.srinathprojects.models.Credential;

@Mapper(componentModel = "spring")
public interface CredentialMapper {

	CredentialDto toDto(Credential credential);
	
}
