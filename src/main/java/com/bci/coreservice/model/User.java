package com.bci.coreservice.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class User {
	@Id
	private String id;
	private String name;		
	private String email;
	private String password;
	private String token;
	private String created;
	private String modified;
	
	@OneToMany(mappedBy = "user")	
	private List<Phone> phones;
		   
}
