package com.gexingw.unittest.mapper;

import com.gexingw.unittest.command.UserCreateRequest;
import com.gexingw.unittest.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

/**
 * @author GeXingW
 */
@Mapper
public interface UserMapper {

//    @Insert("INSERT INTO user(username, password) VALUES(#{username}, #{password})")
//    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
//    public int create(UserCreateRequest createRequest);

    @Select("SELECT * FROM user WHERE username = #{username}")
    public User selectByUsername(String username);

    @Insert("INSERT INTO user(username, password) VALUES(#{username}, #{password})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    public int create(User user);

    @Insert("UPDATE user SET username = #{username}, password = #{password} WHERE id = #{id}")
    public int update(User updateUser);

}
