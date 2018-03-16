package com.fnic.bean;

import java.util.Map;

public class ReqBean {

    private PageBean pageBean;

    private Map reqParam;

    public PageBean getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean pageBean) {
        this.pageBean = pageBean;
    }

    public Map getReqParam() {
        return reqParam;
    }

    public void setReqParam(Map reqParam) {
        this.reqParam = reqParam;
    }
}
