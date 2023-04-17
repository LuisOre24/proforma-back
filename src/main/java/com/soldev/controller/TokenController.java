package com.soldev.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soldev.entity.JwtRequest;
import com.soldev.entity.JwtResponse;
import com.soldev.service.impl.JwtService;

@RestController
@RequestMapping("/v1/auth/admin")
public class TokenController {
	
	@Autowired
	private JwtService jwtService;

	
	@PostMapping("/authenticate")
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
		return jwtService.createJwtToken(jwtRequest);
    }
	
}
