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
			String rowKey = loginLog.id;
			int columnKey = loginLog.no;
			table.put(rowKey, columnKey, loginLog);
		}
		
		for (String rowKey : table.rowKeySet()) {
			LOGGER.debug("rowKey id:" + rowKey);
			for (Entry<Integer, LoginLog> row : table.row(rowKey).entrySet()) {
				int columnKey = row.getKey();
				LoginLog val = row.getValue();
				LOGGER.debug("LoginLog no:" + columnKey + " name:" + val.name + " age:" + val.age);
			}
		}
	}
}
