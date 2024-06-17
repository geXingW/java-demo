package com.gexingw.unittest.service;

import com.gexingw.unittest.command.UserCreateRequest;
import com.gexingw.unittest.entity.User;
import com.gexingw.unittest.exception.InvalidParamException;
import com.gexingw.unittest.feign.OldUserFeign;
import com.gexingw.unittest.mapper.UserMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserMapper userMapper;

    @Mock
    private OldUserFeign oldUserFeign;

    @Spy
    @InjectMocks
    private UserService userService;

    /**
     * 测试使用简单密码保存用户信息
     * 1、验证使用简单密码创建用户时，应当抛出异常
     * 2、验证非简单密码创建用户，应当能够正确保存
     */
    @Test
    public void testSaveSimplePasswd() {
        // 验证使用简单密码创建用户时，应当抛出异常
        UserCreateRequest simplePasswdRequest = new UserCreateRequest("user-01", "123456");
        // 执行被测试方法，并验证异常类型是否匹配
        InvalidParamException assertThrows = Assertions.assertThrows(
                InvalidParamException.class, () -> userService.save(simplePasswdRequest), "简单密码应当抛出异常"
        );
        // 验证异常信息是否匹配
        Assertions.assertEquals("用户密码太简单了！", assertThrows.getMessage(), "简单密码异常信息不匹配!");
        // 抛出异常后，或许代码不应该继续执行
        Mockito.verify(userMapper, Mockito.never()).selectByUsername(simplePasswdRequest.getUsername());

        // 验证非简单密码创建用户，应当不会抛出异常
        UserCreateRequest normalPasswdRequest = new UserCreateRequest("user-01", "12345678");
        // 执行被测试方法，且不会抛出异常
        userService.save(normalPasswdRequest);
        // 未抛出异常，后续代码应当执行一次
        Mockito.verify(userMapper, Mockito.times(1)).selectByUsername(normalPasswdRequest.getUsername());
    }

    /**
     * 测试使用不存在的用户名保存用户信息，应当执行新增操作，且更新操作不会执行
     */
    @Test
    public void testSaveNotExistUsername() {
        // 用户名不存在的用户请求
        UserCreateRequest notExistUsernameRequest = new UserCreateRequest("user-01", "12345678");
        // 模拟UserMapper.selectByUsername返回值
        Mockito.doReturn(null).when(userMapper).selectByUsername(notExistUsernameRequest.getUsername());
        // 模拟UserService.create方法返回值
        Mockito.doReturn(1).when(userService).create(notExistUsernameRequest);
        // 执行被测试方法
        Integer createdUserId = userService.save(notExistUsernameRequest);

        // 未抛出异常，UserMapper.selectByUsername应当执行一次
        Mockito.verify(userMapper, Mockito.times(1)).selectByUsername(notExistUsernameRequest.getUsername());
        // 未抛出异常，UserService.create应当执行一次
        Mockito.verify(userService, Mockito.times(1)).create(notExistUsernameRequest);
        // 用户不存在，UserService.update方法不应该被执行
        Mockito.verify(userService, Mockito.never()).update(Mockito.any(), Mockito.any());
        // UserService.create返回1，UserService.save返回值也应当为1
        Assertions.assertEquals(1, createdUserId, "新增用户Id应该为1!");
    }

    /**
     * 验证使用已存在的用户名保存用户信息，应当执行更新操作，且新增操作不会被执行
     */
    @Test
    public void testSaveExistUsername() {
        User user = new User().setId(1);
        // 用户名存在的用户请求
        UserCreateRequest existUsernameRequest = new UserCreateRequest("user-01", "12345678");
        // 模拟UserMapper.selectByUsername返回值
        Mockito.doReturn(user).when(userMapper).selectByUsername(existUsernameRequest.getUsername());
        // 模拟UserService.update调用，但是没有返回值
        Mockito.doNothing().when(userService).update(Mockito.any(), Mockito.any());
        // 执行被测试方法
        Integer updateUserId = userService.save(existUsernameRequest);
        // 未抛出异常，UserMapper.selectByUsername应当执行一次
        Mockito.verify(userMapper, Mockito.times(1)).selectByUsername(existUsernameRequest.getUsername());
        // 用户存在，UserService.create方法不应该被执行
        Mockito.verify(userService, Mockito.never()).create(Mockito.any());
        // 未抛出异常，UserService.update应当执行一次
        Mockito.verify(userService, Mockito.times(1)).update(Mockito.any(), Mockito.any());
    }

}
