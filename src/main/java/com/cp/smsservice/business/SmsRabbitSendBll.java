package com.cp.smsservice.business;

import com.cp.smsservice.business.IImpl.SmsMqHelper;
import com.cp.smsservice.config.SmsRabbitMqConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;

@Component
public class SmsRabbitSendBll implements SmsMqHelper, RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {

    private Logger logger = LoggerFactory.getLogger("短信消息：：");

    @Resource(name = "smsRabbitTemplate")
    RabbitTemplate rabbitTemplate;

    private final String routingKey = "qimo.sms.routing";

    @Override
    public boolean sendMsg(String json, String msgId) {
        rabbitTemplate.setConfirmCallback(this::confirm);
        rabbitTemplate.setReturnCallback(this::returnedMessage);
        CorrelationData correlationData = new CorrelationData(msgId);

        rabbitTemplate.convertAndSend(SmsRabbitMqConfig.exchangeName, routingKey, json, correlationData);

        return true;
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        logger.info("接收到消息 {} 的确认", correlationData.getId());
        if (ack) {
            logger.info("消息 {} 确认成功", correlationData.getId());
            //// 更新消息表状态
        } else {
            logger.info("消息{} 确认失败，原因 {} 等待补偿重试", correlationData.getId(), cause);
            //// 更新消息表状态
        }
    }

    /**
     * 只有发生错误的情况下才会触发回调
     *
     * @param message
     * @param replyCode
     * @param replyText
     * @param exchage
     * @param routingKey
     */
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchage, String routingKey) {
        String msg = "";
        try {
            msg = new String(message.getBody(), "utf-8");
        }catch (UnsupportedEncodingException es) {
            logger.error(es.getStackTrace().toString());
        }
        logger.info("接收到消息回调 id{} 消息体 {} 回复码 {} 回复内容 {} 交换器 {} 路由 {} ",
                message.getMessageProperties().getHeaders().get("spring_returned_message_correlation"),
                msg, replyCode, replyText, exchage, routingKey);

        //// 更新消息表状态
    }
}
