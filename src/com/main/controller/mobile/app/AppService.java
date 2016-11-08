package com.main.controller.mobile.app;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.main.controller.AdminController;
import com.main.model.HomeBean;
import com.main.model.PageBean;
import com.main.model.Recommend;
import com.main.model.mobile.HttpConstance;
import com.main.model.mobile.request.HomeBeanReq;
import com.main.model.mobile.request.RecommendReq;
import com.main.model.mobile.response.HomeBeanRsp;
import com.main.model.mobile.response.RecommendRsp;
import com.main.service.IAppService;
import com.main.service.IHomeBeanService;
import com.main.service.IRecommendService;
import com.main.service.ISubjectService;
import com.main.utils.HttpUtils;
import com.main.utils.NumberUtil;

@Controller
@RequestMapping(value = "appService")
public class AppService {
	private static Logger log = LoggerFactory.getLogger(AdminController.class);// 日志记录
	@Autowired
	private IAppService appService;
	@Autowired
	private IRecommendService recommendService;
	@Autowired
	private IHomeBeanService homeBeanService;
	@Autowired
	private ISubjectService subjectService;

	@RequestMapping(value="getHomeBeans")
	public void getHomeBeans(@RequestBody HomeBeanReq req,HttpServletResponse response){
		HomeBeanRsp rsp = null;
		if (req != null) {
			rsp = new HomeBeanRsp();
			HomeBeanReq.RequestParam param = req.requestParams;
			int pageNo = param.pageNo;
			int pagesize = param.pageSize;
			List<HomeBean> beans = homeBeanService.getList(pageNo, pagesize, -1);
			if (beans!= null && beans.size()>0) {
				for(HomeBean bean :beans){
					if (bean.getHomeBeanType() == HomeBean.TYPE_SUBJECT) {
						bean.setSubject(subjectService.getBeanById(NumberUtil.parseToInt(bean.getAppIds())));
					}else{
						bean.setApps(appService.getAppByAppIdStr(bean.getAppIds()));
					}
				}
				int total = homeBeanService.getTotalHomeBean(-1);
				PageBean pageBean = HttpUtils.getPageBean(pageNo, pagesize,total );
				rsp.resultData.homeBeans = beans;
				rsp.resultData.pageBean = pageBean;
				rsp.result = HttpConstance.HTTP_SUCCESS;
			}else{
				rsp.failReason = "没有查询到相关资源";
			}
		}else{
			// 请求参数错误
			rsp = new HomeBeanRsp();
			rsp.failReason = "请求参数错误";
		}
		HttpUtils.sendRsp(response, rsp);
	}

	@RequestMapping(value ="getRecommend.do")
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
		HttpUtils.sendRsp(response, rsp);

	}
}
