package com.fnic.sysframe.security;

import com.fnic.mybatis.thingsboard.dao.TbUserMapper;
import com.fnic.mybatis.thingsboard.dao.UserCredentialsMapper;
import com.fnic.mybatis.thingsboard.model.TbUser;
import com.fnic.mybatis.thingsboard.model.TbUserExample;
import com.fnic.mybatis.thingsboard.model.UserCredentials;
import com.fnic.mybatis.thingsboard.model.UserCredentialsExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

/**
 * Created by hjhuang on 2017/2/7.
 */
public class CustomUserService implements UserDetailsService {

    @Autowired
    private TbUserMapper userMapper;

    @Autowired
    private UserCredentialsMapper userCredentialsMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        TbUserExample example = new TbUserExample();
        example.createCriteria().andEmailEqualTo(username);
        List<TbUser> list = userMapper.selectByExample(example);

        if(list.size()>0) {
            TbUser user = list.get(0);
            SysUser sysUser = new SysUser();
            sysUser.setId(user.getId());

            UserCredentialsExample userCredentialsExample = new UserCredentialsExample();
            userCredentialsExample.createCriteria().andUserIdEqualTo(user.getId());
            List<UserCredentials> userCredentialsList = userCredentialsMapper.selectByExample(userCredentialsExample);
            sysUser.setPassword(userCredentialsList.get(0).getPassword());
            //sysUser.setAccountId(user.getAccountId());
            //sysUser.setUserGroupId(user.getUserGroupId());
            sysUser.setEmail(user.getEmail());
            sysUser.setUsername(user.getFirstName());
            sysUser.setTenantId(user.getTenantId());
            sysUser.setCustomerId(user.getCustomerId());

            return sysUser;
        }else {
            throw new UsernameNotFoundException("用户不存在");

        }
    }
}
