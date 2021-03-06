package com.hd.ibus.controller;

import com.hd.ibus.pojo.Station;
import com.hd.ibus.result.DataGridResultInfo;
import com.hd.ibus.service.StationService;
import com.hd.ibus.util.Config;
import com.hd.ibus.util.Page;
import com.hd.ibus.util.PageBean;
import com.hd.ibus.util.PropertiesUtils;
import com.hd.ibus.util.shenw.PageHelp;
import com.hd.ibus.util.shenw.PageStr;
import com.hd.ibus.util.shenw.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by GitHub:thisischina on 2017年7月10日10:25:23.
 * Controller
 * 监测站
 */

@Controller
@RequestMapping("station")
public class StationController {
	@Resource
	private StationService stationService;

	private PageHelp pageHelp=PageHelp.getInstance();

	@RequestMapping("tolist")
	public String toStationList(HttpServletRequest request,Model model,Integer pageNow){
		System.out.println("№tolist");

		if(pageNow!=null&&pageNow==0){
			//初始化
			pageNow = PropertiesUtils.getIntValue(Config.CONFIG, Config.PAGE_NOW);
			Integer pageSize = PropertiesUtils.getIntValue(Config.CONFIG, Config.PAGE_SIZE) ;

			PageBean pageBean=new PageBean();

			pageBean.setPageNow(pageNow);
			pageBean.setPageSize(pageSize);
			pageHelp.setPageBean(pageBean);

			//清除搜索条件
			pageHelp.setSelectStr(null);
			model.addAttribute(pageHelp);
		}else{
			model.addAttribute(pageHelp);
		}

		return "station/station_list";
	}

	@RequestMapping("toadd")
	public String toAddStation(HttpServletRequest request,Model model){
		System.out.println("№station_list");
		return "station/station_add";
	}

	@RequestMapping("toupdate")
	public String toUpdate(HttpServletRequest request,Model model,Integer id){
		System.out.println("№toupdate");

		Station s=new Station();
		s.setId(id);//存储更新记录所在页数
		pageHelp.setObject(s);

		Station Station=stationService.selectByKey(pageHelp);
		pageHelp.setObject(Station);

		model.addAttribute(Station);
		model.addAttribute(pageHelp);

		return "station/station_update";
	}

	@RequestMapping("update")
	public @ResponseBody int update(HttpServletRequest request,HttpServletResponse response,Model model)
			throws IOException {
		System.out.println("№:update");

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("utf-8");

		String id= PageStr.getParameterStr("id",request,model);
		String name= PageStr.getParameterStr("name",request,model);
		String address= PageStr.getParameterStr("address",request,model);
		String type= PageStr.getParameterStr("type",request,model);
		String coordinate= PageStr.getParameterStr("coordinate",request,model);
		String unitId= PageStr.getParameterStr("unitId",request,model);

		/**
		 * 查询条件为空设置对象为空
		 * 查询条件不为空，将参数设置到对象
		 */
		Station station=new Station();
		station.setId(Integer.parseInt(id));
		if(!name.equals("")){
			station.setName(name);
		}if(!address.equals("")){
			station.setAddress(address);
		}if(!type.equals("")){
			station.setType(Integer.parseInt(type));
		}if(!coordinate.equals("")){
			station.setCoordinate(coordinate);
		}if(!unitId.equals("")){
			station.setUnitId(Integer.parseInt(unitId));
		}
		stationService.updateStation(station);

		model.addAttribute(pageHelp);

		return  Value.IntNumOne;
	}

	/**
	 * 带可查询的分页列表
	 * @param request
	 * @param pageNow
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("getlist")
	public @ResponseBody DataGridResultInfo getSelectListPage(HttpServletRequest request,HttpServletResponse response,Integer pageNow,Model model)
			throws IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("utf-8");

		String selectStr= PageStr.getParameterStr("name",request,model);

		/**
		 * 查询条件为空设置对象为空
		 * 查询条件不为空，将参数设置到对象
		 */
		Station station=new Station();
		if(!selectStr.equals("")){
			station.setName(selectStr);

			pageHelp.setObject(station);
			pageHelp.setSelectStr(selectStr);
			model.addAttribute(pageHelp);
		}else {
			pageHelp.setObject(null);
	}
		return stationService.findList(pageHelp,pageNow);
	}

	/**
	 * 确认是否存在同一账号
	 * @param request
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("confirmexist")
	public @ResponseBody DataGridResultInfo confirmExist(HttpServletRequest request,Model model)
			throws IOException {
		String name= PageStr.getParameterStr("name",request,model);

		/**
		 * 查询条件为空设置对象为空
		 * 查询条件不为空，将参数设置到对象
		 */
		Station Station;
		if(!name.equals("")){
			Station=new Station();
			Station.setName(name);
			pageHelp.setObject(Station);
		}else {
			pageHelp.setObject(null);
		}

		return stationService.getNameCount(pageHelp);
	}

	@ResponseBody
	@RequestMapping("addstation")
	public int addStation(Station station,HttpServletRequest request,Model model){
		String name= PageStr.getParameterStr("name",request,model);
		String address= PageStr.getParameterStr("address",request,model);
		String type= PageStr.getParameterStr("type",request,model);
		String coordinate= PageStr.getParameterStr("coordinate",request,model);
		String unitId= PageStr.getParameterStr("unitId",request,model);

		if(!name.equals("")){
			station.setName(name);
		}if(!address.equals("")){
			station.setAddress(address);
		}if(!type.equals("")){
			station.setType(Integer.parseInt(type));
		}if(!coordinate.equals("")){
			station.setCoordinate(coordinate);
		}if(!unitId.equals("")){
			station.setUnitId(Integer.parseInt(unitId));
		}
		stationService.insertStation(station);

		return Value.IntNumOne;
	}


	@ResponseBody
	@RequestMapping("delete")
	public int deleteStation(HttpServletRequest request,Model model){
		String id=PageStr.getParameterStr("id",request,model);
		stationService.deleteStation(Integer.parseInt(id));

		return Value.IntNumOne;
	}
}
