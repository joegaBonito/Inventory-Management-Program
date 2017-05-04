package com.obs.Phones.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.obs.Phones.domain.ItemUnlockedPhone;
import com.obs.Phones.services.ItemUnlockedPhonesService;
import com.obs.Phones.services.PhoneInventoryService;
import com.obs.Phones.services.PhonesReceivedQuantityService;
import com.obs.General.services.CalendarService;

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
	
	/*
	 * Replaced by Pageable List View below.
	 */
/*	@RequestMapping("/unlockedPhonesInventory/list")
	public String inventoryList(Model model) {
		List<Date> days = calendarService.getAllDays();
		model.addAttribute("days",days);
		model.addAttribute("phonesReceivedQuantities",phonesReceivedQuantityService.list());
		model.addAttribute("itemUnlockedPhones", itemUnlockedPhonesService.list());
		return "/unlockedPhonesInventory/list";
	}*/
	
	@RequestMapping("/unlockedPhonesInventory/list")
	public String inventoryPagination(Model model, @PageableDefault(value = 5) Pageable pageable) {
		/*
		 * By changing List<?> to Page<?>, the upsOrders variable now has the pagination ability.
		 */
		List<Date> days = calendarService.getAllDays();
		model.addAttribute("days",days);
		model.addAttribute("phonesReceivedQuantities",phonesReceivedQuantityService.list());
		Page<ItemUnlockedPhone> itemUnlockedPhones = itemUnlockedPhonesService.findByDeleteYNPageable(pageable);
		model.addAttribute("itemUnlockedPhones", itemUnlockedPhones);
		return "/unlockedPhonesInventory/list";
	}
	
	@RequestMapping("/unlockedPhonesInventory/inputReceivedItem/save/{itemUnlockedPhoneId}")
	public String inputReceivedItem(@PathVariable(value="itemUnlockedPhoneId") Long itemUnlockedPhoneId, Model model) {
		model.addAttribute("itemUnlockedPhone", itemUnlockedPhonesService.get(itemUnlockedPhoneId));
		return "/unlockedPhonesInventory/inputReceivedItem";
	}
	
	@RequestMapping("/unlockedPhonesInventory/inputReturnedItem/save/{itemUnlockedPhoneId}")
	public String inputReturnedItem(@PathVariable(value="itemUnlockedPhoneId") Long itemUnlockedPhoneId, Model model) {
		model.addAttribute("itemUnlockedPhone", itemUnlockedPhonesService.get(itemUnlockedPhoneId));
		return "/unlockedPhonesInventory/inputReturnedItem";
	}
	
	@RequestMapping(value="/unlockedPhonesInventory/save", method = RequestMethod.POST)
	public String inventorySave(@ModelAttribute("itemUnlockedPhone") ItemUnlockedPhone itemUnlockedPhone, Model model) {
		phoneInventoryService.setPurchasedQuantity(itemUnlockedPhone);
		phoneInventoryService.setPurchasedAmount(itemUnlockedPhone);
		phoneInventoryService.setTotalPurchasedQuantity(itemUnlockedPhone);
		phoneInventoryService.setTotalPurchasedAmount(itemUnlockedPhone);
		phoneInventoryService.setCurrentInventoryAmount(itemUnlockedPhone);
		phoneInventoryService.setCurrentInventory(itemUnlockedPhone);
		phoneInventoryService.setSalesQuantity();
		phoneInventoryService.setSalesAmount();
		model.addAttribute("itemUnlockedPhones", itemUnlockedPhonesService.list());
		return "redirect:/unlockedPhonesInventory/list";
	}
	
	@RequestMapping(value="/unlockedPhonesInventory/saveReturns", method=RequestMethod.POST)
	public String saveReturns(@ModelAttribute("itemUnlockedPhone") ItemUnlockedPhone itemUnlockedPhone, Model model) {
		phoneInventoryService.setReturnedInventory(itemUnlockedPhone);
		phoneInventoryService.setSalesAmountAfterReturn(itemUnlockedPhone);
		phoneInventoryService.setSalesQuantityAfterReturn(itemUnlockedPhone);
		return "redirect:/unlockedPhonesInventory/list";
	}
}
