package com.demo.dao;


import com.demo.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: demo
 * @Date: 2018/12/20
 * @Description: com.demo.dao
 * @Version: 1.0
 */

@Repository
public interface BaseRepository extends JpaRepository<Order, Long> {

}
