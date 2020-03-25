package com.joseph.coronavirus.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CountryModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private int cases;
	private int deaths;
	private int recovered;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCases() {
		return cases;
	}
	public void setCases(int cases) {
		this.cases = cases;
	}
	public int getDeaths() {
		return deaths;
	}
	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}
	public int getRecovered() {
		return recovered;
	}
	public void setRecovered(int recovered) {
		this.recovered = recovered;
	}
	
	
}
