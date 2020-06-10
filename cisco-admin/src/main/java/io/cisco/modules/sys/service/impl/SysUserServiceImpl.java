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
import io.cisco.modules.sys.dao.SysRoleDao;
import io.cisco.modules.sys.dao.SysUserDao;
import io.cisco.modules.sys.dao.SysUserRoleDao;
import io.cisco.modules.sys.entity.SysUserEntity;
import io.cisco.modules.sys.service.SysDeptService;
import io.cisco.modules.sys.service.SysUserRoleService;
import io.cisco.modules.sys.service.SysUserService;
import io.cisco.modules.sys.shiro.ShiroUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 系统用户
 *
 * @author Mark sunlightcs@gmail.com
 */
@Service("sysUserService")
public class SysUserServiceImpl  implements SysUserService {
	@Autowired
	private SysUserRoleService sysUserRoleService;
	@Autowired
	private SysDeptService sysDeptService;
	@Autowired
	SysUserDao sysUserDao;
	@Autowired
	SysUserRoleDao sysUserRoleDao;


	@Override
	public List<Long> queryAllMenuId(Long userId) {
		return sysUserDao.queryAllMenuId(userId);
	}

	@Override
	@DataFilter(subDept = true, user = false,tableAlias = "t1")
	public List<SysUserEntity> queryPage(Map<String, Object> params) {
		if (params.get("username")==null||params.get("username").equals(""))

			{
			int page = Integer.parseInt(params.get("page").toString());
		int limit = Integer.parseInt(params.get("limit").toString());
		PageHelper.startPage(page,limit);

	List<SysUserEntity> list=sysUserDao.queryPage(params);
	return  list;}
	else{
			String key = (String)params.get("username");

			List<SysUserEntity> list=sysUserDao.getByName(key);
			return list;
	}

	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveUser(SysUserEntity user) {
		user.setCreateTime(new Date());
		//sha256加密
		String salt = RandomStringUtils.randomAlphanumeric(20);
		user.setSalt(salt);
		user.setPassword(ShiroUtils.sha256(user.getPassword(), user.getSalt()));
		sysUserDao.saveUser(user);
		//保存用户与角色关系
		sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void update(SysUserEntity user) {
		if(StringUtils.isBlank(user.getPassword())){
			user.setPassword(null);
		}else{
			SysUserEntity userEntity = sysUserDao.getById(user.getUserId());
			user.setPassword(ShiroUtils.sha256(user.getPassword(), userEntity.getSalt()));
		}
		sysUserDao.update(user);
		
		//保存用户与角色关系
		sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
	}


	@Override
	public boolean updatePassword(Long userId,String password,  String newPassword) {
        SysUserEntity userEntity = sysUserDao.getById(userId);
        if (userEntity.getPassword().equals(password)){
		return sysUserDao.updatePassword(userId,newPassword);}
        else {
        	return false;
		}
    }

    @Override
	public  SysUserEntity getById(Long id){
		return sysUserDao.getById(id);
	}

	@Override
	public void removeByIds(List<Long > ids){
		sysUserDao.removeByIds(ids);
		for (int i=0;i<ids.size();i++)
		{
			sysUserRoleDao.remove(ids.get(i));
		}
	}


}
