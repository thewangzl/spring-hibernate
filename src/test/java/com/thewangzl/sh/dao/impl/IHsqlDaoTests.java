package com.thewangzl.sh.dao.impl;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.thewangzl.sh.ShApplication;
import com.thewangzl.sh.dao.HsqlDao;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=ShApplication.class)
public class IHsqlDaoTests {

	@Autowired
	private HsqlDao hsqlDao;
	
	@Test
	public void createTable() {
		hsqlDao.createTable();
	}
	
	@Test
	public void save() {
		hsqlDao.save();
	}
	
	@Test
	public void query() {
		List<Map<String, Object>> list = hsqlDao.query();
		System.out.println(list);
	}
}
