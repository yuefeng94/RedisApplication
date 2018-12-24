package com.demo.controller;


import com.demo.model.Order;
import com.demo.constants.OrderStatus;
import com.demo.redis.RedisService;
import com.demo.service.OrderService;
import com.demo.utils.DateUtil;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/order/")
public class OrderController {
    @Autowired
    private RedisService redisService;
    @Autowired
    private OrderService orderService;
    @RequestMapping("getOrder")
    public Object getUser() {
           Order order = new Order();
           order.setName("抢购订单");
           order.setState(OrderStatus.ORDER_ACTIVE);
           Date createTime = new Date();
           order.setCreateTime(createTime);
           order.setExpireTime(DateUtil.addTime(createTime,5));

           String result = orderService.save(order);
           redisService.set("order:" + order.getId(), order.getId().toString(), 60);
           return result;
    }

}
