package com.main.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.main.dao.InfoDao;
import com.main.model.Info;
import com.main.service.InfoService;
import com.main.utils.Constance;
import com.main.utils.TextUtils;
@Service
@Transactional
public class InfoServiceImpl implements InfoService {
	@Autowired
	private InfoDao dao;
	@Override
	public List<Info> getInfoListByCateId(int cateId, int pageNO, int pageSize) {
		if (pageNO<1) {
			pageNO = 1;
		}
		if (pageSize == 0) {
			pageSize = Constance.DEFALT_PAGESIZE;
		}
		List<Info> list = dao.getInfoListByCateId(cateId, (pageNO-1)*pageSize, pageSize);
		return list;
	}

	
	@Override
	public Info getInfoById(int infoId) {
//		Info info = new Info();
//		info.setInfo_body(dao.getInfoById(infoId).getInfo_body());
		return dao.getInfoById(infoId);
	}


	@Override
	public int getTotal(int cateId) {
		return dao.getTotal(cateId);
	}

}
