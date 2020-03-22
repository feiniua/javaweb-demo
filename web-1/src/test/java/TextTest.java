import com.wen233.servlet2.JdbcUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author: huu
 * @date: 2020/3/19 14:44
 * @description:
 */
public class TextTest {
    @Test
    public void insertText() {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        Reader reader = null;
        try{
            conn = JdbcUtils.getConnection();
            String sql = "insert into testclob(resume) values(?)";
            st = conn.prepareStatement(sql);
            String path = "E:\\IdeaProjects\\demo-web\\web-1\\src\\main\\resources\\data.txt";
            File file = new File(path);
            reader = new FileReader(file);
            st.setCharacterStream(1, reader,(int) file.length());
            int num = st.executeUpdate();
            if(num>0){
                System.out.println("插入成功！！");
            }
            //关闭流
            reader.close();
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
            String sql = "select resume from testclob where id=1";
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();

            String contentStr ="";
            String content = "";
            if(rs.next()){
                //使用resultSet.getString("字段名")获取大文本数据的内容
                content = rs.getString("resume");
                //使用resultSet.getCharacterStream("字段名")获取大文本数据的内容
                Reader reader = rs.getCharacterStream("resume");
                char buffer[] = new char[1024];
                int len = 0;
                FileWriter out = new FileWriter("D:\\1.txt");
                while((len=reader.read(buffer))>0){
                    contentStr += new String(buffer);
                    out.write(buffer, 0, len);
                }
                out.close();
                reader.close();
            }
            System.out.println(content);
            System.out.println("-----------------------------------------------");
            System.out.println(contentStr);
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            JdbcUtils.close(conn, st, rs);
        }
    }
}
