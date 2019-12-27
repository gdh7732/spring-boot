package com.ocean;

import org.junit.Test;

public class MybatisApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void test() {
		double high = 0.0001;
		int i = 0;
		while (high < 8848) {
			high *= 2;
			i++;
		}
		System.out.println(i);
	}
}
