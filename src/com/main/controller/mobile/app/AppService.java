package com.main.controller.mobile.app;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.main.model.App;
import com.main.model.Category;
import com.main.model.HomeBean;
import com.main.model.PageBean;
import com.main.model.Recommend;
import com.main.model.mobile.BaseResponse;
import com.main.model.mobile.HttpConstance;
import com.main.model.mobile.request.AppDetailsReq;
import com.main.model.mobile.request.GetAppByCateIdReq;
import com.main.model.mobile.request.GetCategoryReq;
import com.main.model.mobile.request.HomeBeanReq;
import com.main.model.mobile.request.RecommendReq;
import com.main.model.mobile.response.AppDetailsRsp;
import com.main.model.mobile.response.GetAppListRsp;
import com.main.model.mobile.response.GetCategoryRsp;
import com.main.model.mobile.response.HomeBeanRsp;
import com.main.model.mobile.response.RecommendRsp;
import com.main.service.IAppService;
import com.main.service.ICateService;
import com.main.service.IHomeBeanService;
import com.main.service.IRecommendService;
import com.main.service.ISubjectService;
import com.main.utils.HttpUtils;
import com.main.utils.NumberUtil;

@Controller
@RequestMapping(value = "appService")
public class AppService {
//	private static Logger log = LoggerFactory.getLogger(AdminController.class);// 日志记录
	@Autowired
	private IAppService appService;
	@Autowired
	private IRecommendService recommendService;
	@Autowired
	private IHomeBeanService homeBeanService;
	@Autowired
	private ISubjectService subjectService;
	@Autowired
	private ICateService cateService;
	
	@RequestMapping(value="getAppDetails.do")
	public void getAppDetails(@RequestBody AppDetailsReq req,HttpServletResponse response){
		AppDetailsRsp rsp = null;
		if (req != null) {
			rsp = new AppDetailsRsp(req);
			
		}else{
			rsp = new AppDetailsRsp();
			rsp.failReason="请求参数错误";
		}
		HttpUtils.sendRsp(response, rsp);
	}
	
	@RequestMapping(value="getAppListByCate.do")
	public void getAppListByCate(@RequestBody GetAppByCateIdReq req,HttpServletResponse response){
		GetAppListRsp rsp =null;
		if (req!= null) {
			rsp = new GetAppListRsp(req);
			Integer cate_id=req.requestParams.cate_id;
			Integer orderType = req.requestParams.orderType;
			Integer pageNO = req.requestParams.pageNo;
			Integer pageSize = req.requestParams.pageSize;
			List<App> list=appService.getAppListByCateId(orderType, cate_id, pageNO, pageSize);
			if (list!= null && list.size()>0) {
				rsp.result = 0;
				rsp.resultData.appList = list;
				if (pageNO < 2) {
					PageBean pageBean = HttpUtils.getPageBean(pageNO, pageSize, appService.getTotalApp(cate_id));
					rsp.resultData.pageBean = pageBean;
				}
			}else{
				rsp.failReason = "没有查询到相关数据";
			}
			
		}else{
			rsp = new GetAppListRsp();
			rsp.failReason="请求参数错误";
		}
		HttpUtils.sendRsp(response, rsp);
	}
	
	@RequestMapping(value="getCateByParentId.do")
	public void getCateByParentId(@RequestBody GetCategoryReq req,HttpServletResponse response){
		GetCategoryRsp rsp = null;
		if (req != null) {
			rsp = new GetCategoryRsp(req);
			Integer parentId = req.requestParams.parentId;
			if (parentId != null) {
				List<Category> list = cateService.getCategorys(parentId);
				if (list!= null) {
					rsp.result=0;
					rsp.resultData.list=list;
				}else{
					rsp.failReason="没有查询到相关数据";
				}
			}
		}else{
			rsp = new GetCategoryRsp();
			rsp.failReason = "请求参数错误";
		}
		HttpUtils.sendRsp(response, rsp);
	}

	@RequestMapping(value="getHomeBeans.do")
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
					for(Recommend recommend:list){
						recommend.setApp(appService.getAppByAppId(recommend.getAppId()));
					}
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
		}
		HttpUtils.sendRsp(response, rsp);

	}
}
