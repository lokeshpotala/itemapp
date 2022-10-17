package com.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.dao.ItemDao;
import com.model.Item;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class ItemRestController {
	
	@Autowired
	ItemDao itemDao;
	
	@GetMapping("/homeinfo")
	@ApiOperation(value = "get home info", notes="get message",nickname = "get msg")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "object returned")})
	public String getHomeInfo() {
		return "Home for Itemrestcontroller! Your api is working fine";
	}
	
	@PostMapping("/additem")
	@ApiOperation(value = "add an item", notes="post item",nickname = "post item")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "object added")})
	public ResponseEntity<String> addItem(@RequestBody Item item){
		itemDao.save(item);
		return new ResponseEntity<String>("item added",HttpStatus.OK);
	}
	
	@GetMapping("/getallitems")
	@ApiOperation(value = "get all items", notes="get all",nickname = "getAllItems")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "objects retrieved")})
	public List<Item> getAllItem(){
		return itemDao.findAll();
	}
	
	@PatchMapping("/updateitem")
	@ApiOperation(value = "update an item", notes="update",nickname = "update")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "object updated")})
	public ResponseEntity<String> updateItem(@RequestBody Item item) {
		itemDao.save(item);
		return new ResponseEntity<String>("Item updated",HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteitem")
	@ApiOperation(value = "delete an item", notes="delete",nickname = "Delete")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "object deleted")})
	public ResponseEntity<String> deleteItem(@RequestBody Item item) {
		itemDao.delete(item);
		return new ResponseEntity<String>("Item deleted",HttpStatus.OK);
	}
	
	@GetMapping("/findbyid/{id}")
	@ApiOperation(value = "get item by id", notes="get item by given id",nickname = "get item")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "object returned")})
	public Optional<Item> getItem(@PathVariable int id) {
		return itemDao.findById(id);
	}

}
