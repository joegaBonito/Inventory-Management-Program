package com.obs.Phones.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.obs.Master.services.MasterService;
import com.obs.Phones.domain.ItemUnlockedPhone;
import com.obs.Phones.services.impl.ItemUnlockedPhonesServiceImpl;

@Controller
public class ItemUnlockedPhonesController {

	private ItemUnlockedPhonesServiceImpl itemUnlockedPhonesServiceImpl;
	private MasterService masterService;
	
	@Autowired
	public ItemUnlockedPhonesController(ItemUnlockedPhonesServiceImpl itemUnlockedPhonesServiceImpl, MasterService masterService) {
		super();
		this.itemUnlockedPhonesServiceImpl = itemUnlockedPhonesServiceImpl;
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
		itemUnlockedPhonesServiceImpl.save(itemUnlockedPhone);
		masterService.saveItemUnlockedPhone(itemUnlockedPhone);
		return "redirect:/itemUnlockedPhone/list"; 
	}
	
	@RequestMapping("/itemUnlockedPhone/list")
	public String item(Model model) {
		model.addAttribute("itemUnlockedPhones",itemUnlockedPhonesServiceImpl.list());
		return "/itemUnlockedPhone/list";
	}
	
	@RequestMapping("/itemUnlockedPhone/edit/{id}")
	public String edit(@PathVariable Long id, Model model){
		model.addAttribute("itemUnlockedPhone", itemUnlockedPhonesServiceImpl.get(id));
		return "/itemUnlockedPhone/inputForm";		
	}
	
	@RequestMapping("/itemUnlockedPhone/delete/{id}")
	public String delete(@PathVariable Long id, Model model){
		itemUnlockedPhonesServiceImpl.delete(id);
		masterService.delete(itemUnlockedPhonesServiceImpl.getProductId(id));
		return "redirect:/itemUnlockedPhone/list";		
	}
}
