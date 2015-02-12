package com.example.guava;

import java.util.Random;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.google.common.collect.Range;
import com.google.common.collect.RangeMap;
import com.google.common.collect.TreeRangeMap;

/**
 * @author gimbyeongsu
 * com.google.common.collect.Range 클래스 관련
 */
public class RangeMapTest {
	private final static Logger LOGGER = Logger.getLogger(RangeMapTest.class);
	private final static int RAND_MIN = 1; // 범위 최소
	private final static int RAND_MAX = 1000; // 범위 최대

	@Test
	public void test000() {
		LOGGER.debug("RangeMap");

		LOGGER.debug("키가 범위값을 가지는 Map");
		
		Random rand = new Random();

		// 1 ~ 1000까지의 범위값
		RangeMap<Integer, String> itemMap = TreeRangeMap.create();
		itemMap.put(Range.closed(RAND_MIN, 2), "ITEM_A");
		itemMap.put(Range.closed(3, 100), "ITEM_B");
		itemMap.put(Range.closed(101, 200), "ITEM_C");
		itemMap.put(Range.closed(201, 500), "ITEM_D");
		itemMap.put(Range.closed(501, RAND_MAX), "ITEM_E");

		// 확률에 의한 아이템 가져오기
		int randValue = rand.nextInt(RAND_MAX) + 1;

		String item = itemMap.get(randValue);
		LOGGER.debug("randValue:" + randValue + " item:" + item);
	}
}
