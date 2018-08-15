package com.thewangzl.sh.controller;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thewangzl.sh.domain.Blog;
import com.thewangzl.sh.service.BlogService;

@Controller
@RequestMapping("blog")
public class BlogController {

	@Autowired
	private BlogService blogService;
	
	@RequestMapping("save")
	@ResponseBody
	public Blog save() {
		
		System.out.println("begin save blog--------------------------");
		Blog blog = new Blog();
		blog.setTitle(RandomStringUtils.randomNumeric(4).toString());
		blog.setContent(RandomStringUtils.randomNumeric(40).toString());
		blogService.save(blog);
		System.out.println("save blog--------------------------"); 
		return blog;
	}
}
