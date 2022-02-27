package com.zlf.iot.mqttx.service.impl;


import com.zlf.iot.entity.Device;
import com.zlf.iot.mqttx.entity.Authentication;
import com.zlf.iot.mqttx.exception.AuthenticationException;
import com.zlf.iot.mqttx.exception.AuthorizationException;
import com.zlf.iot.mqttx.service.IAuthenticationService;
import com.zlf.iot.service.DeviceService;
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
    DeviceService deviceService;

    @Override
    public Authentication authenticate(String username, String password) throws AuthenticationException, AuthorizationException {

        try {
            Device device = deviceService.autheDevice(username, password);
            if (device == null)
                throw new AuthenticationException("密码校验不通过");

        } catch (BindingException e) {
            throw new AuthenticationException("密码校验不通过");
        }
        return null;
    }
}
