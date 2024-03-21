package com.scotia.server.controller;

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

import com.scotia.server.service.UserService;
import com.scotia.server.dto.UserDTO;
import com.scotia.server.entity.User;

@RestController
@CrossOrigin(originPatterns = "*")
@RequestMapping("/scotia")
public class UserController {

	@Autowired
	private UserService userService;
	
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
	
	@RequestMapping(value= "/addUser", method= RequestMethod.POST)
	public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO) {
		User users = userService.insertUser(userDTO);
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	@RequestMapping(value= "/deleteUser/{username}", method= RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteUser(@PathVariable String username){
		boolean isDeleted = userService.deleteUser(username);
		return new ResponseEntity<>(isDeleted, HttpStatus.OK);
	}
	
	@RequestMapping(value= "/updateUser", method= RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@RequestBody UserDTO userModel){
		User user = userService.updateUser(userModel);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
}
