package com.hygogg.games.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.hygogg.games.models.User;
import com.hygogg.games.services.UserService;


@Controller
public class UserController {

	private static UserService userServ;
	
	public UserController(UserService userServ) {
		this.userServ = userServ;
	}
	
	@GetMapping("/sign_in")
	public String signIn(Model model) {
		model.addAttribute("registerringUser", new User());
		return "signIn.jsp";
	}
	
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("registerringUser") User registerringUser, BindingResult result) {
		if(!registerringUser.getPassword().equals(registerringUser.getConfirm())) {
			result.rejectValue("confirm", "Match", "Confirm Password must match Password!");
		}
		if(userServ.getUser(registerringUser.getEmail()) != null) {
			result.rejectValue("email", "Unique", "Email already in use!");
		}
		
		if(result.hasErrors()) {
			return "signIn.jsp";
		} else {
			userServ.create(registerringUser);
			return "redirect:/sign_in";
		}
	}
	
}
