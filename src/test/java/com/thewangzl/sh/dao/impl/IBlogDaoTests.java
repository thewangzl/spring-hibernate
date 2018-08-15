package com.thewangzl.sh.dao.impl;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.thewangzl.sh.ShApplication;
import com.thewangzl.sh.dao.BlogDao;
import com.thewangzl.sh.domain.Blog;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=ShApplication.class)
public class IBlogDaoTests {

	@Autowired
	private BlogDao blogDao;
	
	@Test
	public void save() {
		System.out.println("begin save blog--------------------------");
		Blog blog = new Blog();
		blog.setTitle("XXXXXXX");
		blog.setContent("XXXXXXXXXXXCOntent");
		blogDao.save(blog);
		System.out.println("save blog--------------------------");
	}
	
	@Test 
	public void findAll() {
		List<Blog> blogs = blogDao.findAll();
		System.out.println(blogs);
	}
	
	@Test 
	public void findAllByJdbc() {
		List<Map<String, Object>> blogs = blogDao.findAllByJdbc();
		System.out.println(blogs);
	}
}
