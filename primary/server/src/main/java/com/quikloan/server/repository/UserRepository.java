package com.quikloan.server.repository;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.quikloan.server.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	ArrayList<User> findByRole(String role);
	ArrayList<User> findByEmail(String email);
	Optional<User> findByUsername(String username);

}
