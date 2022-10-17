package com.itemrestapp.itemrestapp;

import static org.junit.jupiter.api.Assertions.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.dao.ItemDao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.model.Item;

@SpringBootTest
class ItemRestControllerTest {

	@Autowired
	ItemDao dao;
	
	@Test
    void test1() throws URISyntaxException, JsonProcessingException {
      
      RestTemplate template=new RestTemplate();
      final String url="http://localhost:9096/findbyid/1";
      URI uri=new URI(url);
      
      ResponseEntity<String> res=template.getForEntity(uri,String.class);
      Assertions.assertEquals(HttpStatus.OK,res.getStatusCode());
      
  }
	@Test
	public void testGetItemListSuccess() throws URISyntaxException 
	{
		List<Item> expList=new ArrayList<>();
		Item i1=new Item();
		i1.setItemId(1);
		i1.setItemName("Coffee");
		i1.setPrice(20);
		i1.setQuantity(150);
		Item i2=new Item();
		i2.setItemId(2);
		i2.setItemName("Tea");
		i2.setPrice(20);
		i2.setQuantity(30);
		expList.add(i1);
		expList.add(i2);
		dao.save(i1);
		dao.save(i2);
	    RestTemplate restTemplate = new RestTemplate();
	     
	    final String baseUrl = "http://localhost:9096/getallitems";
	    URI uri = new URI(baseUrl);
	 
	    HttpHeaders headers=new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<List<Item>> entity=new HttpEntity<List<Item>>(headers);
		
		RestTemplate template=new RestTemplate();
		List<Item> actualList=template.exchange(uri, 
				HttpMethod.GET,entity,List.class).getBody();
		System.out.println(actualList);
		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
	    assertEquals(200, result.getStatusCodeValue());
	   // assertEquals(expList, actualList);
	}

}
