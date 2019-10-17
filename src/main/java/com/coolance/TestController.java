package com.coolance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
        userApi.getAllUser();
        userApi.getUserById("111111");
        userApi.deleteUserById("222222");
        userApi.createUser(Mono.just(User.builder().name("coolance").age(28).build()));

        //直接调用调用 实现调用rest接口的效果
//        Flux<User> users = userApi.getAllUser();
//        users.subscribe(System.out::println);
    }
}
