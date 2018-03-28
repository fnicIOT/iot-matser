package com.fnic.service.impl;

import com.datastax.driver.core.utils.UUIDs;
import com.fnic.bean.PageBean;
import com.fnic.bean.RspBean;
import com.fnic.mybatis.thingsboard.dao.AttributeKvMapper;
import com.fnic.mybatis.thingsboard.dao.DeviceCredentialsMapper;
import com.fnic.mybatis.thingsboard.dao.DeviceMapper;
import com.fnic.mybatis.thingsboard.model.*;
import com.fnic.service.BaseService;
import com.fnic.service.DeviceService;
import com.fnic.sysframe.config.BaseConstants;
import com.fnic.sysframe.security.SysUser;
import com.fnic.sysframe.utils.DateUtil;
import com.fnic.sysframe.utils.UUIDConverter;
import com.fnic.sysframe.utils.UserUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DeviceServiceImpl extends BaseService implements DeviceService {

    @Resource
    private DeviceMapper deviceMapper;

    @Resource
    private AttributeKvMapper attributeKvMapper;

    @Resource
    private DeviceCredentialsMapper deviceCredentialsMapper;

    @Override
    public List<Map<String,Object>> queryDevicesByCustomerId(PageBean pageBean) throws Exception {

        DeviceExample example = new DeviceExample();
        SysUser user = UserUtil.getUser();
        example.createCriteria().andTenantIdEqualTo(user.getTenantId()).andCustomerIdEqualTo(user.getCustomerId());
        example.setLimitStart(pageBean.getPageStart());
        example.setLimitEnd(pageBean.getPageSize());

        example.setOrderByClause("entity_id");
        List<AttributeKv> list = deviceMapper.queryDevicesByTenantId(null,example);

        List<Map<String,Object>> deviceList = converToDeviceList(list);

        return deviceList;
    }

    @Override
    public RspBean addDevice(Map<String,Object> param) throws Exception {

        RspBean rspBean = new RspBean();

        String deviceCode = (String) param.get("deviceCode");

        DeviceExample deviceExample = new DeviceExample();
        deviceExample.createCriteria().andSearchTextEqualTo(deviceCode).andTenantIdEqualTo(UserUtil.getUser().getTenantId());
        List<Device> list = deviceMapper.selectByExample(deviceExample);
        if(list.size()>0) {

            Device device = list.get(0);
            device.setName((String) param.get("deviceName"));
            deviceMapper.updateByPrimaryKey(device);

            for(Map.Entry me : param.entrySet()) {

                if("deviceName" != me.getKey()) {
                    AttributeKvExample attributeKvExample = new AttributeKvExample();

                    String attributeKey = (String) me.getKey();
                    attributeKvExample.createCriteria().andEntityIdEqualTo(device.getId()).andAttributeKeyEqualTo(attributeKey);
                    AttributeKv attributeKv = new AttributeKv();
                    attributeKv.setLastUpdateTs(DateUtil.getNowTime().getTime());

                    if("location_x" == me.getKey() || "location_y" == me.getKey()) {
                        attributeKv.setDblV((Double) me.getValue());
                    }else {
                        attributeKv.setStrV((String) me.getValue());
                    }

                    attributeKvMapper.updateByExampleSelective(attributeKv,attributeKvExample);
                }
            }

        }else {

            String deviceId = UUIDConverter.fromTimeUUID(UUIDs.timeBased());

            Device device = new Device();
            device.setId(deviceId);
            device.setName((String) param.get("deviceName"));
            device.setCustomerId(UserUtil.getUser().getCustomerId());
            device.setType("default");
            device.setSearchText(deviceCode);
            device.setTenantId(UserUtil.getUser().getTenantId());
            deviceMapper.insert(device);

            DeviceCredentials deviceCredentials = new DeviceCredentials();
            deviceCredentials.setId(UUIDConverter.fromTimeUUID(UUIDs.timeBased()));
            deviceCredentials.setCredentialsId("FNIC_YK_" + deviceCode);
            deviceCredentials.setCredentialsType("ACCESS_TOKEN");
            deviceCredentials.setDeviceId(deviceId);
            deviceCredentialsMapper.insert(deviceCredentials);

            for(Map.Entry me : param.entrySet()) {

                if("deviceName" != me.getKey()) {
                    AttributeKv attributeKv = new AttributeKv();
                    attributeKv.setEntityType("DEVICE");
                    attributeKv.setEntityId(deviceId);
                    attributeKv.setAttributeKey((String) me.getKey());
                    attributeKv.setAttributeType("SHARED_SCOPE");
                    attributeKv.setLastUpdateTs(DateUtil.getNowTime().getTime());

                    if("location_x" == me.getKey() || "location_y" == me.getKey() ) {
                        attributeKv.setDblV((Double) me.getValue());
                    }else {
                        attributeKv.setStrV((String) me.getValue());
                    }

                    attributeKvMapper.insert(attributeKv);
                }
            }
        }

        return rspBean;
    }

    @Override
    public RspBean validDeviceByDeviceCode(Map param) throws Exception {

        RspBean rspBean = new RspBean();

        String deviceCode = (String) param.get("deviceCode");
        boolean ifExist = false;

        DeviceExample deviceExample = new DeviceExample();
        deviceExample.createCriteria().andSearchTextEqualTo(deviceCode).andTenantIdEqualTo(UserUtil.getUser().getTenantId());
        List<Device> list = deviceMapper.selectByExample(deviceExample);

        if(list.size()>0) {
            ifExist = true;
        }
        rspBean.put("ifExist",ifExist);

        return rspBean;
    }

    private List<Map<String,Object>> converToDeviceList(List<AttributeKv> list) {

        Map<String,List<AttributeKv>> item = list.stream().collect(Collectors.groupingBy(AttributeKv::getEntityId));

        List<Map<String,Object>> tempList = Lists.newArrayList();
        for ( Map.Entry me : item.entrySet()) {
            Map<String,Object> temp = Maps.newHashMap();
            temp.put("id",me.getKey());
            for(AttributeKv attributeKv : (List<AttributeKv>) me.getValue() ) {
                //logger.debug(attributeKv.getAttributeKey() + " : " +  attributeKv.getLongV());
                switch (attributeKv.getAttributeKey()) {
                    case "location_x":
                        temp.put(attributeKv.getAttributeKey(),attributeKv.getDblV());
                        break;
                    case "location_y":
                        temp.put(attributeKv.getAttributeKey(),attributeKv.getDblV());
                        break;
                    default:
                        temp.put(attributeKv.getAttributeKey(),attributeKv.getStrV());
                        break;
                }
            }

            tempList.add(temp);
        }
        return tempList;
    }
}
