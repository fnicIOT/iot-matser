package com.fnic.controller;

import com.fnic.bean.ReqBean;
import com.fnic.bean.RspBean;
import com.fnic.mybatis.iot.model.TUser;
import com.fnic.mybatis.thingsboard.model.Customer;
import com.fnic.mybatis.thingsboard.model.TbUser;
import com.fnic.service.CustomerService;
import com.fnic.sysframe.security.SysUser;
import com.google.common.collect.Maps;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hjhuang on 2017/5/17.
 */
@RestController
public class LoginController extends BaseController {

    @Resource
    private CustomerService customerService;

    @RequestMapping("/logout/success")
    public RspBean logoutSuccess() throws Exception {

        RspBean rspBean = new RspBean();
        rspBean.setRspDesc("Logout Success");

        return rspBean;
    }

    @RequestMapping("/register")
    public RspBean register(@RequestBody ReqBean reqBean) throws Exception {

        RspBean rspBean = customerService.regCustomer(reqBean.getReqParam());

        return rspBean;
    }

    @RequestMapping("/wchatLogin")
    public RspBean test(@RequestBody ReqBean reqBean) throws Exception {

        RspBean rspBean = new RspBean();

        List<TUser> list = customerService.queryUser();
        rspBean.put("list",list);

        return rspBean;
    }

}
