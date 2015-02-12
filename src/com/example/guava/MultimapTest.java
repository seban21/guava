package com.example.guava;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.example.db.LoginLog;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

/**
 * @author gimbyeongsu
 *
 */
public class MultimapTest extends CommonLoginLog {
	private final static Logger LOGGER = Logger.getLogger(MultimapTest.class);

	@Test
	public void test000() {
		LOGGER.debug("Multimap 만들기");
		
		List<LoginLog> loginLogs = dao.listAll();
		Multimap<String, LoginLog> multimap = ArrayListMultimap.create();
		
		for (LoginLog loginLog : loginLogs) {
			String id = loginLog.id;
			multimap.put(id, loginLog);
		}
		
		Collection<LoginLog> ids = multimap.get("aaa");
		for (LoginLog loginLog : ids) {
			LOGGER.debug(loginLog.toString());
		}
	}
}
