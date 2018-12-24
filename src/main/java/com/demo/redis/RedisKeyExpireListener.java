package com.demo.redis;

import com.demo.model.Order;
import com.demo.constants.OrderStatus;
import com.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;

/**
 * @Author: demo
 * @Date: 2018/12/21
 * @Description: 配置redis监听类
 * @Version: 1.0
 */
@Repository
@Transactional
public class RedisKeyExpireListener implements MessageListener {
    /**
     * 	处理redis消息：当从redis中获取消息后，打印主题名称和基本的消息
     */
    @Autowired
    private OrderService orderService;
    @Override
    public void onMessage(Message message, byte[] bytes) {
        String channel = new String(message.getChannel());
        String content = new String(message.getBody());
        System.out.println("从channel为【" + channel + "】中获取了一条消息，消息内容为：【" + content + "】");
        if (!StringUtils.isEmpty(content) && content.startsWith("order:")) {
            //content = order:26
            String orderId = content.split("\\:")[1];
            Order order = orderService.findById(Long.parseLong(orderId));
            order.setState(OrderStatus.ORDER_UNACTIVE);
            boolean flag = orderService.updateOrderStatus(order);
            if (flag) {
                System.out.println("恭喜你，过期操作成功了");
            } else {
                System.out.println("对不起，过期操作失败了");
            }
        }
    }
}
