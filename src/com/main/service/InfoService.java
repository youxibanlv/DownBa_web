package com.main.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.main.model.Info;

public interface InfoService {
	List<Info> getInfoListByCateId(int cateId, int pageNO, int pageSize);

	Info getInfoById(int infoId);
	
	int getTotal(int cateId);
}
