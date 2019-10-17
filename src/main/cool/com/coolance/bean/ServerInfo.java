package com.coolance.bean;

import lombok.Builder;
import lombok.Data;

/**
 * @ClassName ServerInfo
 * @Description 服务器信息
 * @Author Coolance
 * @Version
 * @Date 2019/10/17 9:40
 */
@Data
@Builder
public class ServerInfo {
    private String url;
}
