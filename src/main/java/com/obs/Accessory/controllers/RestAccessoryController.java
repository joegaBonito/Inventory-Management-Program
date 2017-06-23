package com.obs.Accessory.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.obs.Accessory.domain.ItemAccessory;
import com.obs.Accessory.services.impl.ItemAccessoryServiceImpl;

@CrossOrigin(origins = "http://localhost:3000")
@ExposesResourceFor(ItemAccessory.class)
@RestController
public class RestAccessoryController {
	
	private ItemAccessoryServiceImpl itemAccessoryServiceImpl;
	
	@Autowired
	public RestAccessoryController(ItemAccessoryServiceImpl itemAccessoryServiceImpl) {

		this.itemAccessoryServiceImpl = itemAccessoryServiceImpl;
	}
	
	
	@RequestMapping(value="/api/itemaccessory", produces = "application/hal+json")
	@ResponseBody
	public List<ItemAccessory> listItemAccessories() {
		List<ItemAccessory> itemAccessories = itemAccessoryServiceImpl.list();
		return itemAccessories;
		
	}
	
	@RequestMapping(value="/api/itemaccessory/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getItemAccessory(@PathVariable ("id") long id) {
		ItemAccessory itemAccessory = itemAccessoryServiceImpl.get(id);
		return new ResponseEntity<ItemAccessory>(itemAccessory, HttpStatus.OK);
		
	}
}
