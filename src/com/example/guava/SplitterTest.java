package com.example.guava;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

/**
 * @author gimbyeongsu
 *
 */
public class SplitterTest {
	private final static Logger LOGGER = Logger.getLogger(SplitterTest.class);
	
	private final static String SEPARATOR = ",";
	
	@Test
	public void test000() {
		LOGGER.debug("Splitter delimiter");
		
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
		
		String str = "aaa:홍길동,bbb:일지매,ccc:이순신,ddd:광개토,eee:임꺽정";
		Map<String, String> map = Splitter.on(SEPARATOR).trimResults().withKeyValueSeparator(":")
				.split(str);
		
		LOGGER.debug(map.toString());
	}
}
