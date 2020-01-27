package com.atguigu.funding.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * t_menu
 * @author 
 */
public class Menu implements Serializable {

    private Integer id;
    //pid 对应父节点，如果 pid 为 null 那么当前节点就是根节点
    private Integer pid;
    //当前节点名称
    private String name;
    //节点对应的url地址
    private String url;
    //节点图标
    private String icon;
    //当前节点的子节点集合。防止节点在组装的时候出现空指针异常。比如： children.add()可能出现空指针异常.
    private List<Menu> children = new ArrayList<>();

    //控制节点是展开还是折叠
    private boolean open =true;//设置默认值为true

    public Menu(Integer id, Integer pid, String name, String url, String icon) {
        this.id = id;
        this.pid = pid;
        this.name = name;
        this.url = url;
        this.icon = icon;
    }

    public Menu() {
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", pid=" + pid +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }
}