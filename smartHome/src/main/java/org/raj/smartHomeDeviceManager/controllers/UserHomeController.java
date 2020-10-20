package org.raj.smartHomeDeviceManager.controllers;

import java.util.ArrayList;

import org.raj.smartHomeDeviceManager.domain.UserHome;
import org.raj.smartHomeDeviceManager.repos.HomeRepo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserHomeController {
	HomeRepo object=new HomeRepo();
	@CrossOrigin(origins = "*")
	@GetMapping("/home")
	public ArrayList<UserHome> home(@RequestParam(value = "username", defaultValue = "raj") String username) {
		object.setUsername(username);
		ArrayList<UserHome> devices=object.get();
		return devices;
	}
}
	

