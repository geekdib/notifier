package com.dib.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontController {

	@GetMapping("/home")
	public String index(Model model) {
		System.err.println("hello");
		return "base";
	}
}
