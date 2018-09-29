package com.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import org.junit.Test;
public class TestFile {	
	@Test
	public void test() throws IOException{
		FileInputStream file=new FileInputStream("C:\\Users\\coder\\Desktop\\’À∫≈∞….txt");
		InputStreamReader reader=new InputStreamReader(file);
		BufferedReader rea=new BufferedReader(reader);
		String str=null;
		FileOutputStream out=new FileOutputStream("C:\\Users\\coder\\Desktop\\a.txt"); 
		OutputStreamWriter wi=new OutputStreamWriter(out);
		BufferedWriter bu=new BufferedWriter(wi);
		while((str=rea.readLine())!=null){
			bu.write(str);
			System.out.println();
		}
		bu.flush();
		bu.close();
		wi.close();
		out.close();
		rea.close();
		reader.close();
		file.close();
	}

}
