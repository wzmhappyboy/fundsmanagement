/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.cisco.modules.sys.controller;

import io.cisco.modules.sys.entity.Bxmx;
import io.cisco.modules.sys.entity.SysDeptEntity;
import io.cisco.modules.sys.service.BxmxService;
import io.cisco.modules.sys.service.SysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统页面视图
 *
 * @author Mark sunlightcs@gmail.com
 */
@Controller
public class SysPageController {
	@Autowired
	private BxmxService bxmxService;
	@Autowired
	private SysDeptService sysDeptService;

	@RequestMapping("modules/{module}/{url}.html")
	public String module(@PathVariable("module") String module, @PathVariable("url") String url){
		System.out.println("modules/" + module + "/" + url);
		return "modules/" + module + "/" + url;
	}

	@RequestMapping(value = {"/", "index.html"})
	public String index(){
		return "index";
	}

	@RequestMapping("index1.html")
	public String index1(){
		return "index1";
	}

	@RequestMapping("login.html")
	public String login(){
		return "login";
	}

	@RequestMapping("main.html")
	public String main(){
		return "main";
	}

	@RequestMapping("404.html")
	public String notFound(){
		return "404";
	}

	@RequestMapping("druid/sql.html")
	public String druid()
	{return  "http://localhost:8080/druid/index.html";}

	@ResponseBody
	@PostMapping("/management.html")
	public Map<String,Object> manage(@RequestParam("id") String id)
	{
		int id2=Integer.parseInt(id);
		Long l=(long)id2;
		List<Bxmx> list=bxmxService.queryPassBxmx(l);
		List<Bxmx> list2=bxmxService.queryNotPassBxmx(l);
		SysDeptEntity sysDeptEntity=sysDeptService.getById(l);
		//System.out.println(sysDeptEntity.getName());
		String name=sysDeptEntity.getName();
		Map<String,Object> result=new HashMap<>();
		result.put("list",list);
		result.put("list2",list2);
		result.put("name",name);
		return result;

	}
	@ResponseBody
	@PostMapping("/items")
	public Map<String,Object> updateBxmx(@RequestParam("id") String id){
		System.out.println("controller层的id:");
		Long l=(long) Integer.parseInt(id);
		bxmxService.update(l);
		Map<String,Object> map=new HashMap<>();
		map.put("result",1);
		return map;
	}
}
