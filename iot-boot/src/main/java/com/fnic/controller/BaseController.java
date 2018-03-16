package com.fnic.controller;

import com.fnic.sysframe.security.SysUser;
import com.fnic.sysframe.utils.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by hjhuang on 2017/5/24.
 */
public class BaseController {
    public Logger logger = LoggerFactory.getLogger(this.getClass());

    protected SysUser getUser() {
        return UserUtil.getUser();
    }
}
