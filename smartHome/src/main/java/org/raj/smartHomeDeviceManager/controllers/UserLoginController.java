package org.raj.smartHomeDeviceManager.controllers;

import org.raj.smartHomeDeviceManager.domain.LoginStatus;
import org.raj.smartHomeDeviceManager.domain.UserLogin;
import org.raj.smartHomeDeviceManager.repos.LoginRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserLoginController {
	LoginRepo repo=new LoginRepo();
	@CrossOrigin(origins = "*")
	@PostMapping("/login")
	public LoginStatus login(@RequestBody UserLogin login) {
		boolean check=repo.validate(login.getUsername(),login.getPassword());
		String message="";
		if(check==false) {
			message="invalid username / password";
		}
		LoginStatus loginStatus=new LoginStatus(check,message);
		return loginStatus;
	}
}