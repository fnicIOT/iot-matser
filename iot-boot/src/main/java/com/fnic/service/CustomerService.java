package com.fnic.service;

import com.fnic.bean.RspBean;
import com.fnic.mybatis.iot.model.TUser;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface CustomerService {

    @Transactional
    public RspBean regCustomer(Map<String,Object> param);

    @Transactional
    public List<TUser> queryUser();
}
