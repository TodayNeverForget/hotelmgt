package com.yp.hotelmgt.service;


import com.yp.hotelmgt.domain.Menu;

/**
 * @author 杨鹏
 * @version 1.0
 */
public class Order {
    private DiningTableService diningTableService = new DiningTableService();
    private MenuService menuService = new MenuService();
    private  BillService billService = new BillService();

    public boolean foodOrder(Integer diningTableId, String name, Integer nums) {

        if (!diningTableService.diningTableStateChg(diningTableId, "就餐中")) {
            return false;
        }

        //获取菜品信息
        Menu menu = menuService.menu(name);

        //账单添加
        if (!billService.billAdd(menu.getId(), nums, menu.getPrice() * nums, diningTableId)) {
            return false;
        }

        return true;
    }

    public boolean checkOut(Integer diningTableId, String state) {

        if (!diningTableService.diningTableStateChg(diningTableId, "空")) {
            return false;
        }

        if (!billService.billStateChg(diningTableId, state)) {
            return false;
        }

        return true;
    }

}
