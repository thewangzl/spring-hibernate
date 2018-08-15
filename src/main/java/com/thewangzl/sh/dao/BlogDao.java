package com.thewangzl.sh.dao;

import java.util.List;
import java.util.Map;

import com.thewangzl.sh.domain.Blog;

public interface BlogDao {

	public void save(Blog blog);
	
	public List<Blog> findAll();

	List<Map<String, Object>> findAllByJdbc();
}
