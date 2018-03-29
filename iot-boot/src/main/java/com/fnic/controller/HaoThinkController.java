package com.fnic.controller;

import com.fnic.service.PlatformDockService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping(value = "/haothink")
public class HaoThinkController extends BaseController {

    @Resource
    private PlatformDockService platformDockService;

    @RequestMapping(value = "addDevice",method = RequestMethod.POST)
    public Map<String,Object> addDevice(@RequestBody Map<String,Object> param) throws Exception {
        Map<String,Object> rspMap = platformDockService.addDevice(param,baseConstants.defaultHaoThinkTenantId,
                baseConstants.defaultHaoThinkCustomerId,"HT");

        return rspMap;
    }

}
