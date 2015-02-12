package com.example.guava;

import java.util.Random;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.google.common.collect.Range;
import com.google.common.collect.RangeMap;
import com.google.common.collect.TreeRangeMap;

/**
 * @author gimbyeongsu
 *
 */
public class RangeMapTest {
	private final static Logger LOGGER = Logger.getLogger(RangeMapTest.class);
	private final static int RAND_MIN = 1;
	private final static int RAND_MAX = 1000;

	@Test
	public void test000() {
		LOGGER.debug("RangeMap");

		Random rand = new Random();

		RangeMap<Integer, String> itemMap = TreeRangeMap.create();
		itemMap.put(Range.closed(RAND_MIN, 2), "ITEM_A");
		itemMap.put(Range.closed(3, 100), "ITEM_B");
		itemMap.put(Range.closed(101, 200), "ITEM_C");
		itemMap.put(Range.closed(201, 500), "ITEM_D");
		itemMap.put(Range.closed(501, RAND_MAX), "ITEM_E");

		int randValue = rand.nextInt(RAND_MAX) + 1;

		String item = itemMap.get(randValue);
		LOGGER.debug("randValue:" + randValue + " item:" + item);
	}
}
