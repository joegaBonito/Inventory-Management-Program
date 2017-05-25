package com.obs.General.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@ControllerAdvice
public class HomeController {

	@Secured({"ROLE_GUEST","ROLE_USER","ROLE_ADMIN"})
	@RequestMapping("/")
	public String index(Model model) {
		return "index";
		
	}
	@RequestMapping("/error/error401")
    public String error401() {
        return "/error/error401";
    }
	@RequestMapping("/error/error404")
    public String error404() {
        return "/error/error404";
    }
	@RequestMapping("/error/error500")
    public String error500() {
        return "/error/error500";
    }
}
