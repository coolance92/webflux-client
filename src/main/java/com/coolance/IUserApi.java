package com.coolance;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @ClassName IUserApi
 * @Description 客户端调用接口
 * @Author Coolance
 * @Version
 * @Date 2019/10/16 11:20
 */
@ServerApi("http://localhost:8080/user")
public interface IUserApi {

    /**
     * 获取所有用户
     *
     * @return
     */
    @GetMapping("/")
    Flux<User> getAllUser();

    /**
     * 根据id获取用户
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    Mono<User> getUserById(@PathVariable("id") String id);

    /**
     * 根据id删除用户
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    Mono<Void> deleteUserById(@PathVariable("id") String id);

    /**
     * 创建用户
     *
     * @param user
     * @return
     */
    @PostMapping("/")
    Mono<User> createUser(@RequestBody Mono<User> user);
}
