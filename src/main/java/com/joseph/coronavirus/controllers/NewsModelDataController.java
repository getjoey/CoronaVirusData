package com.joseph.coronavirus.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.joseph.coronavirus.NewsRepositoryI;
import com.joseph.coronavirus.model.NewsModel;

@Controller
public class NewsModelDataController {

	@Autowired
	NewsRepositoryI repo;
	
	@RequestMapping("/news")
	public ModelAndView getStudents() {
		ModelAndView mav = new ModelAndView("News/NewsList"); //html name
		
		List<NewsModel> newsList = repo.findAll();
		
		mav.addObject("newsList", newsList); //object name in html
		return mav;
	}
	
	@RequestMapping("/createNewsPost") //FORM
	public ModelAndView updateCountry(@ModelAttribute NewsModel news) {
		ModelAndView mav = new ModelAndView("News/CreateNewsPost");
		mav.addObject("news", news);
		return mav;
	}
	
	@RequestMapping("/saveNewsPost") //SAVE triggered by form
	public String saveNewsPost(@ModelAttribute NewsModel newsModel) {
		
		Optional<NewsModel> countryOpt = repo.findAll().stream()
				.filter(e -> e.getTitle().toLowerCase().equals(newsModel.getTitle().toLowerCase()))
				.findFirst();
		
		if(countryOpt.isPresent()) {
			repo.delete(countryOpt.get());
		}
		
		repo.save(newsModel);
		return "redirect:/news";
	}
}
