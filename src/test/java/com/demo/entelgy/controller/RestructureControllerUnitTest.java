package com.demo.entelgy.controller;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.demo.entelgy.service.EntelgyService;

@RunWith(MockitoJUnitRunner.class)
class RestructureControllerUnitTest {
	
	@Mock
	EntelgyService entelgyService;
	
	private RestructureController restructureController;
	
	@BeforeEach
	void setup() {
		MockitoAnnotations.initMocks(this);
		
		restructureController = new RestructureController(entelgyService);
		List<String> lista = new ArrayList<>();
		
		lista.add("1|1|Eliseo@gardner.biz");
		lista.add("1|2|Jayne_Kuhic@sydney.com");
		
		when(entelgyService.restructure()).thenReturn(lista);
		
	}
	
	@Test
	void restructure() {
		
		ResponseEntity<List<String>> response = (ResponseEntity<List<String>>) restructureController.restructure();
		assertTrue(response.getStatusCode().equals(HttpStatus.OK));
		
		Map<String,Object> out = (Map<String, Object>) response.getBody();
		assertTrue( ((List<String>) out.get("data")).size()>0 );
	}

}
