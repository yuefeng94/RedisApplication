package com.demo.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author: demo
 * @Date: 2018/12/21
 * @Description: com.demo.model
 * @Version: 1.0
 */
@Entity
@Table(name = "t_order")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "state")
    private String state;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "expire_time")
    private Date expireTime;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

}


