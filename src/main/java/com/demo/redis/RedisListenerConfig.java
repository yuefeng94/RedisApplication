package com.demo.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: demo
 * @Date: 2018/12/21
 * @Description: 添加监听器相关配置以及订阅的频道主题
 * @Version: 1.0
 */
@Configuration
@PropertySource("classpath:channel.properties")
public class RedisListenerConfig {

    @Value("${channelName}")
    private String channelName;
    /**
     * 配置处理消息的消息监听适配器
     */
    @Bean
    public MessageListenerAdapter messageListenerAdapter(RedisKeyExpireListener redisKeyExpireListener) {
        /**
         * 构造方法注入：自定义的消息监听
         */
        MessageListenerAdapter adapter = new MessageListenerAdapter(redisKeyExpireListener);
        return adapter;
    }
    /**
     * 配置消息监听者容器：对所有的消息进行统一管理
     */
    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory connectionFactory, MessageListenerAdapter adapter1, ChannelTopic channelTopic1) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        Map map = new HashMap<MessageListenerAdapter, List<ChannelTopic>>();
        List<ChannelTopic> channelTopicList = new ArrayList<>();
        channelTopicList.add(channelTopic1);
        map.put(adapter1,channelTopicList);//将此频道中的内容交由此监听器处理
        container.setMessageListeners(map);
        return  container;
    }
    /**
     *  配置频道
     * 	ChannelTopic：订阅的消息频道
     *  __keyevent@0__:expired  配置订阅的主题名称
     *    （1）此名称是redis提供的名称，标识过期key消息通知
     * 	  （2）0表示db0 根据自己的dbindex选择合适的数字
     * #  Example 2: to get the stream of the expired keys subscribing to
     * #  channel name __keyevent@0__:expired use:
     *    notify-keyspace-events Ex
     * @return
     */
    @Bean
    public ChannelTopic channelTopic() {
        ChannelTopic topic = new ChannelTopic(this.channelName);
        return topic;
    }
}
