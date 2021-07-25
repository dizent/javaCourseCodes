package cn.dizent.javaCourseCodes.week05spring.dbwork;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @Auther: 布谷
 * @Date: 2021/7/25 22:38
 * @Description:
 */
public enum HikariMysqlUtil {
    /**
     *
     */
    INSTANCE;


    static final DataSource dataSource;

    static{
        HikariConfig configuration = new HikariConfig();
        configuration.setJdbcUrl("jdbc:mysql://localhost:3306/geektime");
        configuration.setDriverClassName("com.mysql.jdbc.Driver");
        configuration.setUsername("dizent");
        configuration.setPassword("dizent");
        configuration.setMaximumPoolSize(100);
        configuration.setAutoCommit(false);
        dataSource = new HikariDataSource(configuration);
    }



    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public int insert() {
        Connection connection = getConnection();
        int rs = 0;
        try {
            PreparedStatement statement = null;
            String sql = "insert into student values (3,'布匹')";
            statement = connection.prepareStatement(sql);
            rs = statement.executeUpdate(sql);
            connection.commit();
        } catch (SQLException throwables) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return rs;
    }

    public int delete() throws SQLException {
        Connection connection = getConnection();
        int rs = 0;
        try {
            String sql = "delete from student where id = 3";
            PreparedStatement statement = connection.prepareStatement(sql);
            rs = statement.executeUpdate(sql);
            connection.commit();
        } catch (SQLException throwables) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return rs;
    }

    public int update() throws SQLException {
        Connection connection = getConnection();
        int rs = 0;
        try {
            String sql = "update student set name = '瀑布' where id = 3";

            PreparedStatement statement = connection.prepareStatement(sql);
            rs = statement.executeUpdate(sql);
            connection.commit();

        } catch (SQLException throwables) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return rs;
    }

    public List<LinkedHashMap<String, Object>> select() throws SQLException {
        Connection connection = getConnection();
        List<LinkedHashMap<String, Object>> result = new ArrayList<>();
        try {
            String sql = "select * from student";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                LinkedHashMap<String, Object> resultObj = new LinkedHashMap<>();
                int columnCount = rs.getMetaData().getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    resultObj.put(rs.getMetaData().getColumnName(i), rs.getObject(i));
                }
                result.add(resultObj);
            }
        } catch (SQLException throwables) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return result;
    }
}
