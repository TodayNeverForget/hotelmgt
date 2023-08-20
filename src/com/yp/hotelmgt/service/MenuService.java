package com.yp.hotelmgt.service;

import com.yp.hotelmgt.dao.MenuDAO;
import com.yp.hotelmgt.domain.Menu;

import java.util.List;

/**
 * @author 杨鹏
 * @version 1.0
 */
public class MenuService {
    private MenuDAO menuDAO = new MenuDAO();


    //获取所有菜品信息
    public List<Menu> menus() {

        String sql = "select * from menu order by type";

        return menuDAO.queryMuti(sql, Menu.class);

    }

    //获取指定菜品信息
    public Menu menu(String name) {

        String sql = "select * from menu where name = ?";

        return menuDAO.querySingle(sql, Menu.class, name);

    }
















}













