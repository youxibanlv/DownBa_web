package com.main.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.main.model.HomeBean;
import com.main.model.Subject;

public interface SubjectDao {

	List<Subject> getList(@Param("pageno") int pageno, @Param("pagesize") int pagesize);

	int getTotal();

	List<Subject> getListByTitle(@Param("title") String title);

	Subject getBeanById(@Param("id") int id);

	boolean add(@Param("subject") Subject bean);

	boolean delete(@Param("id") int id);

	boolean update(@Param("id") HomeBean bean);
}
