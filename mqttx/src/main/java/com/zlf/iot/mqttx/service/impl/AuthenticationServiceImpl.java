package com.zlf.iot.mqttx.service.impl;


import com.zlf.iot.mqttx.common.VO.GlobalResult;
import com.zlf.iot.mqttx.entity.Authentication;
import com.zlf.iot.mqttx.exception.AuthenticationException;
import com.zlf.iot.mqttx.exception.AuthorizationException;
import com.zlf.iot.mqttx.service.IAuthenticationService;

//import org.apache.ibatis.binding.BindingException;
import com.zlf.iot.mqttx.service.ProFeignService;
import org.apache.ibatis.binding.BindingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 认证服务
 *
 * @author Jun
 * @date 2020-03-04 12:44
 */
@Service
public class AuthenticationServiceImpl implements IAuthenticationService {

    @Autowired
    ProFeignService proFeignService;

    private GlobalResult result;
    @Override
    public Authentication authenticate(String productKey,String username, String password) throws AuthenticationException, AuthorizationException {

        try {
            result=proFeignService.authDevice(productKey,username,password);
            if (result.getStatus()!=200) {
                throw new AuthenticationException("密码校验失败");
            }
        } catch (BindingException e) {
            throw new AuthenticationException("密码校验不通过");
        }
        return null;
    }
}
