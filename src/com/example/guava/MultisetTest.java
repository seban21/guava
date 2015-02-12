package com.example.guava;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

/**
 * @author gimbyeongsu
 *
 */
public class MultisetTest extends CommonLoginLog {
	private final static Logger LOGGER = Logger.getLogger(MultisetTest.class);
	
	@Test
	public void test000() {
		LOGGER.debug("Multiset 만들기");
		
		LOGGER.debug("중복이 되는 Set");
		
		List<String> loginLogIds = dao.listIdAll();
		Multiset<String> idMultiset = HashMultiset.create();
		
		for (String id : loginLogIds) {
			idMultiset.add(id);
		}
		
		LOGGER.debug("count:" + idMultiset.count("aaa"));
	}
}
