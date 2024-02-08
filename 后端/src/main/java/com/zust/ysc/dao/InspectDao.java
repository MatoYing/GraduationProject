package com.zust.ysc.dao;

import com.zust.ysc.entity.Inspect;
import org.apache.ibatis.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description
 * @Author 闫思潮
 * @Date 01/05/2023 2:00 pm
 */

@Mapper
@Repository
public interface InspectDao {

    @Select("SELECT * FROM inspect WHERE people = #{name}")
    List<Inspect> getInspectForCalendar(@Param("name") String name);

    @Insert("INSERT INTO inspect(author, title, description, people, start) VALUES(#{author}, #{title}, #{description}, #{people}, #{start})")
    void addInspect(@Param("author") String author, @Param("title") String title, @Param("description") String description, @Param("people") String people, @Param("start") String start);

    @Select("SELECT * FROM inspect")
    List<Inspect> getInspect();

    @Delete("DELETE FROM inspect WHERE title = #{title}")
    void deleteInspect(@Param("title") String title);

    @Update("UPDATE inspect SET end_date = NULL WHERE title = #{title}")
    void deleteInspectForEndDate(@Param("title") String title);

    @Update("UPDATE inspect SET end_date = NOW(), problem = #{problem} WHERE title = #{title}")
    void submitInspect(@Param("title") String title, @Param("problem") String problem);
}
