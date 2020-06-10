/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.cisco.modules.oss.service.impl;



import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.cisco.modules.oss.entity.SysOssEntity;
import io.cisco.modules.oss.service.SysOssService;
import io.cisco.modules.sys.dao.SysOssDao;
import io.cisco.modules.sys.dao.SysUserDao;
import io.cisco.modules.sys.entity.SysConfigEntity;
import io.cisco.modules.sys.entity.SysUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service("sysOssService")
public class SysOssServiceImpl  implements SysOssService {

	@Autowired
    SysOssDao sysOssDao;

    @Autowired
    SysUserDao sysUserDao;

	@Override
	public List<SysOssEntity> queryPage(Map<String, Object> params) {

			int page = Integer.parseInt(params.get("page").toString());
			int limit = Integer.parseInt(params.get("limit").toString());
			PageHelper.startPage(page,limit);

			List<SysOssEntity> list=sysOssDao.queryPage(params);
			return  list;

	}

	@Override
	public void save(SysOssEntity sysOssEntity){
		sysOssDao.save(sysOssEntity);
	}

	@Override
	public void removeByIds(List<Long > ids){
		sysOssDao.removeByIds(ids);

	}
}
