package com.yp.hotelmgt.service;

import com.yp.hotelmgt.dao.DiningTableDAO;
import com.yp.hotelmgt.domain.DiningTable;
import com.yp.hotelmgt.utils.Druid_Utils;

import java.util.List;

/**
 * @author 杨鹏
 * @version 1.0
 */
public class DiningTableService {
    private DiningTableDAO diningTableDAO= new DiningTableDAO();

    //查询所有餐桌
    public List<DiningTable> diningTablesState() {

        String sql = "select id, state from diningTable";

        return diningTableDAO.queryMuti(sql, DiningTable.class);

    }

    //查询指定餐桌
    public DiningTable diningTable(Integer id) {

        String sql = "select * from diningTable where id = ?";

        return diningTableDAO.querySingle(sql, DiningTable.class, id);

    }

    //预定
    public boolean diningTableOrder(Integer id, String orderName, String orderTel) {

        String sql = "update diningTable set state = '预定中', orderName = ?, orderTel = ? where id = ?";

        int rows = diningTableDAO.update(sql, orderName, orderTel, id);

        return rows > 0;

    }

    //修改餐桌状态
    public boolean diningTableStateChg(Integer id, String state) {
        String sql = "update diningTable set state = ? where id = ?";

        int rows = diningTableDAO.update(sql, state, id);

        return rows > 0;
    }


}











