package com.hygogg.games.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.hygogg.games.models.LoginUser;
import com.hygogg.games.models.User;
import com.hygogg.games.repositories.UserRepository;


@Service
public class UserService {

	private static UserRepository userRepo;
	
	public UserService(UserRepository userRepo) {
		this.userRepo = userRepo;
	}
	
	public User create(User registerringUser) {
		String hashed = BCrypt.hashpw(registerringUser.getPassword(), BCrypt.gensalt());
		registerringUser.setPassword(hashed);
		return userRepo.save(registerringUser);
	}
	
	public User getUser(String email) {
		Optional<User> potentialUser = userRepo.findByEmail(email);
		return potentialUser.isPresent() ? potentialUser.get() : null;
	}
	
	public User login(LoginUser loginUser, BindingResult result) {
		if(result.hasErrors()) {
			return null;
		}
		User existingUser = getUser(loginUser.getEmail());
		if(existingUser == null) {
			result.rejectValue("email", "Unique", "Unknown email!");
			return null;
		}
		if(!BCrypt.checkpw(loginUser.getPassword(), existingUser.getPassword())) {
			result.rejectValue("password", "Matches", "Incorrect Password!");
			return null;
		}
		return existingUser;
	}
	
}
