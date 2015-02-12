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
 * com.google.common.collect.Multimap 클래스 관련
 */
public class MultimapTest extends CommonLoginLog {
	private final static Logger LOGGER = Logger.getLogger(MultimapTest.class);

	@Test
	public void test000() {
		LOGGER.debug("Multimap 만들기");
		
		LOGGER.debug("중복이 되는 키를 가지는 Map 생성");
		
		List<LoginLog> loginLogs = dao.listAll();
		Multimap<String, LoginLog> multimap = ArrayListMultimap.create();
		
		for (LoginLog loginLog : loginLogs) {
			String id = loginLog.id;
			multimap.put(id, loginLog);
		}
		
		// 키 검색시 여러개의 값이 리턴
		Collection<LoginLog> ids = multimap.get("aaa");
		for (LoginLog loginLog : ids) {
			LOGGER.debug(loginLog.toString());
		}
	}
}
