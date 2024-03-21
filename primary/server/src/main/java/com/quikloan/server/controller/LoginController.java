package com.quikloan.server.controller;


import java.net.http.HttpRequest;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.quikloan.server.service.UserService;
import com.quikloan.server.authenticator.IJwtGenerator;
import com.quikloan.server.authenticator.JwtGenerator;
import com.quikloan.server.dao.LoginDAO;
import com.quikloan.server.dto.UserDTO;
import com.quikloan.server.dto.UserSession;
import com.quikloan.server.dto.LoginForm;
import com.quikloan.server.entity.User;

@RestController
@CrossOrigin(originPatterns = "*")
@RequestMapping("/quikloan")

public class LoginController {


	@Autowired
	private UserService userService;
	
	@RequestMapping(value= "/login", method= RequestMethod.POST)
	public ResponseEntity<UserSession> login(@RequestBody LoginForm loginForm) {
		boolean loginStatus = false;
		String errorMsg = "Error: Invalid username or password";
		LoginDAO loginDAO = new LoginDAO();
		UserSession userSession = new UserSession();
		IJwtGenerator generator = new JwtGenerator();
		
		try {
			
			userSession = loginDAO.validateLogin(loginForm.getUsername(), loginForm.getPassword());
			if(userSession != null) {
				userSession.setToken(generator.generateToken(userSession));
				loginStatus = true;
				
			}else {
				errorMsg = loginForm.getUsername();
				userSession = new UserSession();
				userSession.setErrMsg(errorMsg);
			}
			
			if(loginStatus) {
				
				return new ResponseEntity<>(userSession, HttpStatus.OK);

			}else {
				return new ResponseEntity<>(userSession, HttpStatus.OK);
			}
		}
		catch (Exception e) {
			errorMsg = e.getMessage();
			userSession = new UserSession();
			userSession.setErrMsg(errorMsg);
			return new ResponseEntity<>(userSession, HttpStatus.OK);
		}
		
	}
	
	@RequestMapping(value= "/logout", method= RequestMethod.GET)
	public ResponseEntity<Boolean> logout() {
		boolean loginStatus = false;
		String errorMsg = "Error: Invalid username or password";
		LoginDAO loginDAO = new LoginDAO();
		UserSession userSession = new UserSession();
		return new ResponseEntity<>(true, HttpStatus.OK);
		
	}
}
