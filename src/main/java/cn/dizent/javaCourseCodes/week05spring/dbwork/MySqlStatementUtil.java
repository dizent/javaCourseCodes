package cn.dizent.javaCourseCodes.week05spring.dbwork;

import java.sql.*;
import java.util.*;

/**
 * @Auther: 布谷
 * @Date: 2021/7/25 15:49
 * @Description:
 */
public enum MySqlStatementUtil {
    /**
     * 单例
     */
    INSTANCE;

    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/geektime", "dizent", "dizent");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public int insert() throws SQLException {
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            String sql = "insert into student values (3,'布匹')";
            int rs = statement.executeUpdate(sql);
            return rs;
        } finally {
            connection.close();
        }
    }

    public int delete() throws SQLException {
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            String sql = "delete from student where id = 3";
            int rs = statement.executeUpdate(sql);
            return rs;
        } finally {
            connection.close();
        }
    }

    public int update() throws SQLException {
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            String sql = "update student set name = '瀑布' where id = 3";
            int rs = statement.executeUpdate(sql);
            return rs;
        } finally {
            connection.close();
        }
    }

    public List<LinkedHashMap<String, Object>> select() throws SQLException {
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            String sql = "select * from student";
            ResultSet rs = statement.executeQuery(sql);
            List<LinkedHashMap<String, Object>> result = new ArrayList<>();
            while (rs.next()) {
                LinkedHashMap<String, Object> resultObj = new LinkedHashMap<>();
                int columnCount = rs.getMetaData().getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    resultObj.put(rs.getMetaData().getColumnName(i), rs.getObject(i));
                }
                result.add(resultObj);
            }
            return result;
        } finally {
            connection.close();
        }
    }
}
