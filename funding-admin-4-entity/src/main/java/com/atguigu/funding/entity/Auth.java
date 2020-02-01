package com.atguigu.funding.entity;

import java.io.Serializable;

/**
 * t_auth
 * @author 
 */
public class Auth implements Serializable {
    private Integer id;

    private String name;

    private String title;

    private Integer categoryId;

    private static final long serialVersionUID = 1L;

    public Auth() {
    }

    public Auth(Integer id, String name, String title, Integer categoryId) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.categoryId = categoryId;
    }

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "Auth{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", categoryId=" + categoryId +
                '}';
    }
}