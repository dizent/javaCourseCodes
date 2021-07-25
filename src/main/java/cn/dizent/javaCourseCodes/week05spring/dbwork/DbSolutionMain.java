package cn.dizent.javaCourseCodes.week05spring.dbwork;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: 布谷
 * @Date: 2021/7/25 22:05
 * @Description:
 */
public class DbSolutionMain {

    public Object mysqlOperate() {
        Map<String,Object> result = new HashMap<>();
        try {
            result.put("insert", MySqlStatementUtil.INSTANCE.insert());
            result.put("update", MySqlStatementUtil.INSTANCE.update());
            result.put("select", MySqlStatementUtil.INSTANCE.select());
            result.put("delete", MySqlStatementUtil.INSTANCE.delete());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public Object transOperate() {
        Map<String,Object> result = new HashMap<>();
        try {
            result.put("insert", MySqlPrepareStatementUtil.INSTANCE.insert());
            result.put("update", MySqlPrepareStatementUtil.INSTANCE.update());
            result.put("select", MySqlPrepareStatementUtil.INSTANCE.select());
            result.put("delete", MySqlPrepareStatementUtil.INSTANCE.delete());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public Object hikariOperate() {
        Map<String,Object> result = new HashMap<>();
        try {
            result.put("insert",HikariMysqlUtil.INSTANCE.insert());
            result.put("update",HikariMysqlUtil.INSTANCE.update());
            result.put("select",HikariMysqlUtil.INSTANCE.select());
            result.put("delete",HikariMysqlUtil.INSTANCE.delete());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new DbSolutionMain().mysqlOperate());
        System.out.println(new DbSolutionMain().transOperate());
        System.out.println(new DbSolutionMain().hikariOperate());
    }
}
