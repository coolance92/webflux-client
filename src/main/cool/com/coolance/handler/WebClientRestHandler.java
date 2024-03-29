package com.coolance.handler;

import com.coolance.bean.MethodInfo;
import com.coolance.bean.ServerInfo;
import com.coolance.interfaces.RestHandler;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * @ClassName WebClientRestHandler
 * @Description webclient处理rest接口类
 * @Author Coolance
 * @Version
 * @Date 2019/10/17 11:29
 */
public class WebClientRestHandler implements RestHandler {
    private WebClient webClient;

    /**
     * 初始化webclient
     *
     * @param serverInfo
     */
    @Override
    public void init(ServerInfo serverInfo) {
        this.webClient = WebClient.create(serverInfo.getUrl());
    }

    /**
     * 处理rest请求
     *
     * @param methodInfo
     * @return
     */
    @Override
    public Object invokeRest(MethodInfo methodInfo) {
        Object result = null;

        WebClient.RequestBodySpec accept = this.webClient
                .method(methodInfo.getMethod())
                .uri(methodInfo.getUri(), methodInfo.getParams())
                .accept(MediaType.APPLICATION_JSON);
        WebClient.ResponseSpec retrieve = null;
        if (methodInfo.getBody() != null) {
            retrieve = accept.body(methodInfo.getBody(), methodInfo.getBodyElementType()).retrieve();
        } else {
            retrieve = accept.retrieve();
        }

        // 处理异常
        retrieve.onStatus(status -> status.value() == 404,
                response -> Mono.just(new RuntimeException("Not Found")));

        if (methodInfo.isFlag()) {
            result = retrieve.bodyToFlux(methodInfo.getReturnElementType());
        } else {
            result = retrieve.bodyToMono(methodInfo.getReturnElementType());
        }

        return result;
    }
}
