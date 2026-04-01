package com.srinathprojects.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserDto {

	private Integer userId;
	private String firstName;
	private String lastName;
	private String emailAddress;
	private String contact;
	

}
