package com.thewangzl.sh.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thewangzl.sh.dao.BlogDao;
import com.thewangzl.sh.domain.Blog;
import com.thewangzl.sh.service.BlogService;

@Service
public class IBlogService implements BlogService {

	@Autowired
	private BlogDao blogDao;
	
	@Override
	public void save(Blog blog) {
		blogDao.save(blog);
	}

}
