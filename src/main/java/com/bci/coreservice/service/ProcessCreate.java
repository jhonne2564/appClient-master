package com.bci.coreservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bci.coreservice.dto.CreateUserDto;
import com.bci.coreservice.model.User;
import com.bci.coreservice.repository.UserRepository;

@Component
public class ProcessCreate {

	@Autowired
    private UserRepository userRepository;
	
	public void create(CreateUserDto userDto) {
		User user= new User();
		user.setEmail(userDto.getEmail());
		user.setId(userDto.getId());
		user.setName(userDto.getName());
		user.setPassword(userDto.getPassword());
		userRepository.save(user);
	}
}
