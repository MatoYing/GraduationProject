package com.zust.ysc.dao;

import com.zust.ysc.entity.knowledge;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * @Description
 * @Author Github: MatoYing
 * @Date 29/04/2023 11:05 am
 */

@Mapper
@Repository
public interface KnowledgeDao {

   @Select("SELECT * FROM knowledge")
   List<knowledge> getKnowledge();

   @Insert("INSERT INTO knowledge(name, content, date, people) VALUES(#{name}, #{content}, #{date}, #{people})")
   void addKnowledge(@Param("name") String name, @Param("content") String content, @Param("date") Timestamp date, @Param("people") String people);

   @Delete("DELETE FROM knowledge WHERE name = #{name}")
   void deleteKnowledge(@Param("name") String name);


}
