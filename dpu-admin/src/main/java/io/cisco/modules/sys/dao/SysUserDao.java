/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.cisco.modules.sys.dao;

import io.cisco.modules.oss.entity.SysOssEntity;
import io.cisco.modules.sys.entity.SysUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 系统用户
 *
 * @author Mark sunlightcs@gmail.com
 */
@Mapper
public interface SysUserDao {
	
	/**
	 * 查询用户的所有权限
	 * @param userId  用户ID
	 */
	List<String> queryAllPerms(Long userId);
	
	/**
	 * 查询用户的所有菜单ID
	 */
	List<Long> queryAllMenuId(Long userId);


	/**
	 * 保存用户
	 */
	void saveUser(SysUserEntity user);

	/**
	 * 修改用户
	 */
	void update(SysUserEntity user);

	/**
	 * 修改密码
	 * @param userId       用户ID
	 * @param newPassword  新密码
	 */
	boolean updatePassword(@Param("id") Long userId,  @Param("newPassword") String newPassword);


	//返回指定用户
	SysUserEntity getById(Long id);

	//查询用户是否存在
	SysUserEntity selectOne(@Param("username") String username);

	//模糊查询
	List<SysUserEntity> getByName(String  name);

	void removeByIds(List<Long > ids);

	List<SysUserEntity> queryPage(Map<String, Object> params);


	List<SysOssEntity> queryPage2();

}
