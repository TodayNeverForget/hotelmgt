package com.yp.hotelmgt.utils;

import java.sql.Connection;

/**
 * @author 杨鹏
 * @version 1.0
 */
public class Test {
    public static void main(String[] args) {

        String s = Utility.readString(10);

        Connection connection = Druid_Utils.getConnection();

    }
}
