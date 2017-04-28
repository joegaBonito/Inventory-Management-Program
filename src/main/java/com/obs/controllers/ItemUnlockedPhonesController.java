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
import com.obs.services.MasterService;

@Controller
public class ItemUnlockedPhonesController {

	private ItemUnlockedPhonesService itemUnlockedPhonesService;
	private MasterService masterService;
	
	@Autowired
	public ItemUnlockedPhonesController(ItemUnlockedPhonesService itemUnlockedPhonesService, MasterService masterService) {
		super();
		this.itemUnlockedPhonesService = itemUnlockedPhonesService;
		this.masterService = masterService;
	}
	
	@RequestMapping("/itemUnlockedPhone/create")
	public String itemCreate(Model model) {
		model.addAttribute("itemUnlockedPhone", new ItemUnlockedPhone());
		return "/itemUnlockedPhone/inputForm";
	}
	
	@RequestMapping(value = "/itemUnlockedPhone/save", method=RequestMethod.POST) 
	public String inputForm(@ModelAttribute("itemUnlockedPhone") ItemUnlockedPhone itemUnlockedPhone) {
		itemUnlockedPhone.setDeleteYN('N');
		itemUnlockedPhonesService.save(itemUnlockedPhone);
		return "redirect:/itemUnlockedPhone/list"; 
	}
	
	@RequestMapping("/itemUnlockedPhone/list")
	public String item(Model model) {
		model.addAttribute("itemUnlockedPhones",itemUnlockedPhonesService.list());
		return "/itemUnlockedPhone/list";
	}
	
	@RequestMapping("/itemUnlockedPhone/edit/{id}")
	public String edit(@PathVariable Long id, Model model){
		model.addAttribute("itemUnlockedPhone", itemUnlockedPhonesService.get(id));
		return "/itemUnlockedPhone/inputForm";		
	}
	
	@RequestMapping("/itemUnlockedPhone/delete/{id}")
	public String delete(@PathVariable Long id, Model model){
		itemUnlockedPhonesService.delete(id);
		masterService.delete(itemUnlockedPhonesService.getProductId(id));
		return "redirect:/itemUnlockedPhone/list";		
	}
}
