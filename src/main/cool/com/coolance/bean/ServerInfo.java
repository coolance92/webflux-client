package com.coolance.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName ServerInfo
 * @Description 服务器信息
 * @Author Coolance
 * @Version
 * @Date 2019/10/17 9:40
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServerInfo {

    private String url;
}
