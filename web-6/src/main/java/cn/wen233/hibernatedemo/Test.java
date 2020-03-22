package cn.wen233.hibernatedemo;

import cn.wen233.hibernatedemo.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author: huu
 * @date: 2020/3/22 16:00
 * @description:
 */
public class Test {

    public static void main(String[] args) {
//        获取默认配置加载
        Configuration configuration = new Configuration().configure();
//        建立会话工厂
        SessionFactory factory = configuration.buildSessionFactory();
//        得到Session对象
        Session session = factory.openSession();
//        开启事务
        session.beginTransaction();

        Product product = new Product();
        product.setName("ni");
        product.setPrice(15f);

        session.save(product);
        session.getTransaction().commit();
        session.close();
//        System.out.println(Thread.currentThread().getContextClassLoader().getResource(""));
//        System.out.println(Thread.currentThread().getContextClassLoader().getResource("cn"));
//        System.out.println(Thread.currentThread().getContextClassLoader().getResource("WEB-INF"));
//        System.out.println(Thread.currentThread().getContextClassLoader().getResource("hibernate.cfg.xml"));
    }
}
