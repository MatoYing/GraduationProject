package com.zust.ysc.dao;

import com.zust.ysc.entity.Alert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description
 * @Author Github: MatoYing
 * @Date 10/05/2023 12:36 am
 */

@Mapper
@Repository
public interface CommonDao {

    @Select("SELECT COUNT(*) FROM inspect WHERE end_date IS NOT NULL")
    int countInspect();

    @Select("SELECT COUNT(*) FROM alert WHERE severity = 'warning'")
    int countWarning();

    @Select("SELECT COUNT(*) FROM alert WHERE severity = 'critical'")
    int countCritical();

    @Select("SELECT COUNT(*) FROM alert WHERE severity = 'emergency'")
    int countEmergency();

    @Select("SELECT COUNT(*) FROM alert WHERE severity = 'warning' AND submit_date IS NULL")
    int countNotWarning();

    @Select("SELECT COUNT(*) FROM alert WHERE severity = 'critical' AND submit_date IS NULL")
    int countNotCritical();

    @Select("SELECT COUNT(*) FROM alert WHERE severity = 'emergency' AND submit_date IS NULL")
    int countNotEmergency();

    @Select("SELECT COUNT(*) FROM monitor")
    int countMonitor();

    @Select("SELECT COUNT(*) FROM log")
    int countLog();

    @Select("SELECT COUNT(*) FROM knowledge")
    int countKnowledge();


    @Select("SELECT COUNT(*) FROM alert WHERE DATE(start_data) = DATE_SUB(CURDATE() + '1',INTERVAL 1 DAY) AND severity = 'warning'")
    int countDay1ForWarning();
    @Select("SELECT COUNT(*) FROM alert WHERE DATE(start_data) = DATE_SUB(CURDATE() + '1',INTERVAL 2 DAY) AND severity = 'warning'")
    int countDay2ForWarning();
    @Select("SELECT COUNT(*) FROM alert WHERE DATE(start_data) = DATE_SUB(CURDATE() + '1',INTERVAL 3 DAY) AND severity = 'warning'")
    int countDay3ForWarning();
    @Select("SELECT COUNT(*) FROM alert WHERE DATE(start_data) = DATE_SUB(CURDATE() + '1',INTERVAL 4 DAY) AND severity = 'warning'")
    int countDay4ForWarning();
    @Select("SELECT COUNT(*) FROM alert WHERE DATE(start_data) = DATE_SUB(CURDATE() + '1',INTERVAL 5 DAY) AND severity = 'warning'")
    int countDay5ForWarning();



    @Select("SELECT COUNT(*) FROM alert WHERE DATE(start_data) = DATE_SUB(CURDATE() + '1',INTERVAL 1 DAY) AND severity = 'critical'")
    int countDay1ForCritical();
    @Select("SELECT COUNT(*) FROM alert WHERE DATE(start_data) = DATE_SUB(CURDATE() + '1',INTERVAL 2 DAY) AND severity = 'critical'")
    int countDay2ForCritical();
    @Select("SELECT COUNT(*) FROM alert WHERE DATE(start_data) = DATE_SUB(CURDATE() + '1',INTERVAL 3 DAY) AND severity = 'critical'")
    int countDay3ForCritical();
    @Select("SELECT COUNT(*) FROM alert WHERE DATE(start_data) = DATE_SUB(CURDATE() + '1',INTERVAL 4 DAY) AND severity = 'critical'")
    int countDay4ForCritical();
    @Select("SELECT COUNT(*) FROM alert WHERE DATE(start_data) = DATE_SUB(CURDATE() + '1',INTERVAL 5 DAY) AND severity = 'critical'")
    int countDay5ForCritical();



    @Select("SELECT COUNT(*) FROM alert WHERE DATE(start_data) = DATE_SUB(CURDATE() + '1',INTERVAL 1 DAY) AND severity = 'emergency'")
    int countDay1ForEmergency();
    @Select("SELECT COUNT(*) FROM alert WHERE DATE(start_data) = DATE_SUB(CURDATE() + '1',INTERVAL 2 DAY) AND severity = 'emergency'")
    int countDay2ForEmergency();
    @Select("SELECT COUNT(*) FROM alert WHERE DATE(start_data) = DATE_SUB(CURDATE() + '1',INTERVAL 3 DAY) AND severity = 'emergency'")
    int countDay3ForEmergency();
    @Select("SELECT COUNT(*) FROM alert WHERE DATE(start_data) = DATE_SUB(CURDATE() + '1',INTERVAL 4 DAY) AND severity = 'emergency'")
    int countDay4ForEmergency();
    @Select("SELECT COUNT(*) FROM alert WHERE DATE(start_data) = DATE_SUB(CURDATE() + '1',INTERVAL 5 DAY) AND severity = 'emergency'")
    int countDay5ForEmergency();



    @Select("SELECT COUNT(*) FROM inspect WHERE DATE_FORMAT(start, '%Y-%m-%d') = DATE_ADD(CURDATE(), INTERVAL 1 DAY)")
    int countTodayForInspect();

    @Select("SELECT COUNT(*) FROM inspect WHERE DATE_FORMAT(start, '%Y-%m-%d') = DATE_ADD(CURDATE(), INTERVAL 1 DAY) AND end_date IS NOT NULL")
    int countTodayForFinishInspect();

    @Select("SELECT COUNT(*) FROM alert WHERE DATE_FORMAT(start_data, '%Y-%m-%d') = DATE_ADD(CURDATE(), INTERVAL 1 DAY) AND severity = 'warning'")
    int countTodayForWarning();

    @Select("SELECT COUNT(*) FROM alert WHERE DATE_FORMAT(start_data, '%Y-%m-%d') = DATE_ADD(CURDATE(), INTERVAL 1 DAY) AND severity = 'critical'")
    int countTodayForCritical();

    @Select("SELECT COUNT(*) FROM alert WHERE DATE_FORMAT(start_data, '%Y-%m-%d') = DATE_ADD(CURDATE(), INTERVAL 1 DAY) AND severity = 'emergency'")
    int countTodayForEmergency();

    @Select("SELECT COUNT(*) FROM alert WHERE DATE_FORMAT(start_data, '%Y-%m-%d') = DATE_ADD(CURDATE(), INTERVAL 1 DAY) AND submit_date IS NOT NULL")
    int countTodayForFinishAlert();



    @Select("SELECT COUNT(*) FROM inspect WHERE end_date IS NOT NULL AND people = #{name}")
    int countInspectForPerson(@Param("name") String name);

    @Select("SELECT COUNT(*) FROM alert WHERE severity = 'warning' AND responsible_name = #{name}")
    int countWarningForPerson(@Param("name") String name);

    @Select("SELECT COUNT(*) FROM alert WHERE severity = 'critical' AND responsible_name = #{name}")
    int countCriticalForPerson(@Param("name") String name);

    @Select("SELECT COUNT(*) FROM alert WHERE severity = 'emergency' AND responsible_name = #{name}")
    int countEmergencyForPerson(@Param("name") String name);






    @Select("SELECT * FROM alert WHERE start_data > DATE_SUB(CURDATE(), INTERVAL 4 DAY)")
    List<Alert> getWeekAlert();







}
