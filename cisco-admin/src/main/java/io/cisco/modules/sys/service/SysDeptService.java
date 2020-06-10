/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.cisco.modules.sys.service;


import io.cisco.modules.sys.entity.SysDeptEntity;

import java.util.List;
import java.util.Map;

/**
 * 部门管理
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface SysDeptService  {

	SysDeptEntity getById(Long id);

	List<SysDeptEntity> queryList(Map<String, Object> map);

	/**
	 * 查询子项目ID列表
	 * @param parentId  上级项目ID
	 */
	List<Long> queryDetpIdList(Long parentId);

	List<Long> queryDetpIdList2(Long parentId);


	/**
	 * 获取子项目ID，用于数据过滤
	 */
	List<Long> getSubDeptIdList(Long deptId);

	void  save(SysDeptEntity sysDeptEntity);

	void  updateById(SysDeptEntity sysDeptEntity);

	void  removeById(Long id);

}
