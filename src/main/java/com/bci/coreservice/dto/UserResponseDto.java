package com.bci.coreservice.dto;

import lombok.Data;

@Data
public class UserResponseDto {
	private String id;
	private String mensaje;
	private String created;
	private String modified;
	private String last_login;
	private String token;
	private boolean isactive;
	
	public String getMensaje() {
		return mensaje;
	}


	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
		
}
