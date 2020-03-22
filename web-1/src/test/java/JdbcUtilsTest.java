import com.wen233.servlet2.JdbcUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.sql.*;

/**
 * @author: huu
 * @date: 2020/3/19 13:58
 * @description:
 */
public class JdbcUtilsTest {
    @Test
    public void insert() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = JdbcUtils.getConnection();
            statement = connection.createStatement();
            String sql = "insert into users(id,name,password,email,birthday) values(5,'小明','123','uie@qq.com','2000-10-20')";
            int num = statement.executeUpdate(sql);
            if (num > 0) {
                System.out.println("插入成功");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(connection, statement, resultSet);
        }
    }

    @Test
    public void delete() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = JdbcUtils.getConnection();
            statement = connection.createStatement();
            String sql = "delete from users where id=5";
            int num = statement.executeUpdate(sql);
            if (num > 0) {
                System.out.println("插入成功");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(connection, statement, resultSet);
        }
    }

    @Test
    public void update() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = JdbcUtils.getConnection();
            statement = connection.createStatement();
            String sql = "update users set name='xiaoming',email='gacl@sina.com' where id=5";
            int num = statement.executeUpdate(sql);
            if (num > 0) {
                System.out.println("插入成功");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(connection, statement, resultSet);
        }
    }

    @Test
    public void find(){
        Connection conn = null;
//        PreperedStatement是Statement的子类，它的实例对象可以通过调用Connection.preparedStatement()方法获得，
//        相对于Statement对象而言：PreperedStatement可以避免SQL注入的问题。
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            conn = JdbcUtils.getConnection();
            String sql = "select * from users where id=?";
            st = conn.prepareStatement(sql);
            st.setInt(1, 5);
            rs = st.executeQuery();
            if(rs.next()){
                System.out.println(rs.getString("name"));
            }
        }catch (Exception e) {

        }finally{
            JdbcUtils.close(conn, st, rs);
        }
    }


}
