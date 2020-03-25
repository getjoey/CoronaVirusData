package com.joseph.coronavirus.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.joseph.coronavirus.CountryRepositoryI;
import com.joseph.coronavirus.model.CountryModel;

@Controller
public class VirusDataController {

	@Autowired
	CountryRepositoryI repo;
	
	@RequestMapping("/")
	public String home() {
		return "Home";
	}
	
	@RequestMapping("/countries")
	public ModelAndView getStudents() {
		ModelAndView mav = new ModelAndView("CountryList"); //html name
		
		List<CountryModel> countries = repo.findAll();
		
		mav.addObject("countries", countries); //object name
		return mav;
	}
	
	@RequestMapping("/updateCountry")
	public ModelAndView updateCountry(@ModelAttribute CountryModel country) {
		ModelAndView mav = new ModelAndView("UpdateCountry");
		mav.addObject("country", country);
		return mav;
	}
	
	@RequestMapping("/updateCountry/{name}")
	public ModelAndView updateCountrySpecified(@PathVariable("name") String name) {
		CountryModel country =  getCountryModelByName(name);
		country.setName(name);
	
		ModelAndView mav = new ModelAndView("UpdateCountry");
		mav.addObject("country", country);
		return mav;
	}
	
	@RequestMapping(value = "/getCountry/{name}",  method = RequestMethod.GET)
	public ModelAndView updateCountry(@PathVariable("name") String name) {	
		CountryModel country =  getCountryModelByName(name);
		
		ModelAndView mav = new ModelAndView("Country");
		mav.addObject("country", country);
		return mav;
	}
	
	@RequestMapping("/saveCountryData")
	public String saveCountryData(@ModelAttribute CountryModel country) {
		
		Optional<CountryModel> countryOpt = repo.findAll().stream()
				.filter(e -> e.getName().toLowerCase().equals(country.getName().toLowerCase()))
				.findFirst();
		
		if(countryOpt.isPresent()) {
			repo.delete(countryOpt.get());
		}
		
		repo.save(country);
		return "redirect:/countries";
	}
	
	private CountryModel getCountryModelByName(String name) {
		Optional<CountryModel> countryOpt = repo.findAll().stream()
				.filter(e -> e.getName().toLowerCase().equals(name.toLowerCase()))
				.findFirst();
		
		CountryModel country;
		if(countryOpt.isPresent()) {
			country = countryOpt.get();
		}
		else {
			country = new CountryModel();
		}
		
		return country;
	}
	
}
