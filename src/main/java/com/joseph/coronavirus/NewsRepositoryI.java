package com.joseph.coronavirus;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joseph.coronavirus.model.NewsModel;

public interface NewsRepositoryI extends JpaRepository<NewsModel, Integer> {

}
