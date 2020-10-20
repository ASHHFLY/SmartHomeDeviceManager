package org.raj.smartHomeDeviceManager.controllers;

import org.raj.smartHomeDeviceManager.domain.UserRegister;
import org.raj.smartHomeDeviceManager.domain.UserRegisterStatus;
import org.raj.smartHomeDeviceManager.repos.RegisterRepo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRegisterController {
	@CrossOrigin(origins = "*")
	@PostMapping("/register")
	public UserRegisterStatus register(@RequestBody UserRegister register) {
		UserRegisterStatus status;
		RegisterRepo registerRepo=new RegisterRepo(register);
		status=registerRepo.check();
		if(status.getStatus()==true) {
		registerRepo.write();
		}
		return status;
	}
}