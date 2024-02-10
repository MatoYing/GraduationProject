package com.zust.ysc.controller;

import com.zust.ysc.dao.ServerDao;
import com.zust.ysc.entity.Server;
import com.zust.ysc.service.ThirdPartyService;
import com.zust.ysc.utils.SocketUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description
 * @Author Github: MatoYing
 * @Date 08/05/2023 5:09 pm
 */

@RestController
@RequestMapping("/socket")
public class SocketController {

    @Autowired
    SocketUtil socketUtil;

    @Autowired
    ServerDao serverDao;

    @Autowired
    ThirdPartyService thirdPartyService;

    @Scheduled(cron = "0 0 0 31 12 ?")
    public void testHealth() {
        List<Server> list = serverDao.selectServer();
        for (Server server : list) {
            if (!socketUtil.healthCheck(server.getHost(), Integer.parseInt(server.getPort()))) {
                thirdPartyService.sendMessageByDingDing(server.getName() + "有问题", "13333461340");
            }
        }
    }
}
