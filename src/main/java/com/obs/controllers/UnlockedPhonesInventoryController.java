package com.obs.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.obs.domain.ItemUnlockedPhone;
import com.obs.domain.UnlockedPhonesInventory;
import com.obs.services.CalendarService;
import com.obs.services.ItemUnlockedPhonesService;
import com.obs.services.PhoneInventoryService;
import com.obs.services.PhonesReceivedQuantityService;

@Controller
public class UnlockedPhonesInventoryController {
	
	
	private PhoneInventoryService phoneInventoryService;
	private ItemUnlockedPhonesService itemUnlockedPhonesService;
	private CalendarService calendarService;
	private PhonesReceivedQuantityService phonesReceivedQuantityService;
	
	@Autowired
	public UnlockedPhonesInventoryController(PhoneInventoryService phoneInventoryService, 
											 ItemUnlockedPhonesService itemUnlockedPhonesService,
											 CalendarService calendarService,
											 PhonesReceivedQuantityService phonesReceivedQuantityService) {
		this.phoneInventoryService = phoneInventoryService;
		this.itemUnlockedPhonesService = itemUnlockedPhonesService;
		this.calendarService = calendarService;
		this.phonesReceivedQuantityService = phonesReceivedQuantityService;
	}
	
	@RequestMapping("/unlockedPhonesInventory/list")
	public String inventoryList(Model model) {
		phoneInventoryService.setSalesQuantity();
		phoneInventoryService.setSalesAmount();
		List<Date> days = calendarService.getAllDays();
		model.addAttribute("days",days);
		model.addAttribute("phonesReceivedQuantities",phonesReceivedQuantityService.list());
		model.addAttribute("itemUnlockedPhones", itemUnlockedPhonesService.list());
		return "/unlockedPhonesInventory/list";
	}
	
	@RequestMapping("/unlockedPhonesInventory/inputReceivedItem/save/{itemUnlockedPhoneId}")
	public String inputReceivedItem(@PathVariable(value="itemUnlockedPhoneId") Long itemUnlockedPhoneId, Model model) {
		model.addAttribute("itemUnlockedPhone", itemUnlockedPhonesService.get(itemUnlockedPhoneId));
		return "/unlockedPhonesInventory/inputReceivedItem";
		
	}
	
	@RequestMapping(value="/inventorySave", method = RequestMethod.POST)
	public String inventorySave(@ModelAttribute("itemUnlockedPhone") ItemUnlockedPhone itemUnlockedPhone, Model model) {
		phoneInventoryService.setPurchasedQuantity(itemUnlockedPhone);
		phoneInventoryService.setPurchasedAmount(itemUnlockedPhone);
		phoneInventoryService.setTotalPurchasedQuantity(itemUnlockedPhone);
		phoneInventoryService.setTotalPurchasedAmount(itemUnlockedPhone);
		phoneInventoryService.setCurrentInventoryAmount(itemUnlockedPhone);
		phoneInventoryService.setCurrentInventory(itemUnlockedPhone);
		UnlockedPhonesInventory unlockedPhonesInventory = itemUnlockedPhone.getUnlockedPhonesInventory();
		phoneInventoryService.setTotals(unlockedPhonesInventory);
		int num = itemUnlockedPhone.getUnlockedPhonesInventory().getPurchasedQuantity();
		model.addAttribute("itemUnlockedPhones", itemUnlockedPhonesService.list());
		return "redirect:/unlockedPhonesInventory/list";
	}
}