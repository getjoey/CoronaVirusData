package com.joseph.coronavirus;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joseph.coronavirus.model.CountryModel;

public interface CountryRepositoryI extends JpaRepository<CountryModel, String> {

}
