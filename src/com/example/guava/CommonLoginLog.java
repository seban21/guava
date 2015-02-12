package com.example.guava;

import java.sql.Connection;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;

import com.example.db.DummyData;
import com.example.db.LoginLogDao;

/**
 * @author gimbyeongsu
 *
 */
public class CommonLoginLog {
	private final static Logger LOGGER = Logger.getLogger(CommonLoginLog.class);
	public LoginLogDao dao = new LoginLogDao();
	private static boolean isBeforeOne = true;
	
	@Before
	public void before() {
		if (isBeforeOne == true) {
			isBeforeOne = false;
			
			dao.delete();
			
			Connection conn = dao.getSession();

			LOGGER.debug("insert start");
			for (int i = 0, size = DummyData.LOGIN_LOG_IDS.length; i < size; ++i) {
				String id = DummyData.LOGIN_LOG_IDS[i];
				String name = DummyData.LOGIN_LOG_NAMES[i];
				int age = DummyData.LOGIN_LOG_AGES[i];

				dao.insert(conn, id, name, age);
			}
			LOGGER.debug("insert end");
			
			dao.commit(conn);
			
			dao.returnObject(conn);
			LOGGER.debug("===========================================================================");
		}
	}
	
	@After
	public void after() {
		LOGGER.debug("===========================================================================");
		LOGGER.debug("");
	}
}
