package com.example.guava;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.google.common.base.Preconditions;

/**
 * @author gimbyeongsu
 * com.google.common.base.Preconditions 클래스 관련
 */
public class PreconditionsTest {
	private final static Logger LOGGER = Logger.getLogger(PreconditionsTest.class);
	
	private final static int ON = 1;
	private final static int OFF = 2;
	
	@Test
	public void test000() {
		LOGGER.debug("Preconditions");
		
		LOGGER.debug("파라메타값 검증");
		
		String[] keys = {"a", "b", "c"};
		
		Preconditions.checkArgument(keys.length > 0);
		
		try {
			Preconditions.checkArgument(keys.length > 5);
		} catch (Exception e) {
			LOGGER.debug("keys.length > 5 false");
		}
		LOGGER.debug("");
		
		LOGGER.debug("파라메타값 널 체크");
		String id1 = "aaa";
		Preconditions.checkNotNull(id1);
		
		String id2 = null;
		try {
			Preconditions.checkNotNull(id2);
		} catch (Exception e) {
			LOGGER.debug("id2 null");
		}
		LOGGER.debug("");
		
		LOGGER.debug("파라메타값 상태 체크");
		int val0 = ON;
		int val1 = ON;
		Preconditions.checkState(val0 == ON || val1 == ON);
		
		int val2 = OFF;
		int val3 = OFF;
		try {
			Preconditions.checkState(val2 == ON || val3 == ON);
		} catch (Exception e) {
			LOGGER.debug("둘중에 하나는 선택 선택되어야 하는데 둘다 선택안됨");
		}
		LOGGER.debug("");
	}
}
