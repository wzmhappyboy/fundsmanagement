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
import io.cisco.modules.sys.dao.SysDictDao;
import io.cisco.modules.sys.entity.SysDictEntity;
import io.cisco.modules.sys.service.SysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("sysDictService")
public class SysDictServiceImpl  implements SysDictService {
@Autowired
SysDictDao sysDictDao;

    @Override
    public PageInfo<SysDictEntity> queryPage(Map<String, Object> params) {
        if (params.get("name")==null||params.get("name").equals(""))
        {
        int page = Integer.parseInt(params.get("page").toString());
        int limit = Integer.parseInt(params.get("limit").toString());
        PageHelper.startPage(page,limit);

        List<SysDictEntity> list=sysDictDao.queryPage();

        return new PageInfo<>(list);}
        else {

            String key = (String)params.get("name");
            List<SysDictEntity> list=sysDictDao.getByName(key);
            return new PageInfo<>(list);}

    }



    @Override
    public  SysDictEntity getById(Long id){
        return sysDictDao.getById(id);
    }

    @Override
    public  void save(SysDictEntity sysDictEntity)
    {
        sysDictDao.save(sysDictEntity);
    }

    @Override
    public  void  updateById(SysDictEntity sysDictEntity)
    {
        sysDictDao.updateById(sysDictEntity);
    }

    @Override
    public  void  deleteById(Long id){sysDictDao.deleteById(id);}

    @Override
    public  void  removeByIds(List<Long> longs){
        for (int i=0;i<longs.size();i++)
        {
            this.deleteById(longs.get(i));

        }
    }

}
