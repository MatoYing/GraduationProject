package com.zust.ysc.dao;

import com.zust.ysc.entity.Monitor;
import com.zust.ysc.entity.Rule;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * @Description
 * @Author Github: MatoYing
 * @Date 14/04/2023 6:51 pm
 */

@Mapper
@Repository
public interface MonitorDao {

   @Select("SELECT * FROM monitor")
   List<Monitor> getMonitor();

   @Update("UPDATE monitor SET name = #{name}, status = #{status}, server_address = #{server_address}, file_address = #{file_address}, grafana = #{grafana}, note=#{note} WHERE name = #{name2}")
   void updateMonitor(@Param("name") String name, @Param("status") String status, @Param("server_address") String server_address, @Param("file_address") String file_address, @Param("grafana") String grafana, @Param("note") String note, @Param("name2") String name2);

   @Insert("INSERT INTO monitor(name, server_address, file_address, grafana, note, people, date, status) VALUES(#{name}, #{server_address}, #{file_address}, #{grafana}, #{note}, #{people}, #{time}, #{status})")
   void addMonitor(@Param("name") String name, @Param("server_address") String server_address, @Param("file_address") String file_address, @Param("grafana") String grafana, @Param("note") String note, @Param("people") String people, @Param("time")Timestamp time, @Param("status") String status);

   @Select("SELECT * FROM rule WHERE monitor_name = #{monitor_name}")
   List<Rule> getRules(@Param("monitor_name") String monitor_name);

   @Update("UPDATE rule SET name = #{name}, responsible_person = #{responsible_person}, status = #{status}, severity = #{severity}, note = #{note}, rule = #{rule} WHERE name = #{name2} AND monitor_name = #{monitor_name}")
   void updateRule(@Param("name") String name, @Param("responsible_person") String responsible_person, @Param("status") String status, @Param("severity") String severity, @Param("note") String note, @Param("rule") String rule, @Param("monitor_name") String monitor_name, @Param("name2") String name2);

   /////////////////////////////////////////////////////////////////////////

   @Select("SELECT * FROM log")
   List<Monitor> getMonitorForLog();

   @Update("UPDATE log SET name = #{name}, status = #{status}, server_address = #{server_address}, file_address = #{file_address}, grafana = #{grafana}, note=#{note} WHERE name = #{name2}")
   void updateMonitorForLog(@Param("name") String name, @Param("status") String status, @Param("server_address") String server_address, @Param("file_address") String file_address, @Param("grafana") String grafana, @Param("note") String note, @Param("name2") String name2);

   @Insert("INSERT INTO log(name, server_address, file_address, grafana, note, people, date, status) VALUES(#{name}, #{server_address}, #{file_address}, #{grafana}, #{note}, #{people}, #{time}, #{status})")
   void addMonitorForLog(@Param("name") String name, @Param("server_address") String server_address, @Param("file_address") String file_address, @Param("grafana") String grafana, @Param("note") String note, @Param("people") String people, @Param("time")Timestamp time, @Param("status") String status);

   @Select("SELECT * FROM logrule WHERE monitor_name = #{monitor_name}")
   List<Rule> getRulesForLog(@Param("monitor_name") String monitor_name);

   @Update("UPDATE logrule SET name = #{name}, responsible_person = #{responsible_person}, status = #{status}, severity = #{severity}, note = #{note}, rule = #{rule} WHERE name = #{name2} AND monitor_name = #{monitor_name}")
   void updateRuleForLog(@Param("name") String name, @Param("responsible_person") String responsible_person, @Param("status") String status, @Param("severity") String severity, @Param("note") String note, @Param("rule") String rule, @Param("monitor_name") String monitor_name, @Param("name2") String name2);
}
