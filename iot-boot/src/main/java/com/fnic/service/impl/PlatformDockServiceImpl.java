package com.fnic.service.impl;

import com.datastax.driver.core.utils.UUIDs;
import com.fnic.bean.PageBean;
import com.fnic.bean.ReqBean;
import com.fnic.mybatis.thingsboard.dao.*;
import com.fnic.mybatis.thingsboard.model.*;
import com.fnic.service.BaseService;
import com.fnic.service.PlatformDockService;
import com.fnic.sysframe.security.SysUser;
import com.fnic.sysframe.utils.DateUtil;
import com.fnic.sysframe.utils.UUIDConverter;
import com.fnic.sysframe.utils.UserUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PlatformDockServiceImpl extends BaseService implements PlatformDockService {

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

    @Override
    public Map<String, Object> addDevice(Map<String, Object> param,String defaultTenantId,String defaultCustomerId,String prefix) throws Exception {

        Map<String,Object> rspMap = Maps.newHashMap();

        String deviceCode = (String) param.get("aid");

        DeviceExample deviceExample = new DeviceExample();
        deviceExample.createCriteria().andSearchTextEqualTo(deviceCode).andTenantIdEqualTo(defaultTenantId);
        List<Device> list = deviceMapper.selectByExample(deviceExample);
        if(list.size()>0) {
            Device device = list.get(0);

            for(Map.Entry me : param.entrySet()) {
                dealUpdateAttribute(me,device);
            }
        }else {

            String deviceId = UUIDConverter.fromTimeUUID(UUIDs.timeBased());

            Device device = new Device();
            device.setId(deviceId);
            //device.setName((String) param.get("deviceName"));
            device.setName(prefix + "_" + deviceCode);
            device.setCustomerId(defaultCustomerId);
            device.setType("default");
            device.setSearchText(deviceCode);
            device.setTenantId(defaultTenantId);
            deviceMapper.insert(device);

            DeviceCredentials deviceCredentials = new DeviceCredentials();
            deviceCredentials.setId(UUIDConverter.fromTimeUUID(UUIDs.timeBased()));
            deviceCredentials.setCredentialsId("FNIC_" + prefix + "_" + deviceCode);
            deviceCredentials.setCredentialsType("ACCESS_TOKEN");
            deviceCredentials.setDeviceId(deviceId);
            deviceCredentialsMapper.insert(deviceCredentials);

            for(Map.Entry me : param.entrySet()) {
                dealInsertAttribute(me,deviceId);
            }
        }

        rspMap.put("success",true);
        rspMap.put("error_code",0);
        rspMap.put("error_msg","");

        return rspMap;
    }

    @Override
    public List<Map<String, Object>> queryDevicesGC(ReqBean reqBean) throws Exception {
        DeviceExample example = new DeviceExample();
        PageBean pageBean = reqBean.getPageBean();
        DeviceExample.Criteria deviceCrit = example.createCriteria();
        AttributeKvExample attributeKvExample = new AttributeKvExample();
        AttributeKvExample.Criteria attributCrit = attributeKvExample.createCriteria();

        Map<String,Object> param = reqBean.getReqParam();

        if(!StringUtils.isEmpty(param)) {
            String deviceType = (String) param.get("deviceType");

            if("1".equals(deviceType)) {
                deviceCrit.andTenantIdEqualTo(baseConstants.defaultHaoThinkTenantId)
                        .andCustomerIdEqualTo(baseConstants.defaultHaoThinkCustomerId);
            }else if("2".equals(deviceType)) {
                deviceCrit.andTenantIdEqualTo(baseConstants.defaultHualiTenantId)
                        .andCustomerIdEqualTo(baseConstants.defaultHualiCustomerId);
                attributCrit.andAttributeKeyEqualTo("MtrType").andLongVEqualTo(0l);
            }else if("3".equals(deviceType)) {
                deviceCrit.andTenantIdEqualTo(baseConstants.defaultHualiTenantId)
                        .andCustomerIdEqualTo(baseConstants.defaultHualiCustomerId);
                attributCrit.andAttributeKeyEqualTo("MtrType").andLongVEqualTo(1l);
            }
        }else {
            List<String> tList = Lists.newArrayList();
            tList.add(baseConstants.defaultHaoThinkTenantId);
            tList.add(baseConstants.defaultHualiTenantId);

            List<String> cList = Lists.newArrayList();
            cList.add(baseConstants.defaultHaoThinkCustomerId);
            cList.add(baseConstants.defaultHualiCustomerId);

            deviceCrit.andTenantIdIn(tList).andCustomerIdIn(cList);
        }

        example.setLimitStart(pageBean.getPageStart());
        example.setLimitEnd(pageBean.getPageSize());
        example.setOrderByClause("entity_id");
        List<AttributeKv> list = deviceMapper.queryDevicesByTenantId(attributeKvExample,example);

        List<Map<String,Object>> deviceList = converToDeviceList(list);

        return deviceList;
    }

    private void dealUpdateAttribute(Map.Entry me,Device device) {

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
                    case "MtrType":
                        attributeKv.setLongV(new Long(String.valueOf((int) me.getValue())));
                        break;
                    case "PositiveKwh_Top":
                        attributeKv.setDblV((Double) me.getValue());
                        break;
                    case "PositiveKwh_Total":
                        attributeKv.setDblV((Double) me.getValue());
                        break;
                    case "PositiveKwh_Peak":
                        attributeKv.setDblV((Double) me.getValue());
                        break;
                    case "PositiveKwh_Flat":
                        attributeKv.setDblV((Double) me.getValue());
                        break;
                    case "PositiveKwh_Valley":
                        attributeKv.setDblV((Double) me.getValue());
                        break;
                    case "ValueStatus":
                        attributeKv.setLongV(new Long(String.valueOf((int) me.getValue())));
                        break;
                    case "WaterValue":
                        attributeKv.setDblV((Double) me.getValue());
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
                    dealCustomer(temp,device.getId());
                }
            }
        }
    }

    private void dealInsertAttribute(Map.Entry me,String deviceId) {

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
                        attributeKv.setLongV(new Long(String.valueOf((int) me.getValue())));
                        break;
                    case "MtrType":
                        attributeKv.setLongV(new Long(String.valueOf((int) me.getValue())));
                        break;
                    case "PositiveKwh_Top":
                        attributeKv.setDblV((Double) me.getValue());
                        break;
                    case "PositiveKwh_Total":
                        attributeKv.setDblV((Double) me.getValue());
                        break;
                    case "PositiveKwh_Peak":
                        attributeKv.setDblV((Double) me.getValue());
                        break;
                    case "PositiveKwh_Flat":
                        attributeKv.setDblV((Double) me.getValue());
                        break;
                    case "PositiveKwh_Valley":
                        attributeKv.setDblV((Double) me.getValue());
                        break;
                    case "ValueStatus":
                        attributeKv.setLongV(new Long(String.valueOf((int) me.getValue())));
                        break;
                    case "WaterValue":
                        attributeKv.setDblV((Double) me.getValue());
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
                    dealCustomer(temp,deviceId);
                }
            }
        }
    }

    private void dealCustomer(Map<String,Object> param,String deviceId) {

        String phone = (String) param.get("tele");

        CustomerExample customerExample = new CustomerExample();
        customerExample.createCriteria().andPhoneEqualTo(phone);
        List<Customer> customerList = customerMapper.selectByExample(customerExample);

        if(customerList.size()>0) {

            Customer customer = customerList.get(0);

            customer.setSearchText((String) param.get("name"));
            customer.setTitle((String) param.get("name"));
            customer.setAdditionalInfo((String) param.get("descrip"));

            customerMapper.updateByPrimaryKeySelective(customer);

            dealRelation(customer.getId(),deviceId);

        }else {
            Customer customer = new Customer();

            String customerId = UUIDConverter.fromTimeUUID(UUIDs.timeBased());
            customer.setId(customerId);
            customer.setPhone((String) param.get("tele"));
            customer.setSearchText((String) param.get("name"));
            customer.setTitle((String) param.get("name"));
            customer.setAdditionalInfo((String) param.get("descrip"));

            customerMapper.insert(customer);

            dealRelation(customerId,deviceId);
        }

    }

    private void dealRelation(String customerId,String deviceId) {

        RelationExample relationExample = new RelationExample();

        relationExample.createCriteria().andFromIdEqualTo(deviceId).andToIdEqualTo(customerId);
        List<Relation> list = relationMapper.selectByExample(relationExample);

        if(list.size() == 0) {
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
                    case "product_type":
                        temp.put(attributeKv.getAttributeKey(),attributeKv.getLongV());
                        break;
                    case "MtrType":
                        temp.put(attributeKv.getAttributeKey(),attributeKv.getLongV());
                        break;
                    case "PositiveKwh_Top":
                        temp.put(attributeKv.getAttributeKey(),attributeKv.getDblV());
                        break;
                    case "PositiveKwh_Total":
                        temp.put(attributeKv.getAttributeKey(),attributeKv.getDblV());
                        break;
                    case "PositiveKwh_Peak":
                        temp.put(attributeKv.getAttributeKey(),attributeKv.getDblV());
                        break;
                    case "PositiveKwh_Flat":
                        temp.put(attributeKv.getAttributeKey(),attributeKv.getDblV());
                        break;
                    case "PositiveKwh_Valley":
                        temp.put(attributeKv.getAttributeKey(),attributeKv.getDblV());
                        break;
                    case "ValueStatus":
                        temp.put(attributeKv.getAttributeKey(),attributeKv.getDblV());
                        break;
                    case "WaterValue":
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
