/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.cisco.modules.sys.dao;

import io.cisco.modules.sys.entity.SysRoleEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 角色管理
 *
 * @author Mark sunlightcs@gmail.com
 */
@Mapper
public interface SysRoleDao  {
    List<SysRoleEntity> list();

    SysRoleEntity getById(Long id);

    List<SysRoleEntity> queryPage(Map<String, Object> params);

    List<SysRoleEntity> getByName(String name);

    void  save(SysRoleEntity sysRoleEntity);

    void  updateById(SysRoleEntity sysRoleEntity);

    void deleteById(Long id);

}
