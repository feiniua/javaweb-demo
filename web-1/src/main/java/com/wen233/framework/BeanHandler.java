package com.wen233.framework;

import com.wen233.framework.ResultSetHandler;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

/**
 * @ClassName: BeanHandler
 * @Description: 将结果集转换成bean对象的处理器
 * @author: 孤傲苍狼
 * @date: 2014-10-5 下午12:00:33
 *
 */
public class BeanHandler implements ResultSetHandler {
    private Class<?> clazz;

    public BeanHandler(Class<?> clazz){
        this.clazz = clazz;
    }

    @Override
    public Object handler(ResultSet rs) {
        try{
            if(!rs.next()){
                return null;
            }
            Object bean = clazz.newInstance();
            //得到结果集元数据
            ResultSetMetaData metadata = rs.getMetaData();
            int columnCount = metadata.getColumnCount();//得到结果集中有几列数据
            for(int i=0;i<columnCount;i++){
//                得到每i+1列的列名
                String coulmnName = metadata.getColumnName(i+1);
//                得到第i+1列的值
                Object coulmnData = rs.getObject(i+1);
//                反射出类上列名对应的属性
                Field f = clazz.getDeclaredField(coulmnName);
                f.setAccessible(true);
                f.set(bean, coulmnData);
            }
            return bean;
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}