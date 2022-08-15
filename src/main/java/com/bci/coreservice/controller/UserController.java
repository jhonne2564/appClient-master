package com.bci.coreservice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bci.coreservice.dto.CreateUserDto;
import com.bci.coreservice.dto.UserResponseDto;
import com.bci.coreservice.model.User;
import com.bci.coreservice.service.JwtUtilService;
import com.bci.coreservice.service.UserService;

@RestController
public class UserController {
	@Value("${bci.pattern}")
	private String pattern = "";

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	UserDetailsService usuarioDetailsService;

	@Autowired
	private JwtUtilService jwtUtilService;

	@Autowired
	private UserService userService;
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@PostMapping(value = "/public/create")
	public ResponseEntity<UserResponseDto> create(@RequestBody @NotNull  @Validated CreateUserDto user){
		logger.debug("Begin create");
		ResponseEntity<UserResponseDto> response=null;		
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(user.getEmail(),
			            user.getPassword()));
			
			final UserDetails userDetails = usuarioDetailsService.loadUserByUsername(
					  user.getEmail());
	
			final String jwt = jwtUtilService.generateToken(userDetails);
			userService.create(user);
			UserResponseDto userresponse = new UserResponseDto();
			userresponse.setMensaje("ok");
			userresponse.setToken(jwt);
			response=new ResponseEntity<>(userresponse,HttpStatus.OK);
			logger.debug("End create");
		
		}catch(Exception e) {
			logger.error("Error creando usuario:{}",e.getMessage(),e);
			UserResponseDto userresponse = new UserResponseDto();
			userresponse.setMensaje(e.getMessage());
			response=new ResponseEntity<>(userresponse,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	@GetMapping("/public/all")
	public ResponseEntity<List<User>> allUsers() {

		logger.debug("Begin allUsers:" + pattern);
		List<User> list = userService.geAllClients();
		ResponseEntity<List<User>> response = new ResponseEntity<List<User>>(list, HttpStatus.OK);
		logger.debug("End allUsers");
		return response;

	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}
}
