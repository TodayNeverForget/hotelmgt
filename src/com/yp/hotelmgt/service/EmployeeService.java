package com.yp.hotelmgt.service;

import com.yp.hotelmgt.dao.EmployeeDAO;
import com.yp.hotelmgt.domain.Employee;

/**
 * @author 杨鹏
 * @version 1.0
 */
public class EmployeeService {
    private EmployeeDAO usersDAO = new EmployeeDAO();

    public Employee login(String empId, String pwd) {
        String sql = "select * from employee where empId = ? and pwd = md5(?)";

        return usersDAO.querySingle(sql, Employee.class, empId, pwd);

    }






}




















