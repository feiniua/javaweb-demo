import com.wen233.servlet2.JdbcUtils;
import org.junit.Test;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author: huu
 * @date: 2020/3/19 14:45
 * @description:
 */
public class BlobTest {
    @Test
    public void insertText() {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            conn = JdbcUtils.getConnection();
            String sql = "insert into testblob(image) values(?)";
            st = conn.prepareStatement(sql);
            String path = "E:\\IdeaProjects\\demo-web\\web-1\\src\\main\\resources\\image.png";
            File file = new File(path);
            FileInputStream inputStream = new FileInputStream(file);
            st.setBinaryStream(1, inputStream, (int)file.length());
            int num = st.executeUpdate();
            if(num>0){
                System.out.println("插入成功！！");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            JdbcUtils.close(conn, st, rs);
        }
    }

    @Test
    public void readText() {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            conn = JdbcUtils.getConnection();
            String sql = "select image from testblob where id=?";
            st = conn.prepareStatement(sql);
            st.setInt(1, 1);
            rs = st.executeQuery();

            if (rs.next()) {
                InputStream inputStream = rs.getBinaryStream("image");
                int len = 0;
                byte[] buffer = new byte[1024];

                FileOutputStream outputStream = new FileOutputStream("D:\\1.png");
                while ((len = inputStream.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, len);
                }
                inputStream.close();
                outputStream.close();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            JdbcUtils.close(conn, st, rs);
        }
    }
}
