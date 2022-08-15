package com.bci.coreservice.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.bci.coreservice.dto.CreateUserDto;
import com.bci.coreservice.dto.UserResponseDto;
import com.bci.coreservice.exception.EmailException;
import com.bci.coreservice.model.User;

@Service
public class UserService {

	private ProcessCreate processCreate;
	private ProcessGetAll processGetAll;
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	public UserService(ProcessCreate processCreate,ProcessGetAll processGetAll) {
		this.processCreate=processCreate;
		this.processGetAll=processGetAll;
	}
	
	public UserResponseDto create(CreateUserDto createUser) throws EmailException{
		 List<User> users=geAllClients().stream().
				 filter(user -> user.getEmail().equalsIgnoreCase(createUser.getEmail())).toList();
		 
		 if(users==null||users.isEmpty() ){
			 return processCreate.create(createUser);
		 }else {
			 logger.error("Email ya existe");
			 throw new EmailException("El correo ya esta registrado") ;			 
		 }		
	}

	public List<User> geAllClients() {			
		return processGetAll.getAll();		
	}
}
