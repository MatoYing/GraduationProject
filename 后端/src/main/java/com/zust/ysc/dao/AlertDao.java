package com.zust.ysc.dao;

import com.zust.ysc.entity.Alert;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description
 * @Author Github: MatoYing
 * @Date 02/05/2023 2:07 pm
 */

@Mapper
@Repository
public interface AlertDao {

    @Select("SELECT * FROM alert")
    List<Alert> getAlert();

    @Update("UPDATE alert SET submit_note = #{submit_note}, submit_date = NOW() WHERE id = #{id}")
    void submitAlert(int id, String submit_note);

    @Update("UPDATE alert SET submit_date = NULL, submit_note = NULL WHERE id = #{id}")
    void deleteAlert(@Param("id") String id);

    @Insert("INSERT INTO alert(name, responsible_name, start_data, severity, alert, monitor_name, status, executor) VALUES(#{name}, #{responsible_name}, NOW(), #{severity}, #{alert}, #{monitor_name}, #{status}, #{executor})")
    void insertAlert(@Param("name") String name, @Param("responsible_name") String responsible_name, @Param("severity") String severity, @Param("alert") String alert, @Param("monitor_name") String monitor_name, @Param("status") String status, @Param("executor") String executor);

    @Update("UPDATE alert SET status = '已解决' WHERE name = #{name} AND status = '未解决'")
    void updateAlert(@Param("name") String name);

    @Select("SELECT phone FROM user WHERE name = #{name}")
    String getPhoneByName(@Param("name") String name);


    @Select("SELECT * FROM alert WHERE executor IS NULL")
    List<Alert> getAlertFromMonitor();

    @Update("UPDATE alert SET note = #{note} WHERE id = #{id}")
    void updateNote(@Param("id") int id, @Param("note") String note);

    @Select("SELECT * FROM alert WHERE executor = '1'")
    List<Alert> getAlertFromLog();

    @Insert("INSERT INTO rule(name, responsible_person, severity, rule, note, add_people, monitor_name, date, status, number) VALUES(#{name}, #{responsible_name}, #{severity}, #{rule}, #{note}, #{add_people}, #{monitor_name}, NOW(), '运行', 0)")
    void insertRule(@Param("name") String name, @Param("responsible_name") String responsible_name, @Param("severity") String severity, @Param("rule") String rule, @Param("note") String note, @Param("add_people") String people, @Param("monitor_name") String monitor_name);

    @Delete("DELETE FROM rule WHERE name = #{name}")
    void deleteRule(@Param("name") String name);

    @Insert("INSERT INTO logrule(name, responsible_person, severity, rule, note, add_people, monitor_name, date, status, number) VALUES(#{name}, #{responsible_name}, #{severity}, #{rule}, #{note}, #{add_people}, #{monitor_name}, NOW(), '运行', 0)")
    void insertRuleLog(@Param("name") String name, @Param("responsible_name") String responsible_name, @Param("severity") String severity, @Param("rule") String rule, @Param("note") String note, @Param("add_people") String people, @Param("monitor_name") String monitor_name);
}
