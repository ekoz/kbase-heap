/*
 * Powered by http://ibotstat.com
 */
package com.eastrobot.heap.mq.E06_Boot集成.config;

import com.eastrobot.heap.mq.RabbitConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.Resource;
import java.nio.charset.StandardCharsets;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/4/25 14:42
 */
@Configuration
@Slf4j
public class RabbitConfig {

    @Resource
    private CachingConnectionFactory connectionFactory;

    /**
     * 声明交换机
     * @return
     */
    @Bean
    FanoutExchange fanoutExchange() {
        // 参数1为交换机的名称
        return new FanoutExchange(RabbitConstant.EXCHANGE_BOOT);
    }

    /**
     * 声明队列
     * @return
     */
    @Bean("smsQueue")
    Queue smsQueue(){
        return new Queue(RabbitConstant.QUEUE_SMS);
    }
    @Bean("emailQueue")
    Queue emailQueue(){
        return new Queue(RabbitConstant.QUEUE_EMAIL);
    }

    /**
     * 绑定交换机和队列
     * @param smsQueue
     * @param fanoutExchange
     * @return
     */
    @Bean
    Binding bindingExchangeSms(@Qualifier("smsQueue") Queue smsQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(smsQueue).to(fanoutExchange);
    }
    @Bean
    Binding bindingExchangeEmail(@Qualifier("emailQueue") Queue emailQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(emailQueue).to(fanoutExchange);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(){
        // 若使用confirm-callback或return-callback，必须要配置publisherConfirms或publisherReturns为true
        // 每个rabbitTemplate只能有一个confirm-callback和return-callback，如果这里配置了，那么写生产者的时候不能再写confirm-callback和return-callback
        // 使用return-callback时必须设置mandatory为true，或者在配置中设置mandatory-expression的值为true
//        connectionFactory.setPublisherConfirms(true);
        connectionFactory.setPublisherReturns(true);
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMandatory(true);
        /*
         * 如果消息没有到exchange,则confirm回调,ack=false
         * 如果消息到达exchange,则confirm回调,ack=true
         * exchange到queue成功,则不回调return
         * exchange到queue失败,则回调return(需设置mandatory=true,否则不回回调,消息就丢了)
         */
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                String message = "";
                if (correlationData!=null){
                    message = new String(correlationData.getReturned().getMessage().getBody(), StandardCharsets.UTF_8);
                }
                if(ack){
                    log.info("消息发送成功: correlationData({}),ack({}),cause({})", message, ack, cause);
                }else{
                    log.info("消息发送失败: correlationData({}),ack({}),cause({})", message, ack, cause);
                }
            }
        });
        rabbitTemplate.setReturnsCallback(new RabbitTemplate.ReturnsCallback() {
            @Override
            public void returnedMessage(ReturnedMessage returned) {
                log.info("消息丢失: {}", returned.toString());
            }
        });
        return rabbitTemplate;
    }
}
