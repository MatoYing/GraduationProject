package com.zust.ysc.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @Description
 * @Author 闫思潮
 * @Date 20/05/2023 3:26 pm
 */

@Mapper
@Repository
public interface TestDao {

    @Select("SELECT phone FROM user WHERE name = #{name}")
    String getPhone(@Param("name") String name);
}
