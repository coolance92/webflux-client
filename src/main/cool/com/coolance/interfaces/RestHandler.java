package com.coolance.interfaces;

import com.coolance.bean.MethodInfo;
import com.coolance.bean.ServerInfo;

/**
 * @ClassName RestHandler
 * @Description rest请求调用处理接口
 * @Author Coolance
 * @Version
 * @Date 2019/10/17 9:58
 */
public interface RestHandler {
    /**
     * 初始化服务器信息
     *
     * @param serverInfo
     */
    void init(ServerInfo serverInfo);

    /**
     * 调用rest接口，返回结果
     * @param methodInfo
     * @return
     */
    Object invokeRest(MethodInfo methodInfo);
}
