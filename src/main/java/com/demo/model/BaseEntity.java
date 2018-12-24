package com.demo.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: demo
 * @Date: 2018/12/24
 * @Description: com.demo.model
 * @Version: 1.0
 */
public class BaseEntity implements Serializable {

    private String createBy;
    private String updateBy;
    private Date createTime;
    private Date updateTime;


}
