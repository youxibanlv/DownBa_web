package com.main.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.main.model.HomeBean;

public interface IHomeBeanService {
	List<HomeBean> getList(int pageno,int pagesize,int homeBeanType);

	int getTotalHomeBean(int homeBeanType);

	List<HomeBean> getListByTitle(@Param("homeBeanTitle") String title);

	List<HomeBean> getListByType(@Param("homeBeanType") int homeBeanType);

	HomeBean getBeanById(@Param("homeBeanId") int id);

	boolean addHomeBean(@Param("homeBean") HomeBean bean);

	boolean deleteHomeBean(@Param("homeBeanId") int id);

	boolean updateHomeBean(@Param("homeBeanId") HomeBean bean);
}
