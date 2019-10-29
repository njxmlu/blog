package com.mybatis.sourceCode;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.mybatis.mapper.BlogMapper;
import com.mybatis.vo.Blog;

public class BlogMain {
	public static void main(String[] args) throws IOException {
		String resource = "mybatis-config-sourceCode.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = sqlSessionFactory.openSession();
		try {
			 select(session);
//			selectHandler(session);
//			 insert(session);
		} finally {
			session.close();
		}
	}

	public static void select(SqlSession session) {
		BlogMapper<Blog> mapper = session.getMapper(BlogMapper.class);
		// 常规方法
//		System.out.println(mapper.selectBlog3(158,"zhaohui"));
//		// Object的方法
//		System.out.println(mapper.hashCode());
//		// public default方法
//		System.out.println(mapper.defaultValue());
//		// 父接口中的方法
//		System.out.println(mapper.selectParent(158));
//		System.out.println(mapper.selectBlog(158));
		System.out.println(mapper.selectBlog3(158,"zhaohui"));
	}

	public static void selectHandler(SqlSession session) {
		BlogMapper mapper = session.getMapper(BlogMapper.class);
		// 常规方法
		MyResultHandler handler = new MyResultHandler();
		mapper.selectBlogsByHandler("zhaohui", handler);
		handler.getResult();
		// Object的方法
	}

	public static void insert(SqlSession session) throws IOException {
		BlogMapper mapper = session.getMapper(BlogMapper.class);
		Blog blog = new Blog();
		blog.setTitle("hello java");
		blog.setAuthor("zhaohui");
		blog.setContent("hello java666");
		mapper.insertBlog(blog);
		session.commit();
		System.out.println(blog.toString());
	}
}
