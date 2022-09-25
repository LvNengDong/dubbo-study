package cn.lnd.dubbo;

import cn.lnd.client.UserService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;

import java.io.IOException;


/**
 * @Author lnd
 * @Description 负责将 UserService 服务暴露出去，让client可以调用
 * @Date 2022/9/26 0:06
 */
public class DubboServer {

    public static void main(String[] args) throws IOException {
        /*
        原则性的东西，不管是使用 Spring、SpringBoot、还是直接通过代码的方式配置Dubbo，下面这些东西都是最基本的原则
        开始暴露 UserService 服务
            1、Application：暴露的服务位于哪个应用程序上
            2、Protocol：暴露的服务要通过什么协议访问（Dubbo支持多种协议，除了自身特有的dubbo协议外，还支持http、rmi等多种协议）
            3、Registry：注册中心。DubboServer负责将服务暴露给注册中心
            4、Service：具体要暴露哪一个服务
        */
        ApplicationConfig applicationConfig = new ApplicationConfig("sample-app");

        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setName("dubbo");//协议名称
        //因为服务就在本机上启动，所以无需特别指定IP地址
        protocolConfig.setPort(-1);//-1表示使用默认端口号，即20880

        RegistryConfig registryConfig = new RegistryConfig(RegistryConfig.NO_AVAILABLE);// NO_AVAILABLE 表示直连，即不使用注册中心

        ServiceConfig serviceConfig = new ServiceConfig();
        serviceConfig.setInterface(UserService.class);//暴露服务
        serviceConfig.setRef(new UserServiceImpl());//暴露服务的实现

        //将Service与其它几项进行关联
        serviceConfig.setRegistry(registryConfig);
        serviceConfig.setProtocol(protocolConfig);
        serviceConfig.setApplication(applicationConfig);

        //调用export方法暴露Service服务
        serviceConfig.export();
        System.out.println("服务已暴露");

        System.in.read();//确保程序不会被关闭，以保证能一直被客户端访问
    }
}
