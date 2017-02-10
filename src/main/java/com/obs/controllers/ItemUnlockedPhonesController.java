package com.obs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.obs.domain.ItemUnlockedPhone;
import com.obs.services.ItemUnlockedPhonesService;

@Controller
public class ItemUnlockedPhonesController {
	@Autowired
	private ItemUnlockedPhonesService itemUnlockedPhonesService;
	
	public ItemUnlockedPhonesController(ItemUnlockedPhonesService itemUnlockedPhonesService) {
		super();
		this.itemUnlockedPhonesService = itemUnlockedPhonesService;
	}
	
	@RequestMapping("/itemUnlockedPhone/create")
	public String itemCreate(Model model) {
		model.addAttribute("itemUnlockedPhone", new ItemUnlockedPhone());
		return "/itemUnlockedPhone/inputForm";
	}
	
	@RequestMapping(value = "/itemUnlockedPhone/save", method=RequestMethod.POST) 
	public String inputForm(@ModelAttribute("itemUnlockedPhone") ItemUnlockedPhone itemUnlockedPhone) {
		itemUnlockedPhonesService.save(itemUnlockedPhone);
		return "redirect:/itemUnlockedPhone/list"; 
	}
	
	@RequestMapping("/itemUnlockedPhone/list")
	public String item(Model model) {
		model.addAttribute("itemUnlockedPhones",itemUnlockedPhonesService.list());
		return "itemUnlockedPhone/list";
	}
	
	@RequestMapping("/itemUnlockedPhone/edit/{id}")
	public String edit(@PathVariable Long id, Model model){
		model.addAttribute("itemUnlockedPhone", itemUnlockedPhonesService.get(id));
		return "/itemUnlockedPhone/inputForm";		
	}
}
