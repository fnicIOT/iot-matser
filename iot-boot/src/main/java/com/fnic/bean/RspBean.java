package com.fnic.bean;

import java.util.HashMap;


public class RspBean extends HashMap {

    public static String ERROR_CODE = "9999";

    public static String SUCCESS_CODE = "0000";

    public RspBean() {
        this.setRspCode(SUCCESS_CODE);
        this.setRspDesc("success");
    }

    public void setRspCode(String rspCode) {
        this.put("rspCode",rspCode);
    }

    public void setRspDesc(String rspDesc) {
        this.put("rspDesc",rspDesc);
    }
}
