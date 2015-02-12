package com.example.guava;

import java.sql.Connection;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;

import com.example.db.DummyData;
import com.example.db.MemberDao;

/**
 * @author gimbyeongsu
 *
 */
public class CommonMember {
	private final static Logger LOGGER = Logger.getLogger(CommonMember.class);
	public MemberDao dao = new MemberDao();
	private static boolean isBeforeOne = true;
	
	@Before
	public void before() {
		if (isBeforeOne == true) {
			isBeforeOne = false;
			
			dao.delete();
			
			Connection conn = dao.getSession();

			LOGGER.debug("insert start");
			for (int i = 0, size = DummyData.MEMBER_IDS.length; i < size; ++i) {
				String id = DummyData.MEMBER_IDS[i];
				String name = DummyData.MEMBER_NAMES[i];
				String password = DummyData.MEMBER_PASSWORDS[i];
				int age = DummyData.MEMBER_AGES[i];

				dao.insert(conn, id, name, password, age);
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
