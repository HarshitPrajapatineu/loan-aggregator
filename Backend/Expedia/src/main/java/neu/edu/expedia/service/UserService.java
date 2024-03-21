package neu.edu.expedia.service;

import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import neu.edu.expedia.dto.UserDTO;
import neu.edu.expedia.entity.User;
import neu.edu.expedia.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public ArrayList<User> getAllUsers() {
		ArrayList<User> users = new ArrayList<>();
		userRepository.findAll().forEach(users::add);
		return users;	
	}
	
	public ArrayList<User> getAllUsersByRole(String role) {	
		ArrayList<User> users = new ArrayList<>();
		if(role == null) {
			userRepository.findAll().forEach(users::add);
		} else {
			userRepository.findByRole(role).forEach(users::add);
		}
		return users;
	}
	
	private int getNextUserId() {
		ArrayList<User> users = getAllUsers();	
		return users.get(users.size()-1).getUserId()+1;
	}

	public User insertUser(UserDTO userDTO) {
		User user = new User();
		user.setUserId(getNextUserId());
		user.setEmail(userDTO.getEmail());
		user.setIsActive(userDTO.getIsActive());
		user.setPassword(userDTO.getPassword());
		user.setPhoneNumber(userDTO.getPhoneNumber());
		user.setRole(userDTO.getRole());
		user.setUsername(userDTO.getUsername());
		
		User savedUser = null;
		
		try {
			savedUser = userRepository.save(user);
		} catch(Exception ex) {
			System.out.println("Exception here - " + ex.getMessage());
		}
		return savedUser;
	}

	public boolean deleteUser(String username) {
		Optional<User> user = userRepository.findByUsername(username);
		if(user.isPresent()) {
			userRepository.delete(user.get());
			return true;
		}else {
			return false;
		}
	}
	
	public User updateUser(UserDTO userModel) {
		Optional<User> user = userRepository.findByUsername(userModel.getUsername());
		if(user.isPresent()) {
			User _user = user.get();
			_user.setIsActive(userModel.getIsActive());
			_user.setPhoneNumber(userModel.getPhoneNumber());
			_user.setEmail(userModel.getEmail());
			_user.setPassword(userModel.getPassword());
			_user.setRole(userModel.getRole());
			_user = userRepository.save(_user);
			return _user;
		}
		return null;
		
	}

}
