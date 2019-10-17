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
    private String url;
    private HttpMethod method;
    private Map<String, Object> params;
    private Mono<?> body;
}
