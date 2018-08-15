package com.thewangzl.sh.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.thewangzl.sh.ShApplication;
import com.thewangzl.sh.domain.Blog;
import com.thewangzl.sh.service.BlogService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=ShApplication.class)
public class IBlogServiceTests{

	@Autowired
	private BlogService blogService;
	
	@Test
	public void save() {
		System.out.println("begin save blog--------------------------");
		Blog blog = new Blog();
		blog.setTitle("XXXXXXX");
		blog.setContent("XXXXXXXXXXXCOntent");
		blogService.save(blog);
		System.out.println("save blog--------------------------");
	}
	
	
}
