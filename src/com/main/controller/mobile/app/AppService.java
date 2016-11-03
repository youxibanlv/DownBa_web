package com.main.controller.mobile.app;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.main.controller.AdminController;
import com.main.model.Recommend;
import com.main.model.mobile.HttpConstance;
import com.main.model.mobile.request.RecommendReq;
import com.main.model.mobile.response.RecommendRsp;
import com.main.service.IAppService;
import com.main.service.IRecommendService;
import com.main.utils.NumberUtil;
import com.sun.org.apache.bcel.internal.generic.NEW;

import gson.Gson;

@Controller
@RequestMapping(value = "appService")
public class AppService {
	private static Logger log = LoggerFactory.getLogger(AdminController.class);// 日志记录
	@Autowired
	private IAppService appService;
	@Autowired
	private IRecommendService recommendService;

	private Gson gson = new Gson();

	@RequestMapping(value = "getRecommend.do")
	public void getRecommend(@RequestBody RecommendReq recommendReq, HttpServletResponse response) {
		RecommendRsp rsp = null;
		if (recommendReq != null) {
			rsp = new RecommendRsp(recommendReq);
			RecommendReq.RequestParam param = recommendReq.requestParams;
			int recommendType = NumberUtil.parseToInt(param.recommend_type);
			if (recommendType != -1) {
				List<Recommend> list = recommendService.getRecommendListByType(recommendType);
				if (list != null && list.size() > 0) {
					rsp.resultData.recommends = list;
					rsp.result = HttpConstance.HTTP_SUCCESS;
				} else {
					rsp.failReason = "没有查询到相关推荐信息，recommend_type =" + recommendType;
				}
			} else {
				rsp.failReason = "推荐分类为不正确，recommend_type =" + param.recommend_type;
			}
		} else {
			// 请求参数错误
			rsp = new RecommendRsp();
			rsp.failReason = "请求参数错误";
			rsp.result = 1;
		}
		try {
			response.setCharacterEncoding("UTF-8");
			PrintWriter writer = response.getWriter();
			writer.write(gson.toJson(rsp));
			System.out.println("*************************");
			System.out.println(gson.toJson(rsp));
			System.out.println("*************************");
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
