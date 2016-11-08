package com.main.service;

import java.util.List;

import com.main.model.HomeBean;
import com.main.model.Subject;

public interface ISubjectService {
	List<Subject> getList(int pageno, int pagesize);

	int getTotal();

	List<Subject> getListByTitle( String title);


	Subject getBeanById( int id);

	boolean add(Subject bean);

	boolean delete( int id);

	boolean update( HomeBean bean);
}
