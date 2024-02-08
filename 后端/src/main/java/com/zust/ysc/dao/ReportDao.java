package com.zust.ysc.dao;

import com.zust.ysc.entity.Report;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description
 * @Author 闫思潮
 * @Date 12/05/2023 7:36 pm
 */

@Mapper
@Repository
public interface ReportDao {

   @Insert("INSERT INTO report (name) VALUES (#{name})")
   void insertReport(@Param("name") String name);

   @Select("SELECT * FROM report")
   List<Report> getReport();
}
