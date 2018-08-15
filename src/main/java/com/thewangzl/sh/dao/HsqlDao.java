package com.thewangzl.sh.dao;

import java.util.List;
import java.util.Map;

public interface HsqlDao {

	public void createTable();

	void save();

	List<Map<String, Object>> query();
	
}
