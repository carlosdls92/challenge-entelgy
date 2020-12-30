package com.demo.entelgy.service;

import static org.mockito.Mockito.when;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.demo.entelgy.bean.RemoteBean;

@RunWith(MockitoJUnitRunner.class)
class EntelgyServiceUnitTest {
	
	@Mock
	RestTemplate restTemplate;
	
	@Value("${config.remote.url.json.list}")
	private String url;
	
	private EntelgyService entelgyService;
	
	@BeforeEach
	void setup() {
		MockitoAnnotations.initMocks(this);
		
		entelgyService = new EntelgyServiceImpl(restTemplate);
		RemoteBean[] array = new RemoteBean[2];
		
		RemoteBean remoteBean;
		remoteBean = new RemoteBean(1, 1, "id labore ex et quam laborum", "Eliseo@gardner.biz", "id labore ex et quam laborum");
		array[0] = remoteBean;
		remoteBean = new RemoteBean(1, 2, "quo vero reiciendis velit similique earum", "Jayne_Kuhic@sydney.com", "quo vero reiciendis velit similique earum");
		array[1] = remoteBean;
		
		ResponseEntity<RemoteBean[]> response = ResponseEntity.ok(array);
		when(restTemplate.getForEntity(url, RemoteBean[].class)).thenReturn(response);
		
	}
	
	@Test
	void testRestructure() {
		List<String> list = entelgyService.restructure();
		list.forEach(System.out::println);
		
		Assertions.assertThat(list.size()>0).isTrue();
	}

}
