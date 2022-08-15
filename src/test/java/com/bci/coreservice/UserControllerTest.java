package com.bci.coreservice;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.bci.coreservice.controller.UserController;
import com.bci.coreservice.dto.CreateUserDto;
import com.bci.coreservice.dto.UserResponseDto;
import com.bci.coreservice.model.User;

@RunWith(MockitoJUnitRunner.Silent.class)
public class UserControllerTest {

	@InjectMocks
	private UserController userController;
	
	
	@Test
	public void usersTest() throws Exception{
		
		CreateUserDto user= new CreateUserDto();
		user.setEmail("admin@bci.com");
		user.setPassword("secreto");
		user.setName("Juan Perez");
		ResponseEntity<UserResponseDto> response=userController.create(user);
		
		assertEquals("El usuario no se creo satisfactoriamente","ok",response.getBody().getMensaje());
		
		ResponseEntity<List<User>> users=userController.allUsers();
		assertNotNull(users);
	}
	
}
