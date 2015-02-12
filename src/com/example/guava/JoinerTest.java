package com.example.guava;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.google.common.base.Joiner;

/**
 * @author gimbyeongsu
 *
 */
public class JoinerTest extends CommonMember {
	private final static Logger LOGGER = Logger.getLogger(JoinerTest.class);
	
	private final static String SEPARATOR = ",";
	
	@Test
	public void test000() {
		LOGGER.debug("Joiner 만들기");
		
		List<String> ids = dao.listIdAll();
		String idGroups = Joiner.on(SEPARATOR).join(ids);
		
		LOGGER.debug(idGroups);
	}
}
