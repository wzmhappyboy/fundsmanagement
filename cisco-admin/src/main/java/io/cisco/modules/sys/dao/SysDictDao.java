/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.cisco.modules.sys.dao;

import io.cisco.modules.sys.entity.SysDictEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 数据字典
 *
 * @author Mark sunlightcs@gmail.com
 */
@Mapper
public interface SysDictDao  {

    List<SysDictEntity> queryPage();

    SysDictEntity getById(Long id);

    List<SysDictEntity> getByName(String name);

    void  save(SysDictEntity sysDictEntity);

    void updateById(SysDictEntity sysDictEntity);

    void deleteById(Long id);
	
}
