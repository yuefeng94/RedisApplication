package com.demo.service;

import com.demo.model.Order;
import com.demo.dao.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


/**
 * @Author: demo
 * @Date: 2018/12/20
 * @Description: com.demo.service
 * @Version: 1.0
 */

@Service
@Transactional
public class OrderService {

    @Autowired
    private BaseRepository repository;

    public String save(Order order) {
        repository.save(order);
        return "SUCCESS";
    }

    public Order findById(Long id) {
       return repository.getOne(id);
    }

    public Boolean updateOrderStatus(Order order) {
        repository.saveAndFlush(order);
        return true;
    }
}
