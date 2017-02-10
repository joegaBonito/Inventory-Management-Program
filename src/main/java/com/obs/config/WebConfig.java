package com.obs.config;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
        .addResourceHandler("/resources/**");
	}
	
	/*
	 * Beans are configured in @Configuration. 
	 * This allows dependency injection.
	 * Here, Map<Date,Integer> can be used globally.
	 */
	
	@Bean
	public Map<Date, Integer> myMap() {
	    final Map<Date, Integer> myMap = new HashMap<Date,Integer>();
	    return myMap;
	}
}
