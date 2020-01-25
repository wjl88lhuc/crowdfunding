package com.atguigu.funding.entity;

import java.io.Serializable;

/**
 * t_role
 * @author 
 */
public class Role implements Serializable {
    private Integer id;

    private String name;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Role() {
    }

    public Role(Integer id, String name) {
        this.id = id;
        this.name = name;
    }



    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}