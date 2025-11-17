package com.flightapp.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightapp.repository.UserRepository;
import com.flightapp.entity.User; 
@Service
public class AuthService {
	@Autowired
private UserRepository userRepository;
private Map<String,String> userSessions=new HashMap<>();
public User register(User user) {
    return userRepository.save(user);
}

public String login( String email, String password) {
	User user=userRepository.findByEmail(email).orElseThrow(()->new RuntimeException("user is not found"));
	if(user==null || !user.getPassword().equals(password))
		return null;
	String sessionId=UUID.randomUUID().toString();
	userSessions.put(sessionId,email);
	return sessionId;
	
}
public User getAdmin(String email) {
	User user=userRepository.findByEmail(email).orElseThrow(()->new RuntimeException("no user found"));
	return user;
}
public User getLoggedInUser( String sessionId) {
	String email=userSessions.get(sessionId);
	if(email==null) return null;
	return userRepository.findByEmail(email).orElseThrow(()->new RuntimeException("no user with this session id"));
}
}
