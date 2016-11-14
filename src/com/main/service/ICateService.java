package com.main.service;

import java.util.List;


import com.main.model.Category;

public interface ICateService {
	List<Category> getCategorys(int parentId);
}
