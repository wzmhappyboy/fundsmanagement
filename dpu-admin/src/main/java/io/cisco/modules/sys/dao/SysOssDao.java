/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.cisco.modules.sys.dao;

import io.cisco.modules.oss.entity.SysOssEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 文件上传
 *
 * @author Mark sunlightcs@gmail.com
 */
@Mapper
public interface SysOssDao {

    List<SysOssEntity> queryPage(Map<String, Object> params);

    void save(SysOssEntity sysOssEntity);

    void removeByIds(List<Long > ids);

}
