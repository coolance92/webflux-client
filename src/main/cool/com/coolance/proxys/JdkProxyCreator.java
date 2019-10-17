package com.coolance.proxys;

import com.coolance.bean.MethodInfo;
import com.coolance.bean.ServerInfo;
import com.coolance.interfaces.ProxyCreator;
import com.coolance.interfaces.RestHandler;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @ClassName JdkProxyCreator
 * @Description 使用jdk动态代理创建代理类
 * @Author Coolance
 * @Version
 * @Date 2019/10/17 9:35
 */
@Slf4j
public class JdkProxyCreator implements ProxyCreator {

    @Override
    public Object createProxy(Class<?> type) {
        log.info("createProxy:" + type);
        // 根据接口得到API服务器信息
        ServerInfo serverInfo = extractServerInfo(type);
        log.info("serverInfo:" + serverInfo);
        // 每一个代理类一个实现
        RestHandler restHandler = null;

        restHandler.init(serverInfo);

        return Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{type}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // 根据方法和参数得到调用信息
                MethodInfo methodInfo = extractMethodInfo(method, args);
                return restHandler.invokeRest(methodInfo);
            }

            private MethodInfo extractMethodInfo(Method method, Object[] args) {
                return null;
            }
        });
    }

    private ServerInfo extractServerInfo(Class<?> type) {
        return null;
    }
}
