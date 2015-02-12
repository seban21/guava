package com.example.guava;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

/**
 * @author gimbyeongsu
 * com.google.common.base.Splitter 클래스 관련
 */
public class SplitterTest {
	private final static Logger LOGGER = Logger.getLogger(SplitterTest.class);
	
	private final static String SEPARATOR = ","; // 문자열 분리자
	private final static String SEPARATOR_MAP = ":"; // 맵형태의 문자열 분리자
	
	@Test
	public void test000() {
		LOGGER.debug("Splitter delimiter");
		
		LOGGER.debug("문자열을 SEPARATOR 기준으로 분할");
		
		String str = "aaa, bbb   ,ddd, sss";
		List<String> ids = Lists.newArrayList(Splitter.on(SEPARATOR).trimResults()
				.omitEmptyStrings().split(str));

		for (String id : ids) {
			LOGGER.debug(id);
		}
		LOGGER.debug("===========================================================================");
		LOGGER.debug("");
	}
	
	@Test
	public void test001() {
		LOGGER.debug("Splitter map");
		
		LOGGER.debug("문자열을 SEPARATOR로 분할후 SEPARATOR_MAP기준으로 Map를 만들어 냄");
		
		String str = "aaa:홍길동,bbb:일지매,ccc:이순신,ddd:광개토,eee:임꺽정";
		Map<String, String> map = Splitter.on(SEPARATOR).trimResults().withKeyValueSeparator(SEPARATOR_MAP)
				.split(str);
		
		LOGGER.debug(map.toString());
	}
}
