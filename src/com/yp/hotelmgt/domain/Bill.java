package com.yp.hotelmgt.domain;

import java.time.DateTimeException;
import java.util.Date;

/**
 * @author 杨鹏
 * @version 1.0
 */
public class Bill {
    private Integer id;
    private String billId;
    private String menuId;
    private Integer nums;
    private Double money;
    private Integer diningTableId;
    private String billDate;
    private String state;

    public Bill() {
    }

    public Bill(Integer id, String billId, String menuId, Integer nums, Double money, Integer diningTableId, String billDate, String state) {
        this.id = id;
        this.billId = billId;
        this.menuId = menuId;
        this.nums = nums;
        this.money = money;
        this.diningTableId = diningTableId;
        this.billDate = billDate;
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public Integer getNums() {
        return nums;
    }

    public void setNums(Integer nums) {
        this.nums = nums;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Integer getDiningTableId() {
        return diningTableId;
    }

    public void setDiningTableId(Integer diningTableId) {
        this.diningTableId = diningTableId;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return  id + "\t\t"
                + menuId + "\t\t"
                + nums + "\t\t"
                + money + (money < 10 ? "\t\t" : "\t")
                + diningTableId + "\t\t"
                + billDate + "\t\t\t"
                + state;
    }
}





















