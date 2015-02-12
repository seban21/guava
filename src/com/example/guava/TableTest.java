package com.example.guava;

import java.util.Map.Entry;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.example.db.LoginLog;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

/**
 * @author gimbyeongsu
 * com.google.common.collect.Table 클래스 관련
 */
public class TableTest extends CommonLoginLog {
	private final static Logger LOGGER = Logger.getLogger(TableTest.class);
	
	@Test
	public void test000() {
		LOGGER.debug("Table 만들기");
		
		LOGGER.debug("테이블 형태의 collection");
		
		List<LoginLog> loginLogs = dao.listAll();
		Table<String, Integer, LoginLog> table = HashBasedTable.create();
		
		for (LoginLog loginLog : loginLogs) {
			String id = loginLog.id;
			int no = loginLog.no;
			table.put(id, no, loginLog);
		}
		
		for (String key : table.rowKeySet()) {
			LOGGER.debug("id:" + key);
			for (Entry<Integer, LoginLog> row : table.row(key).entrySet()) {
				LoginLog loginLog = row.getValue();
				LOGGER.debug("LoginLog no:" + row.getKey() + " name:" + loginLog.name + " age:"
						+ loginLog.age);
			}
		}
	}
}
