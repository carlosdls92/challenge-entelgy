package com.demo.entelgy.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.demo.entelgy.bean.RemoteBean;

@Service("entelgyService")
public class EntelgyServiceImpl implements EntelgyService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${config.remote.url.json.list}")
	private String url;
	
	public EntelgyServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public EntelgyServiceImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	@Override
	public List<String> restructure() {
		
		List<String> result = null;
		try {
			
			ResponseEntity<RemoteBean[]> response = restTemplate.getForEntity(url, RemoteBean[].class);
			
			if(response.getStatusCode().equals(HttpStatus.OK)) {
				List<RemoteBean> remoteList = Arrays.asList(response.getBody());
				result = remoteList
						.stream()
						.map(e -> e.getPostId()+"|"+e.getId()+"|"+e.getEmail())
						.collect(Collectors.toList());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}


	@Override
	public String helloWorld() {
		return "hello world";
	}

}
