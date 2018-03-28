package com.fnic.service.impl;

import com.datastax.driver.core.utils.UUIDs;
import com.fnic.bean.RspBean;
import com.fnic.mybatis.iot.dao.TUserMapper;
import com.fnic.mybatis.iot.model.TUser;
import com.fnic.mybatis.iot.model.TUserExample;
import com.fnic.mybatis.thingsboard.dao.CustomerMapper;
import com.fnic.mybatis.thingsboard.dao.TbUserMapper;
import com.fnic.mybatis.thingsboard.dao.UserCredentialsMapper;
import com.fnic.mybatis.thingsboard.model.Customer;
import com.fnic.mybatis.thingsboard.model.TbUser;
import com.fnic.mybatis.thingsboard.model.TbUserExample;
import com.fnic.mybatis.thingsboard.model.UserCredentials;
import com.fnic.service.BaseService;
import com.fnic.service.CustomerService;
import com.fnic.sysframe.config.BaseConstants;
import com.fnic.sysframe.security.Authority;
import com.fnic.sysframe.utils.UUIDConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class CustomerServiceImpl extends BaseService implements CustomerService {

    @Resource
    private CustomerMapper customerMapper;

    @Resource
    private TbUserMapper tbUserMapper;

    @Resource
    private UserCredentialsMapper userCredentialsMapper;

    @Resource
    private TUserMapper tUserMapper;

    @Override
    public RspBean regCustomer(Map<String, Object> param) {

        RspBean rspBean = new RspBean();

        String accountName = (String) param.get("accountName");

        if(!StringUtils.isEmpty(accountName)) {
            TbUserExample example = new TbUserExample();
            example.createCriteria().andEmailEqualTo(accountName);

            List<TbUser> list = tbUserMapper.selectByExample(example);

            if(list.size() > 0) {
                rspBean.setRspCode(RspBean.ERROR_CODE);
                rspBean.setRspDesc("Acount exist");
            }else {
                TbUser tbUser = new TbUser();
                String userId = UUIDConverter.fromTimeUUID(UUIDs.timeBased());
                String customerId = UUIDConverter.fromTimeUUID(UUIDs.timeBased());
                tbUser.setId((userId));
                tbUser.setAuthority(Authority.CUSTOMER_USER.toString());
                tbUser.setCustomerId(customerId);
                tbUser.setEmail(accountName);
                tbUser.setSearchText(accountName);
                tbUser.setTenantId(baseConstants.defaultTenantId);

                tbUserMapper.insert(tbUser);

                Customer customer = new Customer();
                customer.setId(customerId);
                customer.setSearchText(accountName);
                customer.setTenantId(baseConstants.defaultTenantId);
                customer.setTitle(accountName);
                customerMapper.insert(customer);

                String userCredentialsId = UUIDConverter.fromTimeUUID(UUIDs.timeBased());

                BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
                String password = bCryptPasswordEncoder.encode(baseConstants.defaultPassword);

                UserCredentials userCredentials = new UserCredentials();
                userCredentials.setId(userCredentialsId);
                userCredentials.setEnabled(true);
                userCredentials.setPassword(password);
                userCredentials.setUserId(userId);

                userCredentialsMapper.insert(userCredentials);
            }
        }else {
            rspBean.setRspCode(RspBean.ERROR_CODE);
            rspBean.setRspDesc("Acount is empty");
        }

        return rspBean;
    }

    @Override
    public List<TUser> queryUser() {

        TUserExample example = new TUserExample();
        example.createCriteria();

        return tUserMapper.selectByExample(example);
    }
}
