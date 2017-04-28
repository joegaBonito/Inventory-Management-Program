package com.obs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.obs.domain.ItemAccessory;
import com.obs.services.ItemAccessoryService;
import com.obs.services.MasterService;

@Controller
public class ItemAccessoryController {
	
	private ItemAccessoryService itemAccessoryService;
	private MasterService masterService;
	
	@Autowired
	public ItemAccessoryController(ItemAccessoryService itemAccessoryService, MasterService masterService) {
		super();
		this.itemAccessoryService = itemAccessoryService;
		this.masterService = masterService;
	}
	
	@RequestMapping("/itemAccessory/create")
	public String itemCreate(Model model) {
		model.addAttribute("itemAccessory", new ItemAccessory());
		return "/itemAccessory/inputForm";
	}
	
	@RequestMapping(value = "/itemAccessory/save", method=RequestMethod.POST) 
	public String inputForm(@ModelAttribute("itemAccessory") ItemAccessory itemAccessory) {
		itemAccessoryService.save(itemAccessory);
		return "redirect:/itemAccessory/list"; 
	}
	
	@RequestMapping("/itemAccessory/list")
	public String item(Model model) {
		model.addAttribute("itemAccessories",itemAccessoryService.list());
		return "itemAccessory/list";
	}
	
	@RequestMapping("/itemAccessory/edit/{id}")
	public String edit(@PathVariable Long id, Model model){
		model.addAttribute("itemAccessory", itemAccessoryService.get(id));
		return "/itemAccessory/inputForm";		
	}
	
	@RequestMapping("/itemAccessory/delete/{id}")
	public String delete(@PathVariable Long id, Model model){
		itemAccessoryService.deleteYN(id);
		masterService.delete(id);
		return "redirect:/itemAccessory/list";		
	}
}
