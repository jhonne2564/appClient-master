package com.bci.coreservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="client")
public class ClientDto {

	@Id
	@SequenceGenerator(name="CORE_CLIENT_ID_GENERATOR", sequenceName="CLIENT_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CORE_CLIENT_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer id;
	
	@Column(name="email", length=300)
	private String email;
	
	String name;	
	
	private Boolean active;
	
	@Column(name="document")
	private String nroDocument;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getNroDocument() {
		return nroDocument;
	}

	public void setNroDocument(String nroDocument) {
		this.nroDocument = nroDocument;
	}
	
	
}
