package com.fnic.controller;

import com.fnic.bean.ReqBean;
import com.fnic.bean.RspBean;
import com.fnic.mybatis.thingsboard.model.AttributeKv;
import com.fnic.mybatis.thingsboard.model.Device;
import com.fnic.service.DeviceService;
import com.fnic.service.PlatformDockService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/device")
public class DeviceController extends BaseController {

    @Resource
    private DeviceService deviceService;

    @Resource
    private PlatformDockService platformDockService;

    @PostMapping("queryDevices")
    public RspBean queryDevices(@RequestBody ReqBean reqData) throws Exception {
        List<Map<String,Object>> list = deviceService.queryDevicesByCustomerId(reqData.getPageBean());

        RspBean rspData = new RspBean();
        rspData.put("list",list);
        return rspData;
    }

    @RequestMapping(value = "addDevice",method = RequestMethod.POST)
    public RspBean addDevice(@RequestBody ReqBean reqData) throws Exception {
        RspBean rspData  = deviceService.addDevice(reqData.getReqParam());

        return rspData;
    }

    @RequestMapping(value = "validDeviceByDC",method = RequestMethod.POST)
    public RspBean validDeviceByDeviceCode(@RequestBody ReqBean reqData) throws Exception {

        RspBean rspData = deviceService.validDeviceByDeviceCode(reqData.getReqParam());

        return rspData;
    }

    @RequestMapping(value = "queryDeviceInfo",method = RequestMethod.POST)
    public RspBean queryDeviceInfo(@RequestBody ReqBean reqData) {

        RspBean rspData = new RspBean();

        return rspData;
    }

    @PostMapping("queryDevicesGC")
    public RspBean queryDevicesGC(@RequestBody ReqBean reqData) throws Exception {
        List<Map<String,Object>> list = platformDockService.queryDevicesGC(reqData);

        RspBean rspData = new RspBean();
        rspData.put("list",list);
        return rspData;
    }
}
