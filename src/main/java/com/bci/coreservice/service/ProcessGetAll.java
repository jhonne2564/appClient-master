package com.bci.coreservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bci.coreservice.model.User;
import com.bci.coreservice.repository.UserRepository;

@Component
public class ProcessGetAll {

	@Autowired
    private UserRepository userRepository;

	public List<User> getAll() {		
		List<User> list= new ArrayList<User>();
		userRepository.findAll().forEach(list::add);
		return list;
	}
	
	
}
