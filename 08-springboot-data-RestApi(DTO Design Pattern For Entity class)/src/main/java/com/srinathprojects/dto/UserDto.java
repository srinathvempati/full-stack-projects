package com.srinathprojects.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
	
	private Integer id;
	private String fName;
	private String lName;
	private String emailId;
	private String phoneNumber;

}
