package com.example.guava;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.google.common.base.Strings;

/**
 * @author gimbyeongsu
 * com.google.common.base.Strings 클래스 관련
 */
public class StringsTest {
	private final static Logger LOGGER = Logger.getLogger(StringsTest.class);
	
	@Test
	public void test000() {
		LOGGER.debug("Strings isNullOrEmpty");
		
		// 널 체크
		String str1 = null;
		if (Strings.isNullOrEmpty(str1)) {
			LOGGER.debug("str1 null");
		}
		
		// 빈 문자열 체크
		String str2 = "";
		if (Strings.isNullOrEmpty(str2)) {
			LOGGER.debug("str2 empty");
		}
	}
}
