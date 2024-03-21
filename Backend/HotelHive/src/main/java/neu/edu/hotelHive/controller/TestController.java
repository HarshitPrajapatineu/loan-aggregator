package neu.edu.hotelHive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import neu.edu.hotelHive.model.UserDTO;

import java.util.ArrayList;

@RestController
@RequestMapping("/hotelHive")
public class TestController {

	@Autowired
	@Qualifier("eurekaTemplate")
	private RestTemplate restEurekaTemplate;


	@GetMapping("/getUserCount")
	public ResponseEntity<String> getAllUsers() {
		String url = "http://expedia-server/expedia/getUserData";
		String postForObject = restEurekaTemplate.getForObject(url, String.class);
		return new ResponseEntity<>(postForObject, HttpStatus.OK);
	}
	
	@GetMapping("/getUserData")
	public ResponseEntity<ArrayList<UserDTO>> getAllUserCount() {
		
		String url = "http://expedia-server/expedia/getUsers";
		
		@SuppressWarnings("unchecked")
		ArrayList<UserDTO> postForObject = restEurekaTemplate.getForObject(url, ArrayList.class);
		return new ResponseEntity<>(postForObject, HttpStatus.OK);
	}
}
