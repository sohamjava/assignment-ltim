package com.sohamsg.assignments.backend.controller.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/ui")
@Slf4j

public class MvcUIController {

	@GetMapping("departments")
	public ModelAndView departmentPage() {
		return new ModelAndView("department");
	}
}
