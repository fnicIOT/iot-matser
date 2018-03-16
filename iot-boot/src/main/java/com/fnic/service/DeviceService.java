package com.fnic.service;

import com.fnic.bean.PageBean;

import java.util.List;
import java.util.Map;

public interface DeviceService {

    public List<Map<String,Object>> queryDevicesByCustomerId(PageBean pageBean);
}
