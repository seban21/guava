package com.example.db;

import java.sql.Connection;
import java.util.Random;
import java.util.UUID;

import org.apache.log4j.Logger;

/**
 * @author gimbyeongsu
 *
 */
public class TestDataCreateMain {
	private final static Logger LOGGER = Logger.getLogger(TestDataCreateMain.class);
	
	public static void main(String[] args) {
		
		LOGGER.info("start");
		
		MemberDao dao = new MemberDao();
		dao.delete();
		
		Connection conn = dao.getSession();
		Random rand = new Random();
		
		for (int i = 0; i < 10000 ; ++i) {
			
			String name = UUID.randomUUID().toString();
			String id = UUID.randomUUID().toString();
			String password = UUID.randomUUID().toString();
			int age = rand.nextInt(20) + 20;
			
			dao.insert(conn, name, id, password, age);
		}
		
		LOGGER.info("commit start");
		dao.commit(conn);
		
		LOGGER.info("commit end");
		dao.returnObject(conn);
		LOGGER.info("end");
		
		ConnectionPoolManager.getInstance().close();
	}

}
