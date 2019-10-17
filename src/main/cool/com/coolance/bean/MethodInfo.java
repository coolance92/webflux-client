package com.coolance.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
@AllArgsConstructor
public class MethodInfo {
    /**
     * 请求uri
     */
    private String uri;
    /**
     * 请求方法
     */
    private HttpMethod method;
    /**
     * url上的请求参数
     */
    private Map<String, Object> params;
    /**
     * 请求body
     */
    private Mono<?> body;
    /**
     * 返回是Flux还是Mono，true：Flux；false：Mono
     */
    private boolean flag;
    /**
     * 返回结果泛型参数的类型
     */
    private Class<?> returnElementType;
}
