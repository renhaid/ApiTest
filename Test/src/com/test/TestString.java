package com.test;

import org.junit.Test;

public class TestString {

	@Test
	public void test() {
		String str = "123456789";
		StringBuffer s = new StringBuffer(str);
		System.out.println(s.reverse());// ×Ö·û´®·´×ª
	}
}
