package com.main.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.main.model.Info;

public interface InfoDao {
	
	List<Info> getInfoListByCateId(@Param("cateId") int cateId,@Param("pageNO")int pageNO,@Param("pageSize")int pageSize);
	
	int getTotal(@Param("cateId")int cateId);
	
	Info getInfoById(@Param("infoId") int infoId);
}
