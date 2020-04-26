/*
 * Powered by http://ibotstat.com
 */
package com.eastrobot.heap.mq.E03_发布订阅模式;

/**
 * @author <a href="mailto:eko.z@outlook.com">eko.zhan</a>
 * @version v1.0
 * @date 2020/4/21 19:27
 */
public class SmsConsumer extends MyConsumer{

    private final static String QUEUE_EMAIL = "queue.sms";

    @Override
    public String getQueue() {
        return QUEUE_EMAIL;
    }

    public static void main(String[] args) {
        SmsConsumer emailConsumer = new SmsConsumer();
        emailConsumer.execute();
    }

}
