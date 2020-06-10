/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.cisco.modules.sys.service;

import com.github.pagehelper.PageInfo;
import io.cisco.modules.sys.entity.SysDictEntity;

import java.util.List;
import java.util.Map;

/**
 * 数据字典
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface SysDictService  {

    PageInfo<SysDictEntity> queryPage(Map<String, Object> params);

    SysDictEntity getById(Long id);

    void save(SysDictEntity sysDictEntity);

    void updateById(SysDictEntity sysDictEntity);

    void removeByIds(List<Long> longs);

    void deleteById (Long id);
}

