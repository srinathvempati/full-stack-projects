package com.srinathprojects.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srinathprojects.dto.CredentialDto;
import com.srinathprojects.mappers.CredentialMapper;
import com.srinathprojects.repository.CredentialRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CredentialService {

	@Autowired
	private CredentialRepository credentialRepository;

	@Autowired
	private CredentialMapper credentialMapper;

	public CredentialDto findByUsername(String username) {

		return credentialRepository.findByuserName(username).map(credentialMapper::toDto)
				.orElseThrow(() -> new RuntimeException("user name not found"));

	}

}
