/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.cisco.modules.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.cisco.common.annotation.DataFilter;
import io.cisco.common.utils.Constant;
import io.cisco.modules.sys.dao.SysRoleDao;
import io.cisco.modules.sys.entity.SysRoleEntity;
import io.cisco.modules.sys.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 角色
 *
 * @author Mark sunlightcs@gmail.com
 */
@Service("sysRoleService")
public class SysRoleServiceImpl  implements SysRoleService {
	@Autowired
	private SysRoleMenuService sysRoleMenuService;
	@Autowired
	private SysRoleDeptService sysRoleDeptService;
	@Autowired
	private SysUserRoleService sysUserRoleService;
	@Autowired
	private SysDeptService sysDeptService;
	@Autowired
	private  SysRoleDao sysRoleDao;

	@Override
	@DataFilter(subDept = true, user = false,tableAlias = "t1")
	public List<SysRoleEntity> queryPage(Map<String, Object> params) {
		if (params.get("roleName")==null||params.get("roleName").equals(""))
		{
			int page = Integer.parseInt(params.get("page").toString());
		int limit = Integer.parseInt(params.get("limit").toString());
		PageHelper.startPage(page,limit);
		List<SysRoleEntity> list=sysRoleDao.queryPage( params);

return  list;
		}
		else {
			String key = (String)params.get("roleName");
			List<SysRoleEntity> list=sysRoleDao.getByName(key);
			return   list;

	}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveRole(SysRoleEntity role) {
		role.setCreateTime(new Date());
		sysRoleDao.save(role);

		//保存角色与菜单关系
		sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());

		//保存角色与部门关系
		sysRoleDeptService.saveOrUpdate(role.getRoleId(), role.getDeptIdList());
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void update(SysRoleEntity role) {
		sysRoleDao.updateById(role);

		//更新角色与菜单关系
		sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());

		//保存角色与部门关系
		sysRoleDeptService.saveOrUpdate(role.getRoleId(), role.getDeptIdList());
	}

	@Override
	public  void deleteByIds(List<Long> longs)
	{
		for (int i=0;i<longs.size();i++)
		{
			sysRoleDao.deleteById(longs.get(i));
		}
	}


	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteBatch(Long[] roleIds) {
		//删除角色
		this.deleteByIds(Arrays.asList(roleIds));

		//删除角色与菜单关联
		sysRoleMenuService.deleteBatch(roleIds);

		//删除角色与部门关联
		sysRoleDeptService.deleteBatch(roleIds);

		//删除角色与用户关联
		sysUserRoleService.deleteBatch(roleIds);
	}

	@Override
	public List<SysRoleEntity> list(){
		return sysRoleDao.list();
	}

@Override
	public  	SysRoleEntity getById(Long id){
		return sysRoleDao.getById(id);
}


}
