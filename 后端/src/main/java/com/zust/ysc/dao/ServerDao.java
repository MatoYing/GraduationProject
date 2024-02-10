package com.zust.ysc.dao;

import com.zust.ysc.entity.Server;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description
 * @Author Github: MatoYing
 * @Date 08/05/2023 5:20 pm
 */

@Mapper
@Repository
public interface ServerDao {
   @Select("SELECT * FROM server")
   List<Server> selectServer();
}
