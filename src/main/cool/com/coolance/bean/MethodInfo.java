package com.coolance.bean;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpMethod;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * @ClassName MethodInfo
 * @Description 方法调用信息类
 * @Author Coolance
 * @Version
 * @Date 2019/10/17 9:50
 */
@Data
@Builder
public class MethodInfo {
    private String url;
    private HttpMethod method;
    private Map<String, Object> params;
    private Mono<?> body;
}
