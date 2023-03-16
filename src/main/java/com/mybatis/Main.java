package com.mybatis;

import com.mybatis.dao.UserDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.javassist.bytecode.stackmap.BasicBlock;
import org.apache.ibatis.session.*;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
    /**从 XML 中构建 SqlSessionFactory
     每个基于 MyBatis 的应用都是以一个 SqlSessionFactory 的实例为核心的。
     SqlSessionFactory 的实例可以通过 SqlSessionFactoryBuilder 获得。
     而 SqlSessionFactoryBuilder 则可以从 XML 配置文件或一个预先配置的 Configuration 实例来构建出 SqlSessionFactory 实例。
     从 XML 文件中构建 SqlSessionFactory 的实例非常简单，建议使用类路径下的资源文件进行配置。
     但也可以使用任意的输入流（InputStream）实例，比如用文件路径字符串或 file:// URL 构造的输入流。
     MyBatis 包含一个名叫 Resources 的工具类，它包含一些实用方法，使得从类路径或其它位置加载资源文件更加容易。
*/
        String resource="mybatis-config.xml";
        try {
            InputStream inputStream=Resources.getResourceAsStream(resource);
            //通过SqlSessionFactoryBuilder.build()方法创建SqlSessionFactory
            SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
            //从SqlSessionFactory获取SqlSession
            try (SqlSession sqlSession=sqlSessionFactory.openSession()){
                UserDao userDao=sqlSession.getMapper(UserDao.class);
                System.out.println( userDao.findById(1));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
