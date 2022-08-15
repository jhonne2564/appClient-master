package com.bci.coreservice.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bci.coreservice.dto.CreateUserDto;
import com.bci.coreservice.dto.UserResponseDto;
import com.bci.coreservice.model.User;
import com.bci.coreservice.repository.UserRepository;
import com.bci.coreservice.util.IdGenerator;

@Component
public class ProcessCreate {

	@Autowired
    private UserRepository userRepository;
	
	private  SimpleDateFormat date_format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	public UserResponseDto create(CreateUserDto userDto) {
		String correlationId = IdGenerator.getUuId();
		User user= new User();
		user.setEmail(userDto.getEmail());
		user.setId(correlationId);
		user.setName(userDto.getName());
		user.setPassword(userDto.getPassword());
		user.setCreated(date_format.format(new Date()));
		user.setModified(date_format.format(new Date()));
		user.setToken(userDto.getToken());
		user.setPhones(userDto.getPhones());
		userRepository.save(user);
		
		UserResponseDto userresponse = new UserResponseDto();
		userresponse.setMensaje("ok");
		userresponse.setToken(userDto.getToken());
		userresponse.setId(correlationId);
		userresponse.setCreated(user.getCreated());
		userresponse.setModified(user.getCreated());
		return userresponse;
	}
}
