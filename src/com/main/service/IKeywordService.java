package com.main.service;

import java.util.List;

import com.main.model.Keyword;

public interface IKeywordService {
	List<Keyword> getDefault(String key,int num);
	boolean add(String keyword);
}
