package com.yp.hotelmgt.dao;

import com.yp.hotelmgt.utils.Druid_Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author 杨鹏
 * @version 1.0
 */
public class BasicDAO<T> {
    private static QueryRunner queryRunner = new QueryRunner();

    public int update(String sql, Object... parameters) {
        Connection connection = null;

        try {
            connection = Druid_Utils.getConnection();
            int rows = queryRunner.update(connection, sql, parameters);

            return rows;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Druid_Utils.closeConnection(null, null, connection);
        }

    }

    public List<T> queryMuti(String sql, Class<T> clazz, Object... parameters) {

        Connection connection = null;

        try {
            connection = Druid_Utils.getConnection();
            return queryRunner.query
                    (connection, sql, new BeanListHandler<T>(clazz), parameters);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Druid_Utils.closeConnection(null, null, connection);
        }


    }

    public T querySingle(String sql, Class<T> clazz, Object... parameters) {

        Connection connection = null;

        try {
            connection = Druid_Utils.getConnection();
            return queryRunner.query(connection, sql, new BeanHandler<T>(clazz), parameters);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Druid_Utils.closeConnection(null, null, connection);
        }

    }

    public Object queryScalar(String sql, Object... parameter) {

        Connection connection = null;

        try {
            connection = Druid_Utils.getConnection();
            return queryRunner.query(connection, sql, new ScalarHandler(), parameter);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Druid_Utils.closeConnection(null, null, connection);
        }

    }



}






















