package com.example.VaccinationControl.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.VaccinationControl.entities.User;
import com.example.VaccinationControl.services.UserService;

@RestController
@RequestMapping("users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<User> insertUser(@Valid @RequestBody User userData) {
		User user = userService.insertUser(userData);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/"+user.getId()).build().toUri();
		return ResponseEntity.created(uri).body(user); 
	}
}
