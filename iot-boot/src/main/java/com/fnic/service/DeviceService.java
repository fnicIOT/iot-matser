package com.fnic.service;

import com.fnic.bean.PageBean;
import com.fnic.bean.RspBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface DeviceService {

    public List<Map<String,Object>> queryDevicesByCustomerId(PageBean pageBean) throws Exception;

    @Transactional
    public RspBean addDevice(Map<String,Object> param) throws Exception;

    public RspBean validDeviceByDeviceCode(Map param) throws Exception;
}
