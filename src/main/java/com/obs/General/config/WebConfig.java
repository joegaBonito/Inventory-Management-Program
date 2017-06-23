package com.obs.General.config;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.script.ScriptTemplateConfigurer;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class WebConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
	 * #addViewControllers(org.springframework.web.servlet.config.annotation.
	 * ViewControllerRegistry) Sets /login to the highest precedence in the web
	 * site without having to create a controller.
	 */

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		super.addViewControllers(registry);
		registry.addViewController("/login").setViewName("auth/login");
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
	}

	/*
	 * Beans are configured in @Configuration. This allows dependency injection.
	 * Here, Map<Date,Integer> can be used globally.
	 */
	@Bean
	public Map<Date, Integer> myMap() {
		final Map<Date, Integer> myMap = new HashMap<Date, Integer>();
		return myMap;
	}
	
	/*
	 * This bean catches error and redirects.
	 */
	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {
	    return new EmbeddedServletContainerCustomizer() {
	        @Override
	        public void customize(ConfigurableEmbeddedServletContainer container) {
	        	ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/error/error401");
	            ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/error/error404");
	            ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/error500");
	            container.addErrorPages(error401Page, error404Page, error500Page);
	        }
	    };
	}
	
	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**");
            }
        };
    }
}
