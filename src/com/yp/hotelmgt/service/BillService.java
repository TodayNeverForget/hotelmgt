package com.yp.hotelmgt.service;

import com.yp.hotelmgt.dao.BillDAO;
import com.yp.hotelmgt.domain.Bill;

import java.util.List;
import java.util.UUID;

/**
 * @author 杨鹏
 * @version 1.0
 */
public class BillService {
    BillDAO billDAO = new BillDAO();

    public boolean billAdd(Integer menuId, Integer nums, Double money, Integer diningTableId) {

        String uuid = UUID.randomUUID().toString();
        String sql = "insert into bill values (null, ?, ?, ?, ?, ?, now(), '未结账')";

        int rows = billDAO.update(sql, uuid, menuId, nums, money, diningTableId);

        return rows > 0;

    }

    public List<Bill> bills() {

        String sql = "select * from bill";

        return billDAO.queryMuti(sql, Bill.class);

    }


    public List<Bill> checkBill(Integer checkOutDiningTableId) {

        String sql = "select * from bill where diningTableId = ? and state = '未结账'";

        return billDAO.queryMuti(sql, Bill.class, checkOutDiningTableId);

    }

    public boolean billStateChg(Integer checkOutDiningTableId, String state) {

        String sql = "update bill set state = ? where diningTableId = ? and state = '未结账'";

        int rows = billDAO.update(sql, state, checkOutDiningTableId);

        return rows > 0;
    }



}
