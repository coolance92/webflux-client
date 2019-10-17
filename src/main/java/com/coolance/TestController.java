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
    private IUserApi userApi;

    @GetMapping("/")
    public void test() {
        // 测试信息提取，不订阅，不会实际发出请求，但会进入我们的代理类
//        userApi.getAllUser();
//        userApi.getUserById("111111");
//        userApi.deleteUserById("222222");
//        userApi.createUser(Mono.just(User.builder().name("coolance").age(28).build()));

        //直接调用调用 实现调用rest接口的效果
//        Flux<User> users = userApi.getAllUser();
//        users.subscribe(System.out::println);
//        String id = "5da3c8eef182d10fd05281b0";
//        userApi.getUserById(id).subscribe(user -> {
//            System.out.println("找到用户:" + user);
//        }, e -> {
//            System.err.println("找不到用户:" + e.getMessage());
//        });
//
//        userApi.deleteUserById(id).subscribe();
        // 创建用户
        userApi.createUser(Mono.just(User.builder().name("山伯").age(28).build()))
                .subscribe(System.out::println);
    }
}
