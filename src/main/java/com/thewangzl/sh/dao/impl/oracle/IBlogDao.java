package com.thewangzl.sh.dao.impl.oracle;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.thewangzl.sh.dao.BlogDao;
import com.thewangzl.sh.domain.Blog;

@Repository
public class IBlogDao implements BlogDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void save(Blog blog) {
		System.out.println("use oracle database");
		hibernateTemplate.save(blog);
	}
	
	@Override
	public List<Blog> findAll() {
		System.out.println("use oracle database");
		return hibernateTemplate.loadAll(Blog.class);
	}

	@Override
	public List<Map<String,Object>> findAllByJdbc(){
		System.out.println("use oracle database");
		return jdbcTemplate.queryForList("select * from blog");
	}
}
