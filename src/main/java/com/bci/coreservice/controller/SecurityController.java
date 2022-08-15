package com.bci.coreservice.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bci.coreservice.dto.AuthenticationReq;
import com.bci.coreservice.dto.TokenInfo;
import com.bci.coreservice.service.JwtUtilService;

@RestController
public class SecurityController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtilService jwtUtilService;
	
	@Autowired
	  UserDetailsService usuarioDetailsService;
	private static final Logger logger = LoggerFactory.getLogger(SecurityController.class);
	
	 @PostMapping("/public/authenticate")
	  public ResponseEntity<TokenInfo> authenticate(@RequestBody AuthenticationReq authenticationReq) {
	    logger.info("Autenticando al usuario {}", authenticationReq.getUsuario());

	    authenticationManager.authenticate(
	        new UsernamePasswordAuthenticationToken(authenticationReq.getUsuario(),
	            authenticationReq.getClave()));

	    final UserDetails userDetails = usuarioDetailsService.loadUserByUsername(
	        authenticationReq.getUsuario());

	    final String jwt = jwtUtilService.generateToken(userDetails);

	    TokenInfo tokenInfo = new TokenInfo(jwt);

	    return ResponseEntity.ok(tokenInfo);
	  }
}
