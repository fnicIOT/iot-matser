package com.fnic.service;

import com.fnic.bean.PageBean;
import com.fnic.bean.ReqBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface PlatformDockService {

    @Transactional
    public Map<String,Object> addDevice(Map<String, Object> param,String tenantId,String customerId,String prefix) throws Exception;

    public List<Map<String,Object>> queryDevicesGC(ReqBean reqBean) throws Exception;
}
