package com.gexingw.unittest.service;

import com.gexingw.unittest.command.UserCreateRequest;
import com.gexingw.unittest.entity.User;
import com.gexingw.unittest.exception.InvalidParamException;
import com.gexingw.unittest.feign.OldUserFeign;
import com.gexingw.unittest.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * @author GeXingW
 */
@Service
@RequiredArgsConstructor(onConstructor_ = {@Lazy})
public class UserService {

    private final UserMapper userMapper;

    private final OldUserFeign oldUserFeign;

//    public Integer save(UserCreateRequest createRequest) {
//        // 用户密码不能等于123456
//        if ("123456".equals(createRequest.getPassword())) {
//            throw new InvalidParamException("用户密码太简单了！");
//        }
//
//        // 检查用户是否存在
//        User user = userMapper.selectByUsername(createRequest.getUsername());
//        if (user == null) {
//            // 用户不存在则进行新增
//            User createValue = new User();
//            createValue.setUsername(createRequest.getUsername());
//            // 从老系统查询数据
//            User oldUser = oldUserFeign.getByUsername(createRequest.getUsername());
//            if (oldUser == null) {
//                // 如果在老系统不存在，使用默认值填充
//                createValue.setSex("未知");
//            } else {
//                // 如果在老系统存在，使用老系统用户信息进行填充
//                createValue.setBirthday(oldUser.getBirthday());
//                createValue.setSex(oldUser.getSex());
//            }
//            return userMapper.create(createValue);
//        } else {
//            // 用户存在，则按照用户名进行更新
//            // 用户密码不能包含生日
//            String formatUserBirthday = user.getBirthday().replace("-", "");
//            if (createRequest.getPassword().contains(formatUserBirthday)) {
//                throw new InvalidParamException("用户密码不能包含生日！");
//            }
//
//            User updateUser = new User();
//            updateUser.setUsername(createRequest.getUsername());
//            updateUser.setPassword(createRequest.getPassword());
//            userMapper.update(updateUser);
//        }
//        return 1;
//    }

    public Integer save(UserCreateRequest createRequest) {
        // 用户密码不能等于123456
        if ("123456".equals(createRequest.getPassword())) {
            throw new InvalidParamException("用户密码太简单了！");
        }

        // 检查用户是否存在
        User user = userMapper.selectByUsername(createRequest.getUsername());
        if (user == null) {
            // 用户不存在则进行新增
            return this.create(createRequest);
        }

        // 用户存在，则按照用户名进行更新
        // 用户密码不能包含生日
        this.update(user, createRequest);

        return user.getId();
    }

    /**
     * 新增用户
     *
     * @param createRequest 用户创建请求
     * @return 用户Id
     */
    public Integer create(UserCreateRequest createRequest) {
        // 用户不存在则进行新增
        User createValue = new User();
        createValue.setUsername(createRequest.getUsername());
        // 从老系统查询数据
        User oldUser = oldUserFeign.getByUsername(createRequest.getUsername());
        if (oldUser == null) {
            // 如果在老系统不存在，使用默认值填充
            createValue.setSex("未知");
        } else {
            // 如果在老系统存在，使用老系统用户信息进行填充
            createValue.setBirthday(oldUser.getBirthday());
            createValue.setSex(oldUser.getSex());
        }
        return userMapper.create(createValue);
    }

    /**
     * 更新用户
     *
     * @param user          用户
     * @param createRequest 更新参数
     */
    public void update(User user, UserCreateRequest createRequest) {
        String formatUserBirthday = user.getBirthday().replace("-", "");
        if (createRequest.getPassword().contains(formatUserBirthday)) {
            throw new InvalidParamException("用户密码不能包含生日！");
        }

        User updateUser = new User();
        updateUser.setUsername(createRequest.getUsername());
        updateUser.setPassword(createRequest.getPassword());
        userMapper.update(updateUser);
    }

}
