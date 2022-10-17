package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.Item;

@Repository
public interface ItemDao extends JpaRepository<Item,Integer>{
	
	public List<Item> findByPrice(float price);
	
}
