package com.andy.websocket.service;

import com.andy.websocket.config.Constant;
import com.andy.websocket.config.WSResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Leone
 * @since 2018-07-06
 **/
@Slf4j
@Service
public class WebSocketService {

    @Autowired
    private SimpMessagingTemplate template;

    /**
     * 广播
     * 发给所有在线用户
     *
     * @param msg
     */
    public void sendMsg(WSResponse msg) {
        template.convertAndSend(Constant.PRODUCER_PATH, msg);
    }

    /**
     * 发送给指定用户
     *
     * @param users
     * @param msg
     */
    public void send2Users(List<String> users, WSResponse msg) {
        users.forEach(userName -> template.convertAndSendToUser(userName, Constant.P2P_PUSH_PATH, msg));
    }

}
