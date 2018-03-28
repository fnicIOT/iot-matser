package com.fnic.controller;

import com.fnic.sysframe.config.BaseConstants;
import com.fnic.sysframe.security.SysUser;
import com.fnic.sysframe.utils.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by hjhuang on 2017/5/24.
 */
public class BaseController {

    public Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public BaseConstants baseConstants;

    protected SysUser getUser() {
        return UserUtil.getUser();
    }
}
