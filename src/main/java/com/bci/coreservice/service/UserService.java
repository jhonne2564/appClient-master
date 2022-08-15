package com.bci.coreservice.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.bci.coreservice.dto.CreateUserDto;
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
	
	public void create(CreateUserDto createUser) {
		 List<User> users=geAllClients().stream().
				 filter(user -> user.getEmail().equalsIgnoreCase(user.getEmail())).toList();
		 
		 if(users==null||users.isEmpty() ){
			 processCreate.create(createUser);
		 }else {
			 logger.error("Email ya existe");
		 }
		
	}

	public List<User> geAllClients() {
		return processGetAll.getAll();		
	}
}
