package com.demo.entelgy;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;



@RunWith(SpringRunner.class)
@SpringBootTest
class ChallengeEntelgy1ApplicationTests {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;
	
	ObjectMapper objectMapper = new ObjectMapper();

	@BeforeEach
	private void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	void contextLoads() throws Exception {

		MvcResult result = mockMvc.perform(post("/restructure").accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk()).andReturn();
		
		String resultContent = result.getResponse().getContentAsString();
		Map<String,Object> out = objectMapper.readValue(resultContent, Map.class);
		
		assertTrue( ((List<String>) out.get("data")).size()>0 );
	}

}
