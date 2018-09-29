package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
