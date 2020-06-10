/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.cisco.modules.sys.service;


import com.github.pagehelper.PageInfo;
import io.cisco.modules.sys.entity.SysRoleEntity;

import java.util.List;
import java.util.Map;


/**
 * 角色
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface SysRoleService  {


	void saveRole(SysRoleEntity role);

	void update(SysRoleEntity role);
	
	void deleteBatch(Long[] roleIds);

	List<SysRoleEntity> list();

	SysRoleEntity getById(Long id);

	List<SysRoleEntity> queryPage(Map<String, Object> params);

	void deleteByIds(List<Long> longs);
}
