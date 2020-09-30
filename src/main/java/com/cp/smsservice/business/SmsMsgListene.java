package com.cp.smsservice.business;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;


@Component
public class SmsMsgListene extends SimpleMessageListenerContainer {
    private final Logger logger = LoggerFactory.getLogger("短信消息接收");

    public SmsMsgListene(@Qualifier("smsRabbitmqConnectionFactory") ConnectionFactory connectionFactory, @Qualifier("smsQueue") Queue queue) {
        super(connectionFactory);

        this.setExposeListenerChannel(true);

        this.setQueues(queue);

        // 并发数2
        this.setMaxConcurrentConsumers(2);

        this.setConcurrentConsumers(1);
        // 单次请求的
        this.setPrefetchCount(1);

        // 手动确认
        this.setAcknowledgeMode(AcknowledgeMode.MANUAL);

        // 设置消息监听处理
        this.setMessageListener((ChannelAwareMessageListener) (message, channel) -> {
            String msg = "";
            try {
                msg = new String(message.getBody(), "utf-8");
            } catch (UnsupportedEncodingException es) {
                logger.error(es.getStackTrace().toString());
            }

            logger.info("接收到消息，ID {} body {}",
                    message.getMessageProperties().getHeaders().get("spring_returned_message_correlation"), msg);

            // 业务逻辑处理代码
            boolean flag = true;

            if (flag) {
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            } else {
                //// 第三个参数为是否返回队列继续处理
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            }
        });
    }
}
