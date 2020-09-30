package com.cp.smsservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Configurable
@Component
@PropertySource("classpath:rabbitmq.properties")
public class SmsRabbitMqConfig {
    @Value("${sms.spring.rabbitmq.addresses}")
    private String address;
    @Value("${sms.spring.rabbitmq.username}")
    private String userName;
    @Value("${sms.spring.rabbitmq.password}")
    private String password;
    @Value("${sms.spring.rabbitmq.virtual-host}")
    private String virtualHost;
    @Value("${sms.spring.rabbitmq.publisher-confirm-type}")
    private CachingConnectionFactory.ConfirmType publishConfirmType;
    @Value("${sms.spring.rabbitmq.publisher-returns}")
    private boolean publishReturn;
    public static String exchangeName;
    private static String smsRoutingKey;
    public static String smsQueueName;

    @Value("${sms.spring.rabbitmq.exchangeName}")
    public void setExchangeName(String exchange) {
        exchangeName = exchange;
    }

    @Value("${sms.spring.rabbitmq.routingKey}")
    public void setSmsRoutingKey(String routingKey) {
        smsRoutingKey = routingKey;
    }

    @Value("${sms.spring.rabbitmq.queueName}")
    public void setSmsQueueName(String queueName) {
        smsQueueName = queueName;
    }

    /**
     * 自动建exchange
     * @return
     */
    @Bean("smsExchange")
    public TopicExchange exchange() {
        return new TopicExchange(exchangeName);
    }

    /**
     * 自动建队列
     * @return
     */
    @Bean("smsQueue")
    public Queue queue() {
        // 第二个参数设置持久化
        return new Queue(smsQueueName, true);
    }

    /**
     * 自动建绑定
     * @return
     */
    @Bean("smsBind")
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(exchange()).with(smsRoutingKey);
    }

    @Bean("smsRabbitmqConnectionFactory")
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses(address);
        connectionFactory.setUsername(userName);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(virtualHost);
        connectionFactory.setPublisherConfirmType(publishConfirmType);
        connectionFactory.setPublisherReturns(publishReturn);

        return connectionFactory;
    }

    @Bean("smsRabbitTemplate")
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        rabbitTemplate.setMandatory(true);
        return rabbitTemplate;
    }
}
