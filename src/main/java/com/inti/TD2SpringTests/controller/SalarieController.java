package com.inti.TD2SpringTests.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.inti.TD2SpringTests.repository.SalarieRepository;

@Controller
public class SalarieController
{
	
	@Autowired
	SalarieRepository salarieRepository;
	
	@GetMapping("/salaries")
	public String getAllSalaries(Model m)
	{
		m.addAttribute("listeSalarie", salarieRepository.findAll());
		
		return "salaries";
	}

}
