package com.main.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.main.model.HomeBean;

public interface HomeBeanDao {
	List<HomeBean> getList(@Param("pageno") int pageno, @Param("pagesize") int pagesize,@Param("homeBeanType") int homeBeanType);

	int getTotalHomeBean(@Param("homeBeanType")int homeBeanType);

	List<HomeBean> getListByTitle(@Param("homeBeanTitle") String title);

	List<HomeBean> getListByType(@Param("homeBeanType") int homeBeanType);

	HomeBean getBeanById(@Param("id") int id);

	boolean addHomeBean(@Param("homeBean") HomeBean bean);

	boolean deleteHomeBean(@Param("id") int id);

	boolean updateHomeBean(@Param("id") HomeBean bean);

}
