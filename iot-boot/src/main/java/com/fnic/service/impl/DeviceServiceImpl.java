package com.fnic.service.impl;

import com.fnic.bean.PageBean;
import com.fnic.mybatis.thingsboard.dao.DeviceMapper;
import com.fnic.mybatis.thingsboard.model.AttributeKv;
import com.fnic.mybatis.thingsboard.model.Device;
import com.fnic.mybatis.thingsboard.model.DeviceExample;
import com.fnic.service.DeviceService;
import com.fnic.sysframe.security.SysUser;
import com.fnic.sysframe.utils.UserUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DeviceServiceImpl implements DeviceService {

    @Resource
    private DeviceMapper deviceMapper;

    @Override
    public List<Map<String,Object>> queryDevicesByCustomerId(PageBean pageBean) {

        DeviceExample example = new DeviceExample();
        SysUser user = UserUtil.getUser();
        example.createCriteria().andTenantIdEqualTo(user.getTenantId()).andCustomerIdEqualTo(user.getCustomerId());
        example.setLimitStart(pageBean.getPageStart());
        example.setLimitEnd(pageBean.getPageSize());

        example.setOrderByClause("entity_id");
        List<AttributeKv> list = deviceMapper.queryDevicesByTenantId(example);

        List<Map<String,Object>> deviceList = converToDeviceList(list);

        return deviceList;
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
                        temp.put(attributeKv.getAttributeKey(),attributeKv.getLongV());
                        break;
                }
            }

            tempList.add(temp);
        }
        return tempList;
    }
}
