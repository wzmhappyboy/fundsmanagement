/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.cisco.modules.sys.dao;


import io.cisco.modules.sys.entity.SysLogEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 系统日志
 *
 * @author Mark sunlightcs@gmail.com
 */
@Mapper
public interface SysLogDao  {

    List<SysLogEntity> queryPage();

    List<SysLogEntity> getByName(String name);

    void  save(SysLogEntity sysLogEntity);
	
}
