package yzoo.homeworkproject.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import yzoo.homeworkproject.domain.User;
import yzoo.homeworkproject.domain.UserInfo;

@Repository
@Mapper
public interface UserDao {
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Select("select * from user where username=#{username} and password=#{password}")
    User findByUsername(User user);

    @Select("select * from user where phonecode=#{phonecode} and password=#{password}")
    User findByPhone(User user);

    @Select("select id from user where username=#{username}")
    int findUserId(@Param("username") String username);

    // 主键回填
    @Options(useGeneratedKeys =  true, keyProperty = "id")
    @Insert("insert into user( username, email, phonecode, password) value (#{username}, #{email}, #{phonecode}, #{password})")
    int addUser(User user);

    @Select("select * from userinformation where uid = #{id}")
    UserInfo findInformationById(@Param("id") int id);

    @Select("select email, phonecode from user where id = ${id}")
    User getUserAccount(@Param("id") int id);
}
