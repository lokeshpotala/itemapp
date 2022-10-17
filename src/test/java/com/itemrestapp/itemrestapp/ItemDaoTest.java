package com.itemrestapp.itemrestapp;

import static org.junit.jupiter.api.Assertions.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.dao.ItemDao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.model.Item;

@SpringBootTest
class ItemDaoTest {
	
	@Autowired
	ItemDao dao;

	@Test
	void test() {
		Item expItem=new Item();
		expItem.setItemId(1);
		expItem.setItemName("Coffee");
		expItem.setPrice(20);
		expItem.setQuantity(150);
		
		dao.save(expItem);
		Item actualItem=dao.findById(expItem.getItemId()).get();
		
		Assertions.assertEquals(expItem.getPrice(), actualItem.getPrice());
	}
	
	@Test()
	void testItemByPrice() {
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
		//dao.delete(i2);
		List<Item> actualList=dao.findByPrice(20);
		for(Item i:actualList) {
			assertEquals(i.getItemId(),expList.get(actualList.indexOf(i)).getItemId());
		}
//		boolean res=actualList.toString().equals(expList.toString());
//		Assertions.assertEquals(true, res);
	}
	


}
