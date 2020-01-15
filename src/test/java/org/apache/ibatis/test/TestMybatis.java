package org.apache.ibatis.test;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


/**
 * @author gaoh
 * @version 1.0
 * @date 2020/1/14 14:02
 */
public class TestMybatis {

  @Test
  public void test() {
    PooledDataSource dataSource = new PooledDataSource();
    dataSource.setDriver("com.mysql.jdbc.Driver");
    dataSource.setUsername("root");
    dataSource.setPassword("root");
    dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/mybatis?characterEncoding=utf-8");

    TransactionFactory transactionFactory = new JdbcTransactionFactory();


    Environment environment = new Environment("development", transactionFactory, dataSource);

    Configuration configuration = new Configuration(environment);
    configuration.addMapper(BlogsMapper.class);


    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);

    SqlSession sqlSession = sqlSessionFactory.openSession();

    BlogsMapper mapper = sqlSession.getMapper(BlogsMapper.class);

    List<Blogs> blogs = mapper.selectAll();
    System.out.println(blogs);
  }

  @Test
  public void test01() {
    String resource = "org/apache/ibatis/test/mybatis-config.xml";
    InputStream inputStream = null;
    try {
      inputStream = Resources.getResourceAsStream(resource);
    } catch (IOException e) {
      e.printStackTrace();
    }
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

    SqlSession sqlSession = sqlSessionFactory.openSession();

    BlogsMapper mapper = sqlSession.getMapper(BlogsMapper.class);

    List<Blogs> blogs = mapper.selectAll();
    System.out.println(blogs);
  }
}
