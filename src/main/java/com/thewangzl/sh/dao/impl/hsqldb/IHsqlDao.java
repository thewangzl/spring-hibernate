package com.thewangzl.sh.dao.impl.hsqldb;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.thewangzl.sh.dao.HsqlDao;

@Repository
public class IHsqlDao implements HsqlDao {

	@Autowired
	@Qualifier("hsqlJdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void createTable() {
		String sql = "create table person("
				+ "id integer,"
				+ "name varchar(200),"
				+ "age integer"
				+ ");";
		jdbcTemplate.execute(sql);
	}
	
	@Override
	public void save() {
		String sql = "insert into person(id, name, age) values(1,'thewangz',29);";
		jdbcTemplate.execute(sql);
	}
	
	@Override
	public List<Map<String,Object>> query(){
		String sql = "select * from person";
		return jdbcTemplate.queryForList(sql);
	}

}
