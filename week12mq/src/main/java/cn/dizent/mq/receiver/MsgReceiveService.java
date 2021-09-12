package cn.dizent.mq.receiver;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @Auther: 布谷
 * @Date: 2021/9/12 17:26
 * @Description: Queue接收，a-b两个consumer只会有一个能消费到单条消息
 */
@Component
public class MsgReceiveService {

    @JmsListener(destination = "dizent-queue", containerFactory = "jmsListenerContainerQueue")
    public String receive(String text){
        System.out.println("QueueListener: consumer-a 收到一条信息: " + text);
        return "consumer-a received : " + text;
    }


    @JmsListener(destination = "dizent-queue", containerFactory = "jmsListenerContainerQueue")
    public String receiveb(String text){
        System.out.println("QueueListener: consumer-b 收到一条信息: " + text);
        return "consumer-b received : " + text;
    }
}
