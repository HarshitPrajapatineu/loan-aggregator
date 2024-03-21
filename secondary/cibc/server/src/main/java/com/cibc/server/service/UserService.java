package com.cibc.server.service;

import java.util.ArrayList;
import java.util.Optional;

import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cibc.server.dto.UserDTO;
import com.cibc.server.entity.User;
import com.cibc.server.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public ArrayList<User> getAllUsers() {
		ArrayList<User> users = new ArrayList<>();
		userRepository.findAll().forEach(users::add);
		return users;
	}

//	public User getUserByUsername(String username, String password) {
//		ArrayList<User> users = null;
//		if (username != null) {
//			users = userRepository.findByUsername(username);
//		}
//		if( users != null && !users.isEmpty())
//		{
//			for (User user : users) {
//				if (user.getUsername() == username && user.getPassword().equals(MD5Encoder(password))) {
//					
//				}
//			}
//		}
//		return use;
//	}

	public ArrayList<User> getAllUsersByRole(String role) {
		ArrayList<User> users = new ArrayList<>();
		if (role == null) {
			userRepository.findAll().forEach(users::add);
		} else {
			userRepository.findByRole(role).forEach(users::add);
		}
		return users;
	}

	private int getNextId() {
		ArrayList<User> users = getAllUsers();
		return users.get(users.size() - 1).getId() + 1;
	}

	public int getNextUserId() {
		ArrayList<User> users = getAllUsers();
		int currentSize = users.size();
		int finalId;
		if (currentSize == 0) {
			finalId = 1;
		} else {
			finalId = users.get(users.size() - 1).getId() + 1;
		}
		return finalId;
	}

	public User insertUser(UserDTO userDTO) {
		User user = new User();
		user.setFirstname(userDTO.getFirstname());
		user.setLastname(userDTO.getLastname());
		user.setEmail(userDTO.getEmail());
		user.setPassword(userDTO.getPassword());
		user.setPhone(userDTO.getPhone());
		user.setRole(userDTO.getRole());
		user.setUsername(userDTO.getUsername());
		user.setAddress(userDTO.getAddress());
		user.setGender(userDTO.getGender());

		User savedUser = null;

		try {
			savedUser = userRepository.save(user);
		} catch (Exception ex) {
			System.out.println("Exception here - " + ex.getMessage());
		}
		return savedUser;
	}

	public boolean deleteUser(String username) {
		Optional<User> user = userRepository.findByUsername(username);
		if (user.isPresent()) {
			userRepository.delete(user.get());
			return true;
		} else {
			return false;
		}
	}

	public User updateUser(UserDTO userModel) {
		Optional<User> user = userRepository.findByUsername(userModel.getUsername());
		if (user.isPresent()) {
			User _user = user.get();
			_user.setFirstname(userModel.getFirstname());
			_user.setLastname(userModel.getLastname());
			_user.setEmail(userModel.getEmail());
			_user.setPassword(userModel.getPassword());
			_user.setPhone(userModel.getPhone());
			_user.setRole(userModel.getRole());
			_user.setAddress(userModel.getAddress());
			_user.setGender(userModel.getGender());
			_user = userRepository.save(_user);
			return _user;
		}
		return null;

	}

	public User updateUserPassword(UserDTO userModel) {
		Optional<User> user = userRepository.findByUsername(userModel.getUsername());
		if (user.isPresent()) {
			User _user = user.get();
			_user.setPassword(userModel.getPassword());
			_user = userRepository.save(_user);
			return _user;
		}
		return null;

	}

}
