package com.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class People {
	
	@Test
	public void test(){
			String str="4123456";
			byte[] str1=str.getBytes();
			for(byte s:str1){
				System.out.print(s+" ");
			}
			System.out.println();
			List<Integer> list=new ArrayList<>();
			for(byte s:str1){
				list.add((int)s);
			}
			for(int i :list){
				System.out.print(i+",");
			}
	}

}
