package com.apiTest.test;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.TestNG;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.reporters.jq.TestPanel;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.apiTest.com.base.TestBase;
import com.apiTest.com.client.RestClient;
import com.apiTest.com.pojo.User;


public class PostApiTest extends TestBase{
	private static final Object RESPNSE_STATUS_CODE_201 = null;
	TestBase testBase;
	String host;
	String url;
	RestClient restClient;
	CloseableHttpResponse httpResponse;
	
	@BeforeClass
	public void setUp(){
		testBase = new TestBase();
		host = pro.getProperty("HOST");
		url = host + "/api/users";
	}
	
	@Test	
	public void postApiTest() throws ClientProtocolException, IOException {		
		restClient = new RestClient();		
		//准备请求头信息		
		HashMap<String,String> headermap = new HashMap<String,String>();		
		headermap.put("Content-Type", "application/json"); 
		//这个在postman中可以查询到				
		
		//对象转换成Json字符串		
		User user = new User("Anthony","tester");		
		String userJsonString =JSON.toJSONString(user);
			
		//System.out.println(userJsonString);				
		httpResponse = restClient.post(url, userJsonString, headermap);				
		
		//验证状态码是不是200		
		int statusCode = httpResponse.getStatusLine().getStatusCode();		
		Assert.assertEquals(statusCode, RESPNSE_STATUS_CODE_201,"status code is not 201");				
		
		//断言响应json内容中name和job是不是期待结果		
		String responseString = EntityUtils.toString(httpResponse.getEntity());		
		JSONObject responseJson = JSON.parseObject(responseString);		
		
		//System.out.println(responseString);		
		
		String name = TestUitl.getValueByJPath(responseJson, "name");		
		String job = TestUitl.getValueByJPath(responseJson, "job");		
		
		Assert.assertEquals(name, "Anthony","name is not same");		
		Assert.assertEquals(job, "tester","job is not same");			
		}
	
	
	@Test
	public void getApi() throws ClientProtocolException, IOException{
		CloseableHttpClient httpclient = HttpClients.createDefault();		
		//创建一个HttpGet的请求对象		
		HttpGet httpget = new HttpGet(url);		
		//执行请求,相当于postman上点击发送按钮，然后赋值给HttpResponse对象接收		
		CloseableHttpResponse httpResponse = httpclient.execute(httpget);				
		//拿到Http响应状态码，例如和200,404,500去比较
		httpResponse.getStatusLine().getStatusCode()
		int responseStatusCode = httpResponse.getStatusLine().getStatusCode();		
		System.out.println("response status code -->"+responseStatusCode);				
		//把响应内容存储在字符串对象		
		String responseString = EntityUtils.toString(httpResponse.getEntity(),"UTF-8");				
		//创建Json对象，把上面字符串序列化成Json对象	
		JSONObject responseJson = JSON.parseObject(responseString);		
		System.out.println("respon json from API-->" + responseJson);				
		//获取响应头信息,返回是一个数组		
		Header[] headerArray = httpResponse.getAllHeaders();		
		//创建一个hashmap对象，通过postman可以看到请求响应头信息都是Key和value得形式，所以我们想起了HashMap		
		HashMap<String, String> hm = new HashMap<String, String>();		
		//增强for循环遍历headerArray数组，依次把元素添加到hashmap集合		
		for(Header header : headerArray) {			
		hm.put(header.getName(), header.getValue());		
		}				
		//打印hashmap		
		System.out.println("response headers -->"+ hm);
	}
}
