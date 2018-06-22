package com.evolocity.springboot.controller;

import org.springframework.stereotype.Controller;
/**
 * APP entry for loading freemarker 
 */
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppResource {

	@RequestMapping("/")
	String home(ModelMap modal) {
		modal.addAttribute("title","Evolocity Test");
		return "index";
	}

	@RequestMapping("/partials/{page}")
	String partialHandler(@PathVariable("page") final String page) {
		return page;
	}

}
