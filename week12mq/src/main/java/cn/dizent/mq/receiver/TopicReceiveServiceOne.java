package cn.dizent.mq.receiver;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @Auther: 布谷
 * @Date: 2021/9/12 17:32
 * @Description: Topic 订阅，1-2 两个consumer都会收到相同的一条消息
 */
@Component
public class TopicReceiveServiceOne {

    @JmsListener(destination = "dizent-topic", containerFactory = "jmsListenerContainerTopic")
    public String receive(String text){
        System.out.println("TopicListener: consumer-1 收到一条广播信息: " + text);
        return "consumer-1 received : " + text;
    }

    @JmsListener(destination = "dizent-topic", containerFactory = "jmsListenerContainerTopic")
    public String receiveTwo(String text){
        System.out.println("TopicListener: consumer-2 收到一条广播信息: " + text);
        return "consumer-2 received : " + text;
    }
}
