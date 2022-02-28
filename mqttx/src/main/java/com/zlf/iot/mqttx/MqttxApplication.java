package com.zlf.iot.mqttx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import com.zlf.iot.mqttx.broker.BrokerInitializer;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class MqttxApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx =   SpringApplication.run(MqttxApplication.class, args);
        //启动mqtt
        try {
            ctx.getBean(BrokerInitializer.class).start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
