package com.demo.entelgy.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.entelgy.service.EntelgyService;

@RestController
public class RestructureController {
	
	public RestructureController() {
		// TODO Auto-generated constructor stub
	}
	
	public RestructureController(EntelgyService entelgyService) {
		this.entelgyService = entelgyService;
	}

	@Autowired
	private EntelgyService entelgyService;
	
	@PostMapping("/restructure")
	public ResponseEntity<?> restructure(){
		List<String	> lista = entelgyService.restructure();
		Map<String,Object> out = new HashMap<>();
		out.put("data", lista);
		return new ResponseEntity<Map<String,Object>>(out, HttpStatus.OK);
	}
	
}
