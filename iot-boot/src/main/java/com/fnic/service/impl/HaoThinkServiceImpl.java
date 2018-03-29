package com.fnic.service.impl;

import com.datastax.driver.core.utils.UUIDs;
import com.fnic.mybatis.thingsboard.dao.*;
import com.fnic.mybatis.thingsboard.model.*;
import com.fnic.service.BaseService;
import com.fnic.service.HaoThinkService;
import com.fnic.sysframe.config.BaseConstants;
import com.fnic.sysframe.utils.DateUtil;
import com.fnic.sysframe.utils.UUIDConverter;
import com.fnic.sysframe.utils.UserUtil;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class HaoThinkServiceImpl extends BaseService implements HaoThinkService {

    @Resource
    private DeviceMapper deviceMapper;

    @Resource
    private AttributeKvMapper attributeKvMapper;

    @Resource
    private DeviceCredentialsMapper deviceCredentialsMapper;

    @Resource
    private CustomerMapper customerMapper;

    @Resource
    private RelationMapper relationMapper;

    @Value("${default.haothink.customerId}")
    public  String defaultHaoThinkCustomerId;

    @Override
    public Map<String, Object> addDevice(Map<String, Object> param) throws Exception {

        Map<String,Object> rspMap = Maps.newHashMap();

        String deviceCode = (String) param.get("aid");

        DeviceExample deviceExample = new DeviceExample();
        deviceExample.createCriteria().andSearchTextEqualTo(deviceCode).andTenantIdEqualTo(baseConstants.defaultHaoThinkTenantId);
        List<Device> list = deviceMapper.selectByExample(deviceExample);
        if(list.size()>0) {
            Device device = list.get(0);

            for(Map.Entry me : param.entrySet()) {

                if("teles" != me.getKey()) {
                    if(!StringUtils.isEmpty(me.getValue())) {
                        AttributeKv attributeKv = new AttributeKv();
                        attributeKv.setEntityId(device.getId());
                        attributeKv.setAttributeKey((String) me.getKey());
                        attributeKv.setLastUpdateTs(DateUtil.getNowTime().getTime());

                        switch ((String) me.getKey()) {
                            case "product_type":
                                attributeKv.setLongV(new Long(String.valueOf((int) me.getValue())));
                                break;
                            default:
                                attributeKv.setStrV((String) me.getValue());
                                break;
                        }

                        AttributeKvExample attributeKvExample = new AttributeKvExample();
                        attributeKvExample.createCriteria().andEntityIdEqualTo(device.getId()).andAttributeKeyEqualTo((String) me.getKey());

                        attributeKvMapper.updateByExampleSelective(attributeKv,attributeKvExample);
                    }
                }else {
                    List<Map<String,Object>> telelist = (List<Map<String,Object>>) me.getValue();

                    if(telelist != null)
                    {
                        for(Map<String,Object> temp : telelist) {

                            String phone = (String) temp.get("tele");

                            CustomerExample customerExample = new CustomerExample();
                            customerExample.createCriteria().andPhoneEqualTo(phone);
                            List<Customer> customerList = customerMapper.selectByExample(customerExample);

                            if(customerList.size()>0) {

                                Customer customer = customerList.get(0);

                                customer.setSearchText((String) temp.get("name"));
                                customer.setTitle((String) temp.get("name"));
                                customer.setAdditionalInfo((String) temp.get("descrip"));

                                customerMapper.updateByPrimaryKeySelective(customer);
                            }else {
                                Customer customer = new Customer();

                                String customerId = UUIDConverter.fromTimeUUID(UUIDs.timeBased());
                                customer.setId(customerId);
                                customer.setPhone(phone);
                                customer.setSearchText((String) temp.get("name"));
                                customer.setTitle((String) temp.get("name"));
                                customer.setAdditionalInfo((String) temp.get("descrip"));

                                customerMapper.insert(customer);

                                Relation relation = new Relation();
                                relation.setFromId(device.getId());
                                relation.setFromType("DEVICE");
                                relation.setToId(customerId);
                                relation.setToType("CUSTOMER");
                                relation.setRelationType("Contains");
                                relation.setRelationTypeGroup("COMMON");

                                relationMapper.insert(relation);
                            }
                        }
                    }
                }
            }
        }else {

            String deviceId = UUIDConverter.fromTimeUUID(UUIDs.timeBased());

            Device device = new Device();
            device.setId(deviceId);
            device.setName((String) param.get("deviceName"));
            device.setCustomerId(baseConstants.defaultHaoThinkCustomerId);
            device.setType("default");
            device.setSearchText(deviceCode);
            device.setTenantId(baseConstants.defaultHaoThinkTenantId);
            deviceMapper.insert(device);

            DeviceCredentials deviceCredentials = new DeviceCredentials();
            deviceCredentials.setId(UUIDConverter.fromTimeUUID(UUIDs.timeBased()));
            deviceCredentials.setCredentialsId("FNIC_HT_" + deviceCode);
            deviceCredentials.setCredentialsType("ACCESS_TOKEN");
            deviceCredentials.setDeviceId(deviceId);
            deviceCredentialsMapper.insert(deviceCredentials);

            for(Map.Entry me : param.entrySet()) {

                if("teles" != me.getKey()) {
                    if(!StringUtils.isEmpty(me.getValue())) {
                        AttributeKv attributeKv = new AttributeKv();
                        attributeKv.setEntityType("DEVICE");
                        attributeKv.setEntityId(deviceId);
                        attributeKv.setAttributeKey((String) me.getKey());
                        attributeKv.setAttributeType("SHARED_SCOPE");
                        attributeKv.setLastUpdateTs(DateUtil.getNowTime().getTime());
                        switch ((String) me.getKey()) {
                            case "product_type":
                                attributeKv.setLongV( new Long(String.valueOf((int) me.getValue())));
                                break;
                            default:
                                attributeKv.setStrV((String) me.getValue());
                                break;
                        }
                        attributeKvMapper.insert(attributeKv);
                    }
                }else {
                    List<Map<String,Object>> telelist = (List<Map<String,Object>>) me.getValue();

                    if(telelist != null) {
                        for (Map<String, Object> temp : telelist) {
                            Customer customer = new Customer();

                            String customerId = UUIDConverter.fromTimeUUID(UUIDs.timeBased());
                            customer.setId(customerId);
                            customer.setPhone((String) temp.get("tele"));
                            customer.setSearchText((String) temp.get("name"));
                            customer.setTitle((String) temp.get("name"));
                            customer.setAdditionalInfo((String) temp.get("descrip"));

                            customerMapper.insert(customer);

                            Relation relation = new Relation();
                            relation.setFromId(deviceId);
                            relation.setFromType("DEVICE");
                            relation.setToId(customerId);
                            relation.setToType("CUSTOMER");
                            relation.setRelationType("Contains");
                            relation.setRelationTypeGroup("COMMON");

                            relationMapper.insert(relation);
                        }
                    }
                }
            }
        }

        rspMap.put("success",true);
        rspMap.put("error_code",0);
        rspMap.put("error_msg","");

        return rspMap;
    }
}
