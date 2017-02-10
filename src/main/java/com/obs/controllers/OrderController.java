package com.obs.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.obs.domain.OrderEntity;
import com.obs.services.ItemAccessoryService;
import com.obs.services.OrderService;

@Controller
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ItemAccessoryService itemAccessoryService;
	
	public OrderController(OrderService orderService, ItemAccessoryService itemAccessoryService) {
		super();
		this.orderService = orderService;
		this.itemAccessoryService = itemAccessoryService;
	}
	
	@RequestMapping("/order/create") 
	public String orderCreate(Model model) {
		model.addAttribute("orderEntity", new OrderEntity());
		model.addAttribute("itemAccessories",itemAccessoryService.list());
		return "/order/inputForm";
	}
	
	@RequestMapping(value = "/order/save", method=RequestMethod.POST)
	public String orderSave(@ModelAttribute("orderEntity") @Valid OrderEntity orderEntity, BindingResult bindingResultOrderEntity) {
/*		if (bindingResultOrderEntity.hasErrors()) {
	        return "index";
	    }*/
		orderService.save(orderEntity);
		return "redirect:/order/list";
	}
	
	@RequestMapping("/order/list")
	public String orderList(Model model) {
		model.addAttribute("orderEntities", orderService.list());
		return "/order/list";
	}
	
	@RequestMapping("/order/delete/{id}")
	public String delete(@PathVariable Long id, RedirectAttributes redirectAttrs) {
						orderService.delete(id);
		redirectAttrs.addFlashAttribute("message", "Order was deleted!");
		return "redirect:/order/list";
	}
}
 