/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.cisco.modules.sys.service.impl;

import io.cisco.common.annotation.DataFilter;
import io.cisco.modules.sys.dao.SysDeptDao;
import io.cisco.modules.sys.entity.SysDeptEntity;
import io.cisco.modules.sys.service.SysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service("sysDeptService")
public class SysDeptServiceImpl  implements SysDeptService {
	@Autowired
	SysDeptDao sysDeptDao;


	@Override
	@DataFilter(subDept = true, user = false, tableAlias = "t1")
	public List<SysDeptEntity> queryList(Map<String, Object> params){
		return sysDeptDao.queryList(params);
	}

	@Override
	public List<Long> queryDetpIdList(Long parentId) {
		return sysDeptDao.queryDetpIdList(parentId);
	}

	@Override
	public List<Long> queryDetpIdList2(Long parentId) {
		return sysDeptDao.queryDetpIdList2(parentId);
	}


	@Override
	public List<Long> getSubDeptIdList(Long deptId){
		//部门及子部门ID列表
		List<Long> deptIdList = new ArrayList<>();

		//获取子部门ID
		List<Long> subIdList = queryDetpIdList(deptId);
		getDeptTreeList(subIdList, deptIdList);

		return deptIdList;
	}

	/**
	 * 递归
	 */
	private void getDeptTreeList(List<Long> subIdList, List<Long> deptIdList){
		for(Long deptId : subIdList){
			List<Long> list = queryDetpIdList(deptId);
			if(list.size() > 0){
				getDeptTreeList(list, deptIdList);
			}

			deptIdList.add(deptId);
		}
	}

	@Override
	public  SysDeptEntity getById(Long id){
		return sysDeptDao.getById(id);
	}

	@Override
	public  void  save(SysDeptEntity sysDeptEntity){
		sysDeptDao.save(sysDeptEntity);
	}

	@Override
	public void  updateById(SysDeptEntity sysDeptEntity){
		sysDeptDao.updateById(sysDeptEntity);
	}

	@Override
	public  void  removeById(Long id){
		sysDeptDao.removeById(id);
	}


}
