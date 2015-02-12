package com.example.guava;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.google.common.base.Strings;

/**
 * @author gimbyeongsu
 *
 */
public class StringsTest {
	private final static Logger LOGGER = Logger.getLogger(StringsTest.class);
	
	@Test
	public void test000() {
		LOGGER.debug("Strings isNullOrEmpty");
		
		String str1 = null;
		if (Strings.isNullOrEmpty(str1)) {
			LOGGER.debug("str1 null");
		}
		
		String str2 = "";
		if (Strings.isNullOrEmpty(str2)) {
			LOGGER.debug("str2 empty");
		}
	}
}
