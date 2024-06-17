package com.gexingw.unittest.controller;

import com.gexingw.unittest.command.UserCreateRequest;
import com.gexingw.unittest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author GeXingW
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor(onConstructor_ = {@Lazy})
public class UserController {

    private final UserService userService;

    @PostMapping
    public Integer create(@RequestBody UserCreateRequest createRequest) {
        return userService.save(createRequest);
    }

}
