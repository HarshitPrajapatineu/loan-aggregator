package com.quikloan.server.controller;

import java.util.ArrayList;
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
import com.quikloan.server.dao.LoginDAO;
import com.quikloan.server.dto.UserDTO;
import com.quikloan.server.entity.User;

@RestController
@CrossOrigin(originPatterns = "*")
@RequestMapping("/quikloan")
public class UserController {

	@Autowired
	private UserService userService;
	private LoginDAO loginDAO = new LoginDAO();
	
	@RequestMapping(value= "/getUsersCount", method= RequestMethod.GET)
	public ResponseEntity<String> getAllUserSize() {
		ArrayList<User> users = userService.getAllUsers();
		return new ResponseEntity<>(Integer.toString(users.size()), HttpStatus.OK);
	}
	
	@RequestMapping(value= "/getUsers", method= RequestMethod.GET)
	public ResponseEntity<ArrayList<User>> getAllUsers() {
		ArrayList<User> users = userService.getAllUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	@RequestMapping(value= "/getUsers/{role}", method= RequestMethod.GET)
	public ResponseEntity<ArrayList<User>> getAllUsersByRole(@PathVariable String role) {
		ArrayList<User> users = userService.getAllUsersByRole(role);
		return new ResponseEntity<>(users, HttpStatus.OK);

	}
	
	@RequestMapping(value= "/getUser/{username}", method= RequestMethod.GET)
	public ResponseEntity<UserDTO> getUser(@PathVariable String username) {
		UserDTO user = loginDAO.getUser(username);
		return new ResponseEntity<>(user, HttpStatus.OK);

	}
	
	@RequestMapping(value= "addUser", method= RequestMethod.POST)
	public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
		boolean result = loginDAO.registerUser(userDTO);
		if(result) {
			return new ResponseEntity<>(userDTO, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
	
	@RequestMapping(value= "/deleteUser/{username}", method= RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteUser(@PathVariable String username){
		boolean isDeleted = loginDAO.deleteUser(username);
		return new ResponseEntity<>(isDeleted, HttpStatus.OK);
	}
	
	@RequestMapping(value= "/updateUser", method= RequestMethod.POST)
	public ResponseEntity<Boolean> updateUser(@RequestBody UserDTO userModel){
		boolean result = loginDAO.editUser(userModel);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value= "/updatePassword", method= RequestMethod.POST)
	public ResponseEntity<Boolean> updatePassword(@RequestBody UserDTO userModel){
		boolean result = loginDAO.updatePassword(userModel);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
}
