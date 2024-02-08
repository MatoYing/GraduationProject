package com.zust.ysc;

import cn.afterturn.easypoi.entity.ImageEntity;
import com.zust.ysc.dao.AlertDao;
import com.zust.ysc.dao.CommonDao;
import com.zust.ysc.dao.UserDao;
import com.zust.ysc.entity.Alert;
import com.zust.ysc.service.ThirdPartyService;
import com.zust.ysc.service.impl.MinIOServiceImpl;
import com.zust.ysc.utils.JfreeUtil;
import com.zust.ysc.utils.SnowflakeUtil;
import com.zust.ysc.utils.SocketUtil;
import com.zust.ysc.utils.WordUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.text.SimpleDateFormat;
import java.util.*;


@SpringBootTest
class YscApplicationTests {

//    @Autowired
//    private SnowflakeUtil snowflakeUtil;
//
//    @Autowired
//    private UserDao userDao;
//
//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;
//
//    @Test
//    void test01() {
//        System.out.println(snowflakeUtil.nextId());
//    }
//
//    @Test
//    void test02() {
//        String a = "http://39.98.60.186:5601/app/kibana#/dashboard/869ae520-f58f-11ed-9369-191a7e6cefe3?embed=true&_a=(description:'',filters:!(),fullScreenMode:!f,options:(hidePanelTitles:!f,useMargins:!t),panels:!((embeddableConfig:(),gridData:(h:15,i:'15b04dbc-4226-48f7-afc8-fdc8409d8a6a',w:24,x:0,y:0),id:cc39e940-f58b-11ed-9369-191a7e6cefe3,panelIndex:'15b04dbc-4226-48f7-afc8-fdc8409d8a6a',type:visualization,version:'7.7.1'),(embeddableConfig:(),gridData:(h:15,i:'4abdcf85-2e5a-4d6f-ba50-4be164658dc3',w:24,x:24,y:0),id:'6f2668b0-f58f-11ed-9369-191a7e6cefe3',panelIndex:'4abdcf85-2e5a-4d6f-ba50-4be164658dc3',type:visualization,version:'7.7.1')),query:(language:kuery,query:''),timeRestore:!f,title:upset,viewMode:view)&_g=(filters:!(),refreshInterval:(pause:!t,value:0),time:(from:now-15M,to:now))";
//        System.out.println(a);
//    }
//
//    @Test
//    void test03() {
//        String captcha = (String) stringRedisTemplate.opsForHash().get("spring:session:sessions:02870fcb-7da7-4ae4-8bd0-4e1e5eb13966", "sessionAttr:captcha");
//        System.out.println(captcha);
//    }
//
//    @Autowired
//    ThirdPartyService thirdPartyService;
//
//    @Test
//    void test04() {
//        thirdPartyService.sendCode(1111, "13333461340");
//    }
//
//    @Test
//    void test05() {
//        String a = "'das'";
//        a = a.replaceAll("'", "");
//        System.out.println(a);
//    }
//
//
//    @Test
//    void test06() {
//        thirdPartyService.sendMessageByDingDing("哈哈哈哈", "13333461340");
//    }
//
//    @Test
//    void test07() {
//        Map<String, Object> map = new HashMap<>();
//        map.put("status", "firing");
//        List<Map<String, String>> list = new ArrayList<>();
//        list.add(new HashMap<String, String>() {{
//            put("status", "firing");
//            put("labels", "123");
//        }});
//        map.put("alerts", list);
//        List list2 = (List) map.get("alerts");
//        System.out.println(list2.get(0));
//        System.out.println();
//    }
//
//    @Autowired
//    AlertDao alertDao;
//    @Test
//    void test08() {
//        System.out.println(alertDao.getPhoneByName("张三"));
//    }
//
//    @Test
//    void test09() {
//        HashMap<String, Object> map = new HashMap<>(4);
//
//        //模拟饼状图数据
//        HashMap<String, Integer> datas = new HashMap<>(3);
//        datas.put("一号",10);
//        datas.put("二号",20);
//        datas.put("三号",40);
//        ImageEntity imageEntity = JfreeUtil.pieChart("测试",datas, 500, 300);
//        map.put("picture", imageEntity);
//
//        //模拟其它普通数据
//        map.put("username", "张三");
//        map.put("date", "2019-10-10");
//        map.put("desc", "测试");
//        map.put("boo", true);
//
//        //模拟表格数据
//        ArrayList<HashMap<String, String>> list = new ArrayList<>(2);
//        HashMap<String, String> temp = new HashMap<>(3);
//        temp.put("sn","1");
//        temp.put("name","第一个人");
//        temp.put("age","23");
//        list.add(temp);
//        temp = new HashMap<>(3);
//        temp.put("sn","2");
//        temp.put("name","第二个人");
//        temp.put("age","24");
//        list.add(temp);
//        map.put("personlist",list);
//        //word模板相对路径、word生成路径、word生成的文件名称、数据源
//        WordUtil.exportWord("./src/main/resources/static/demo1.docx", "./src/main/resources/static/", "生成文件.docx", map);
//    }
//
//    @Autowired
//    SocketUtil socketUtil;
//
//    @Autowired
//    CommonDao commonDao;
//
//    @Autowired
//    MinIOServiceImpl minIOService;
//
//    @Test
//    void test10() {
//        HashMap<String, Object> map = new HashMap<>();
//
//        //模拟饼状图数据
//        HashMap<String, Integer> datas = new HashMap<>();
//
//        datas.put("普通报警", commonDao.countDay1ForWarning() + commonDao.countDay2ForWarning() + commonDao.countDay3ForWarning() + commonDao.countDay4ForWarning() + commonDao.countDay5ForWarning());
//        datas.put("中级报警", commonDao.countDay1ForEmergency() + commonDao.countDay2ForEmergency() + commonDao.countDay3ForEmergency() + commonDao.countDay4ForEmergency() + commonDao.countDay5ForEmergency());
//        datas.put("高级报警", commonDao.countDay1ForCritical() + commonDao.countDay2ForCritical() + commonDao.countDay3ForCritical() + commonDao.countDay4ForCritical() + commonDao.countDay5ForCritical());
//        ImageEntity imageEntity = JfreeUtil.pieChart("占比图",datas, 500, 300);
//        map.put("picture", imageEntity);
//
//        //模拟其它普通数据
//        Calendar calendar = Calendar.getInstance();
//        Date currentDate = calendar.getTime();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        String today = sdf.format(currentDate);
//        map.put("date", today);
//
//        //模拟表格数据
//        ArrayList<HashMap<String, String>> list = new ArrayList<>();
//        List<Alert> alertList = commonDao.getWeekAlert();
//        for (int i = 0; i < alertList.size(); i++) {
//            HashMap<String, String> temp = new HashMap<>();
//            temp.put("id", String.valueOf(i + 1));
//            temp.put("monitor_name", alertList.get(i).getMonitor_name());
//            temp.put("name", alertList.get(i).getName());
//            temp.put("responsible_name", alertList.get(i).getResponsible_name());
//            temp.put("start_data", sdf.format(alertList.get(i).getStart_data()));
//            temp.put("submit_date", alertList.get(i).getSubmit_date());
//            list.add(temp);
//        }
//        map.put("alertList",list);
//        System.out.println(map);
//
//        //word模板相对路径、word生成路径、word生成的文件名称、数据源
//        WordUtil.exportWord("./src/main/resources/static/demo1.docx", "./src/main/resources/static/", today + ".docx", map);
//        minIOService.uploadFile(today + ".docx", "./src/main/resources/static/" + today + ".docx");
//    }

}
