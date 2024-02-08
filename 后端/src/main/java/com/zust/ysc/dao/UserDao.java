package com.zust.ysc.dao;

import com.zust.ysc.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description
 * @Author 闫思潮
 * @Date 27/02/2023 7:49 pm
 */

@Mapper
@Repository
public interface UserDao {

    /**
     * 注册用户时写入信息
     * @param ID
     * @param password
     * @param phone
     */
    void insertUser(@Param("ID") String ID, @Param("phone") String phone, @Param("password") String password);  // 参数超过一个必须加@Param

    @Insert("INSERT INTO user(ID, phone, password, QQ, DingDing) VALUES(#{ID}, #{phone}, #{password}, #{QQ}, #{DingDing})")
    void insertUser2(@Param("ID") String ID, @Param("phone") String phone, @Param("password") String password, @Param("QQ") String QQ, @Param("DingDing") String DingDing);  // 参数超过一个必须加@Param

    @Select("SELECT * FROM user WHERE phone = #{phone}")  // 用注解的方式写sql语句，不用xml文件
    User selectUserByPhone(@Param("phone") String phone);

    @Select("SELECT QQ FROM user")
    List<String> selectAllQQ();

    @Select("SELECT * FROM user WHERE QQ = #{QQ}")
    User selectUserByQQ(@Param("QQ") String QQ);

    @Update("UPDATE user SET QQ = #{QQ} WHERE ID = #{ID}")
    void updateQQ(@Param("ID") String ID, @Param("QQ") String QQ);

    @Select("SELECT DingDing FROM user")
    List<String> selectAllDingDing();

    @Select("SELECT * FROM user WHERE DingDing = #{DingDing}")
    User selectUserByDingDing(@Param("DingDing") String DingDing);

    @Update("UPDATE user SET password = #{password} WHERE phone = #{phone}")
    void updatePassword(@Param("phone") String phone, @Param("password") String password);

    @Select("SELECT * FROM user WHERE name = #{name}")
    User getUserByName(@Param("name") String name);

    @Update("UPDATE user SET name = #{name}, phone = #{phone}, email = #{email}, birthday = #{birthday}, sex = #{sex} WHERE name = #{name}")
    void updatePerson(@Param("name") String name, @Param("phone") String phone, @Param("email") String email, @Param("birthday") String birthday, @Param("sex") String sex);

    @Select("SELECT * FROM user")
    List<User> getAllPerson();

    @Insert("INSERT INTO user(ID, name, phone, email, role) VALUES(#{ID}, #{name}, #{phone}, #{email}, #{role})")
    void addPerson(@Param("ID") String ID, @Param("name") String name, @Param("phone") String phone, @Param("email") String email, @Param("role") String role);

    @Delete("DELETE FROM user WHERE name = #{name}")
    void deletePerson(@Param("name") String name);

    @Update("UPDATE user SET ID = #{ID}, name = #{name}, phone = #{phone}, email = #{email}, role = #{role} WHERE ID = #{ID}")
    void editPerson(@Param("ID") String ID, @Param("name") String name, @Param("phone") String phone, @Param("email") String email, @Param("role") String role);
}
