import com.wen233.jdbcpool.JdbcPoolUtils;
import com.wen233.servlet2.JdbcUtils;
import org.junit.Test;

import java.sql.*;

/**
 * @author: huu
 * @date: 2020/3/19 18:21
 * @description:
 */
public class PoolTest {

    @Test
    public void insert() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = JdbcPoolUtils.getConnection();
            statement = connection.createStatement();
            String sql = "insert into users(id,name,password,email,birthday) values(6,'小明','123','uie@qq.com','2000-10-20')";
            int num = statement.executeUpdate(sql);
            if (num > 0) {
                System.out.println("插入成功");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcPoolUtils.release(connection, statement, resultSet);
        }
    }

    @Test
    public void printConnectionMetaData() throws SQLException {
        Connection conn = JdbcPoolUtils.getConnection();
        DatabaseMetaData metadata = conn.getMetaData();
        //getURL()：返回一个String类对象，代表数据库的URL
        System.out.println(metadata.getURL());
        //getUserName()：返回连接当前数据库管理系统的用户名
        System.out.println(metadata.getUserName());
        //getDatabaseProductName()：返回数据库的产品名称
        System.out.println(metadata.getDatabaseProductName());
        //getDatabaseProductVersion()：返回数据库的版本号
        System.out.println(metadata.getDatabaseProductVersion());
        //getDriverName()：返回驱动驱动程序的名称
        System.out.println(metadata.getDriverName());
        //getDriverVersion()：返回驱动程序的版本号
        System.out.println(metadata.getDriverVersion());
        //isReadOnly()：返回一个boolean值，指示数据库是否只允许读操作
        System.out.println(metadata.isReadOnly());
        JdbcPoolUtils.release(conn, null, null);
    }

    @Test
    public void printPreparedStatementMetaData() throws SQLException {
        Connection conn = JdbcPoolUtils.getConnection();
        String sql = "select * from users wherer name=? and password=?";
        //将SQL预编译一下
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, "小明");
        st.setString(2, "123");
        ParameterMetaData pm = st.getParameterMetaData();
        //getParameterCount() 获得指定参数的个数
        System.out.println(pm.getParameterCount());
        //getParameterType(int param)：获得指定参数的sql类型，MySQL数据库驱动不支持
        System.out.println(pm.getParameterType(1));
        JdbcPoolUtils.release(conn, null, null);
    }

    @Test
    public void printResultSetMetaData() throws SQLException {
        Connection conn = JdbcPoolUtils.getConnection();
        String sql = "select * from account";
        PreparedStatement st  = conn.prepareStatement(sql);
        ResultSet rs = st.executeQuery();
        //ResultSet.getMetaData()获得代表ResultSet对象元数据的ResultSetMetaData对象
        ResultSetMetaData metadata = rs.getMetaData();
        //getColumnCount() 返回resultset对象的列数
        System.out.println(metadata.getColumnCount());
        //getColumnName(int column) 获得指定列的名称
        System.out.println(metadata.getColumnName(1));
        //getColumnTypeName(int column)获得指定列的类型
        System.out.println(metadata.getColumnTypeName(1));
        JdbcPoolUtils.release(conn, st, rs);
    }
}
