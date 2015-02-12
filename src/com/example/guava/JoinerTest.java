package com.example.guava;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.google.common.base.Joiner;

/**
 * @author gimbyeongsu
 * com.google.common.base.Joiner 클래스 관련
 */
public class JoinerTest extends CommonMember {
	private final static Logger LOGGER = Logger.getLogger(JoinerTest.class);
	
	private final static String SEPARATOR = ",";
	
	@Test
	public void test000() {
		LOGGER.debug("Joiner 만들기");
		
		LOGGER.debug("문자열 리스트 값을 가져온후 SEPARATOR 문자열을 연결함");
		
		List<String> ids = dao.listIdAll();
		String idGroups = Joiner.on(SEPARATOR).join(ids);
		
		LOGGER.debug(idGroups);
	}
}
