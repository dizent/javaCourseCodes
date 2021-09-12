package cn.dizent.mq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Queue;
import javax.jms.Topic;
import java.util.Map;

/**
 * @Auther: 布谷
 * @Date: 2021/9/12 15:04
 * @Description:
 */
@RestController
@RequestMapping("publish")
public class PublisherController {

    @Autowired
    private JmsMessagingTemplate jms;

    @Autowired
    private Queue queue;

    @Autowired
    private Topic topic;

    @GetMapping("msg")
    public String publishMsg(String context){
        jms.convertAndSend(queue,context);
        return "消息发送完成";
    }

    @GetMapping("pubTopic")
    public String publishTopicMsg(String context){
        jms.convertAndSend(topic,context);
        return "Topic消息发送完成";
    }
}
