package org.fkjava.layout.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/layout")
public class IndexController {

	@RequestMapping
	public String index() {
		return "layout/index";
	}
}
