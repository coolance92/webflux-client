package com.coolance.interfaces;
/**
 *@ClassName ProxyCreator
 *@Description 创建代理类接口
 *@Author Coolance
 *@Version
 *@Date 2019/10/17 09:12
 */
public interface ProxyCreator {
    Object createProxy(Class<?> type);
}
