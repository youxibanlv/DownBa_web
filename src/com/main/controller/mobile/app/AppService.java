package com.main.controller.mobile.app;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.deploy.LoginConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.main.model.App;
import com.main.model.Category;
import com.main.model.Comment;
import com.main.model.HomeBean;
import com.main.model.Info;
import com.main.model.PageBean;
import com.main.model.Recommend;
import com.main.model.Subject;
import com.main.model.mobile.HttpConstance;
import com.main.model.mobile.request.AddCommentReq;
import com.main.model.mobile.request.AppDetailsReq;
import com.main.model.mobile.request.GetAppByCateIdReq;
import com.main.model.mobile.request.GetCategoryReq;
import com.main.model.mobile.request.HomeBeanReq;
import com.main.model.mobile.request.InfoReq;
import com.main.model.mobile.request.RecommendReq;
import com.main.model.mobile.request.SubjectReq;
import com.main.model.mobile.response.AddCommentRsp;
import com.main.model.mobile.response.AppDetailsRsp;
import com.main.model.mobile.response.GetAppListRsp;
import com.main.model.mobile.response.GetCategoryRsp;
import com.main.model.mobile.response.HomeBeanRsp;
import com.main.model.mobile.response.InfoRsp;
import com.main.model.mobile.response.RecommendRsp;
import com.main.model.mobile.response.SubjectRsp;
import com.main.service.IAppService;
import com.main.service.ICateService;
import com.main.service.ICommentService;
import com.main.service.IHomeBeanService;
import com.main.service.IRecommendService;
import com.main.service.ISubjectService;
import com.main.service.InfoService;
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
	@Autowired
	private ICommentService commentService;
	@Autowired
	private InfoService infoService;
	
	//获取专题列表
	@RequestMapping(value="getSubject.do")
	public void getSubject(@RequestBody SubjectReq req,HttpServletResponse response){
		SubjectRsp rsp = null;
		if (req!= null) {
			rsp = new SubjectRsp(req);
			int pageNo = req.requestParams.pageNo;
			int pageSize =  req.requestParams.pageSize;
			List<Subject> list = subjectService.getList(pageNo,pageSize);
			rsp.resultData.subjects=list;
			rsp.result=0;
			if (pageNo == 0 || pageNo == 1) {
				PageBean pageBean = HttpUtils.getPageBean(pageNo, pageSize, subjectService.getTotal());
				rsp.resultData.pageBean = pageBean;
			}
		}else{
			rsp = new SubjectRsp();
			rsp.failReason="请求参数错误";
		}
		HttpUtils.sendRsp(response, rsp);
	}
	//获取资讯列表
	@RequestMapping(value="getInfoList.do")
	public void getInfoList(@RequestBody InfoReq req,HttpServletResponse response){
		InfoRsp rsp = null;
		if (req!= null) {
			rsp = new InfoRsp(req);
			//业务逻辑
			int cate_id = req.requestParams.cate_id;
			int pageNo = req.requestParams.pageNo;
			int pageSize = req.requestParams.pageSize;
			if (pageNo == 0 || pageNo == 1) {
				int total = infoService.getTotal(cate_id);
				PageBean pageBean = HttpUtils.getPageBean(pageNo, pageSize, total);
				rsp.resultData.pageBean = pageBean;
			}
			List<Info> list = infoService.getInfoListByCateId(cate_id, pageNo, pageSize);
			if (list!= null && list.size()>0) {
				rsp.result= HttpConstance.HTTP_SUCCESS;
				rsp.resultData.infoList = list;
			}else{
				rsp.failReason="没有查询到相关信息";
			}
		}else{
			rsp = new InfoRsp();
			rsp.failReason="请求参数错误";
		}
		HttpUtils.sendRsp(response, rsp);
	}
	
	
	//发表评论
	@RequestMapping(value="addComment.do")
	public void addComment(@RequestBody AddCommentReq req,HttpServletResponse response){
		AddCommentRsp rsp = null;
		if (req != null) {
			rsp = new AddCommentRsp(req);
			Comment comment = req.requestParams.comment;
			if (comment != null) {
				if (commentService.add(comment)) {
					rsp.result = HttpConstance.HTTP_SUCCESS;
				}else{
					rsp.failReason = "发表失败";
				}
				List<Comment> list = commentService.getListByAppId(String.valueOf(comment.getId()));
				rsp.resultData.list=list;
			}else{
				rsp.failReason="comment == null";
			}
		}else {
			rsp= new AddCommentRsp();
			rsp.failReason="请求参数错误";
		}
		HttpUtils.sendRsp(response, rsp);
	}
	
	//查询app的截图列表和评论列表
	@RequestMapping(value="getAppDetails.do")
	public void getAppDetails(@RequestBody AppDetailsReq req,HttpServletResponse response){
		AppDetailsRsp rsp = null;
		if (req != null) {
			rsp = new AppDetailsRsp(req);
			App app = appService.getAppDetails(req.requestParams.app_id);
			rsp.resultData.app = app;
			rsp.result=HttpConstance.HTTP_SUCCESS;
		}else{
			rsp = new AppDetailsRsp();
			rsp.failReason="请求参数错误";
		}
		HttpUtils.sendRsp(response, rsp);
	}
//	根据分类查询app列表
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
//	获取分类列表
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
//获取首页模块
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
//获取推荐内容
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
		}
		HttpUtils.sendRsp(response, rsp);

	}
}
