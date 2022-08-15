package com.bci.coreservice.repository;

import org.springframework.data.repository.CrudRepository;

import com.bci.coreservice.model.User;

public interface UserRepository extends CrudRepository<User, String>{

}
