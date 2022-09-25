package cn.lnd.dubbo;

import cn.lnd.client.UserService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;

/**
 * @Author lnd
 * @Description
 * @Date 2022/9/26 0:34
 */
public class DubboClient {


    public static void main(String[] args) {
        //Application
        ApplicationConfig applicationConfig = new ApplicationConfig("client-app");

        //Reference
        ReferenceConfig<UserService> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setProtocol("dubbo");
        referenceConfig.setApplication(applicationConfig);
        referenceConfig.setInterface(UserService.class);
        referenceConfig.setRegistry(new RegistryConfig(RegistryConfig.NO_AVAILABLE));
        referenceConfig.setUrl("dubbo://192.168.3.7:20880/cn.lnd.client.UserService");

        //拿到Dubbo服务器上的Service
        UserService userService = referenceConfig.get();

        System.out.println(userService.getUser(1));
    }
}
