package com.zust.ysc.utils;

import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @Description
 * @Author 闫思潮
 * @Date 08/05/2023 4:57 pm
 */

@Component
public class SocketUtil {
   public boolean healthCheck(String hostname, int port) {
      try (Socket socket = new Socket()) {
         socket.connect(new InetSocketAddress(hostname, port), 1000);
         return true;
      } catch (Exception e) {
         return false;
      }
   }

}
