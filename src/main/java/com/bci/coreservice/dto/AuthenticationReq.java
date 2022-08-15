package com.bci.coreservice.dto;
import java.io.Serializable;

import lombok.Data;

@Data
public class AuthenticationReq implements Serializable {

  private static final long serialVersionUID = 1L;

  private String usuario;

  private String clave;

  public AuthenticationReq(String usuario, String clave) {
    this.usuario = usuario;
    this.clave = clave;
  }
}
