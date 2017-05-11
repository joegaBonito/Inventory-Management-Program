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

import com.obs.General.services.impl.CalendarServiceImpl;
import com.obs.Phones.domain.ItemUnlockedPhone;
import com.obs.Phones.services.impl.ItemUnlockedPhonesServiceImpl;
import com.obs.Phones.services.impl.PhoneInventoryServiceImpl;
import com.obs.Phones.services.impl.PhonesReceivedQuantityServiceImpl;

@Controller
public class UnlockedPhonesInventoryController {
	
	
	private PhoneInventoryServiceImpl phoneInventoryServiceImpl;
	private ItemUnlockedPhonesServiceImpl itemUnlockedPhonesServiceImpl;
	private CalendarServiceImpl calendarServiceImpl;
	private PhonesReceivedQuantityServiceImpl phonesReceivedQuantityServiceImpl;
	
	@Autowired
	public UnlockedPhonesInventoryController(PhoneInventoryServiceImpl phoneInventoryServiceImpl, 
											 ItemUnlockedPhonesServiceImpl itemUnlockedPhonesServiceImpl,
											 CalendarServiceImpl calendarServiceImpl,
											 PhonesReceivedQuantityServiceImpl phonesReceivedQuantityServiceImpl) {
		this.phoneInventoryServiceImpl = phoneInventoryServiceImpl;
		this.itemUnlockedPhonesServiceImpl = itemUnlockedPhonesServiceImpl;
		this.calendarServiceImpl = calendarServiceImpl;
		this.phonesReceivedQuantityServiceImpl = phonesReceivedQuantityServiceImpl;
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
		List<Date> days = calendarServiceImpl.getAllDays();
		model.addAttribute("days",days);
		model.addAttribute("phonesReceivedQuantities",phonesReceivedQuantityServiceImpl.list());
		Page<ItemUnlockedPhone> itemUnlockedPhones = itemUnlockedPhonesServiceImpl.findByDeleteYNPageable(pageable);
		model.addAttribute("itemUnlockedPhones", itemUnlockedPhones);
		return "/unlockedPhonesInventory/list";
	}
	
	@RequestMapping("/unlockedPhonesInventory/inputReceivedItem/save/{itemUnlockedPhoneId}")
	public String inputReceivedItem(@PathVariable(value="itemUnlockedPhoneId") Long itemUnlockedPhoneId, Model model) {
		model.addAttribute("itemUnlockedPhone", itemUnlockedPhonesServiceImpl.get(itemUnlockedPhoneId));
		return "/unlockedPhonesInventory/inputReceivedItem";
	}
	
	@RequestMapping("/unlockedPhonesInventory/inputReturnedItem/save/{itemUnlockedPhoneId}")
	public String inputReturnedItem(@PathVariable(value="itemUnlockedPhoneId") Long itemUnlockedPhoneId, Model model) {
		model.addAttribute("itemUnlockedPhone", itemUnlockedPhonesServiceImpl.get(itemUnlockedPhoneId));
		return "/unlockedPhonesInventory/inputReturnedItem";
	}
	
	@RequestMapping(value="/unlockedPhonesInventory/save", method = RequestMethod.POST)
	public String inventorySave(@ModelAttribute("itemUnlockedPhone") ItemUnlockedPhone itemUnlockedPhone, Model model) {
		phoneInventoryServiceImpl.setPurchasedQuantity(itemUnlockedPhone);
		phoneInventoryServiceImpl.setPurchasedAmount(itemUnlockedPhone);
		phoneInventoryServiceImpl.setTotalPurchasedQuantity(itemUnlockedPhone);
		phoneInventoryServiceImpl.setTotalPurchasedAmount(itemUnlockedPhone);
		phoneInventoryServiceImpl.setCurrentInventoryAmount(itemUnlockedPhone);
		phoneInventoryServiceImpl.setCurrentInventory(itemUnlockedPhone);
		phoneInventoryServiceImpl.setSalesQuantity();
		phoneInventoryServiceImpl.setSalesAmount();
		model.addAttribute("itemUnlockedPhones", itemUnlockedPhonesServiceImpl.list());
		return "redirect:/unlockedPhonesInventory/list";
	}
	
	@RequestMapping(value="/unlockedPhonesInventory/saveReturns", method=RequestMethod.POST)
	public String saveReturns(@ModelAttribute("itemUnlockedPhone") ItemUnlockedPhone itemUnlockedPhone, Model model) {
		phoneInventoryServiceImpl.setReturnedInventory(itemUnlockedPhone);
		phoneInventoryServiceImpl.setSalesAmountAfterReturn(itemUnlockedPhone);
		phoneInventoryServiceImpl.setSalesQuantityAfterReturn(itemUnlockedPhone);
		return "redirect:/unlockedPhonesInventory/list";
	}
}
