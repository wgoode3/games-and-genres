package com.hygogg.games.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

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
	
}
