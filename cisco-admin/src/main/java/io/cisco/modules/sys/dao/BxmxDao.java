package io.cisco.modules.sys.dao;

import io.cisco.modules.sys.entity.Bxmx;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BxmxDao
{

    List<Bxmx> queryPassBxmx(Long id);
    List<Bxmx> queryNotPassBxmx(Long id);
    void updateBxmxState(Long id);
    void insertBxmx(Bxmx bxmx);
}
