package io.cisco.modules.sys.service;

import io.cisco.modules.sys.entity.Bxmx;

import java.math.BigDecimal;
import java.util.List;

public interface BxmxService {
    List<Bxmx> queryPassBxmx(Long id);
    List<Bxmx> queryNotPassBxmx(Long id);
    void update(Long id);
    void  insert(Bxmx bxmx);
    BigDecimal showSum(Long id);
}
