package com.coolance.proxys;

import com.coolance.ServerApi;
import com.coolance.bean.MethodInfo;
import com.coolance.bean.ServerInfo;
import com.coolance.handler.WebClientRestHandler;
import com.coolance.interfaces.ProxyCreator;
import com.coolance.interfaces.RestHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Proxy;
import java.util.LinkedHashMap;
import java.util.Map;

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
        RestHandler restHandler = new WebClientRestHandler();
        // 初始化服务器信息(初始化webclient)
        restHandler.init(serverInfo);

        return Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{type}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // 根据方法和参数得到调用信息
                MethodInfo methodInfo = extractMethodInfo(method, args);
                log.info("methodInfo:" + methodInfo);
                return restHandler.invokeRest(methodInfo);
            }

            /**
             * 根据方法定义和调用参数得到调用相关的信息
             *
             * @param method
             * @param args
             * @return
             */
            private MethodInfo extractMethodInfo(Method method, Object[] args) {
                MethodInfo methodInfo = new MethodInfo();
                extractMethodAndUrl(method, methodInfo);
                extractParamsAndBody(method, args, methodInfo);
                return methodInfo;
            }
            /**
             * 得到MethodInfo的params和body
             *
             * @param method
             * @param args
             * @param methodInfo
             */
            private void extractParamsAndBody(Method method, Object[] args, MethodInfo methodInfo) {
                Parameter[] parameters = method.getParameters();
                Map<String, Object> params = new LinkedHashMap<>();
                methodInfo.setParams(params);
                for (int i = 0; i < parameters.length; i++) {
                    PathVariable annoPath = parameters[i].getAnnotation(PathVariable.class);
                    if (annoPath != null) {
                        params.put(annoPath.value(), args[i]);
                    }
                    RequestBody annoBody = parameters[i].getAnnotation(RequestBody.class);
                    if (annoBody != null) {
                        methodInfo.setBody((Mono<?>)args[i]);
                    }
                }
            }
            /**
             * 得到MethodInfo的url和method
             *
             * @param method
             * @param methodInfo
             */
            private void extractMethodAndUrl(Method method, MethodInfo methodInfo) {
                Annotation[] annotations = method.getAnnotations();
                for (Annotation annotation : annotations) {
                    if (annotation instanceof GetMapping) {
                        methodInfo.setUrl(((GetMapping) annotation).value()[0]);
                        methodInfo.setMethod(HttpMethod.GET);
                    } else if (annotation instanceof PostMapping) {
                        methodInfo.setUrl(((PostMapping) annotation).value()[0]);
                        methodInfo.setMethod(HttpMethod.POST);
                    } else if (annotation instanceof PutMapping) {
                        methodInfo.setUrl(((PutMapping) annotation).value()[0]);
                        methodInfo.setMethod(HttpMethod.PUT);
                    } else if (annotation instanceof DeleteMapping) {
                        methodInfo.setUrl(((DeleteMapping) annotation).value()[0]);
                        methodInfo.setMethod(HttpMethod.DELETE);
                    }
                }
            }
        });
    }

    private ServerInfo extractServerInfo(Class<?> type) {
        ServerInfo serverInfo = new ServerInfo();
        ServerApi serverApi = type.getAnnotation(ServerApi.class);
        serverInfo.setUrl(serverApi.value());
        return serverInfo;
    }
}
