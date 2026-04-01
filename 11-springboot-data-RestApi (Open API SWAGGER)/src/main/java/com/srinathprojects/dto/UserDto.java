package com.srinathprojects.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
public class UserDto {
	
	
	private Integer id;
	
	@Size(min = 3, max = 20, message = "Name must be between 3 to 20 characters")
	private String fName;
	
	@NotNull(message = "Last Name must not be null")
	private String lName;
	
	@Email
	private String emailId;
	
	@Size(min = 10, max = 10, message = "Phone number must be 10 numbers" )
	private String phoneNumber;

}
