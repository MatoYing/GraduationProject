package com.zust.ysc.dao;

import com.zust.ysc.entity.CurrentUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @Description
 * @Author Github: MatoYing
 * @Date 29/04/2023 10:45 am
 */

@Mapper
@Repository
public interface CurrentUserDao {

    @Select("SELECT * FROM currentUser")
    CurrentUser getCurrentUser();
}
