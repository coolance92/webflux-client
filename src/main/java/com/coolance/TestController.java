package com.coolance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * @ClassName TestController
 * @Description 测试
 * @Author Coolance
 * @Version
 * @Date 2019/10/16 11:27
 */
@RestController
public class TestController {
    /**
     * 直接注入定义的接口
     */
    @Autowired
    IUserApi userApi;

    @GetMapping("/")
    public void test() {
        //直接调用调用 实现调用rest接口的效果
        Flux<User> users = userApi.getAllUser();
        users.subscribe(System.out::println);
    }
}
