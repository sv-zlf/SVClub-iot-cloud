package com.zlf.iot.authserver.service;




import com.zlf.iot.authserver.common.utils.JwtTokenUtil;
import com.zlf.iot.authserver.dao.UserMapper;
import com.zlf.iot.authserver.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;

/**
 * @author: zzx
 * @date: 2018/10/15 16:54
 * @description: 用户认证、权限
 */
@Component
public class SelfUserDetailsService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //通过username查询用户
        User user = userMapper.SelectByUsername(username);
        if(user == null){
            //仍需要细化处理
            throw new UsernameNotFoundException("该用户不存在");
        }
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        Collection authoritiesSet = new HashSet();
        // 模拟从数据库中获取用户角色
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_ADMIN");

        authoritiesSet.add(authority);
        user.setAuthorities(authoritiesSet);

      //  log.info("用户{}验证通过",username);
        return user;
    }


    public boolean check_token(String token){

        return  JwtTokenUtil.isExpiration(token);
    }
}