package io.cisco.modules.sys.service.impl;

import io.cisco.modules.sys.dao.BxmxDao;
import io.cisco.modules.sys.entity.Bxmx;
import io.cisco.modules.sys.service.BxmxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;



@Service("BxmxService")
public class BxmxServiceImpl implements BxmxService {
    @Autowired
    private BxmxDao bxmxDao;

   @Override
    public  List<Bxmx> queryPassBxmx(Long id){
        List<Bxmx> list=bxmxDao.queryPassBxmx(id);
        return list;
    }

    @Override
    public List<Bxmx> queryNotPassBxmx(Long id) {
        List<Bxmx> list = bxmxDao.queryNotPassBxmx(id);
        return list;
    }

    @Override
    public void update(Long id) {
        System.out.println("bxmxId:"+id);
        bxmxDao.updateBxmxState(id);
    }
    @Override
    public void insert(Bxmx bxmx){
       bxmxDao.insertBxmx(bxmx);
    }

    @Override
    public BigDecimal showSum(Long id)
    {
        BigDecimal b=bxmxDao.showSum(id);
        return  b;
    }
}
