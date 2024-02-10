package com.zust.ysc.dao;

import com.zust.ysc.entity.File;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description
 * @Author Github: MatoYing
 * @Date 01/05/2023 7:32 am
 */

@Mapper
@Repository
public interface FileDao {
   @Select("SELECT * FROM file")
   List<File> getFile();

   @Insert("INSERT INTO file(name, address, file, people, note) VALUES(#{name}, #{address}, #{file}, #{people}, #{note})")
   void addFile(@Param("name") String name, @Param("address") String address, @Param("file") String file, @Param("people") String people, @Param("note") String note);

   @Delete("DELETE FROM file WHERE name = #{name}")
   void deleteFile(@Param("name") String name);

   @Update("UPDATE file SET address = #{address}, file = #{file}, note = #{note} WHERE name = #{name}")
   void editFile(@Param("name") String name, @Param("address") String address, @Param("file") String file, @Param("note") String note);
}
