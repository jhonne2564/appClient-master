package com.bci.coreservice.dto;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class CreateUserDto {

	private String id;
	@NotBlank
	private String name;
	
	@Email(regexp = "(.+)@[a-zA-Z0-9][a-zA-Z0-9-]{1,61}[a-zA-Z0-9]\\.[a-zA-Z]{2,6}$")
	private String email;
	
	private String password;
	
	private List<PhoneDto> phones;
}
