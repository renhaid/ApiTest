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
		//׼������ͷ��Ϣ		
		HashMap<String,String> headermap = new HashMap<String,String>();		
		headermap.put("Content-Type", "application/json"); 
		//�����postman�п��Բ�ѯ��				
		
		//����ת����Json�ַ���		
		User user = new User("Anthony","tester");		
		String userJsonString =JSON.toJSONString(user);
			
		//System.out.println(userJsonString);				
		httpResponse = restClient.post(url, userJsonString, headermap);				
		
		//��֤״̬���ǲ���200		
		int statusCode = httpResponse.getStatusLine().getStatusCode();		
		Assert.assertEquals(statusCode, RESPNSE_STATUS_CODE_201,"status code is not 201");				
		
		//������Ӧjson������name��job�ǲ����ڴ����		
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
		//����һ��HttpGet���������		
		HttpGet httpget = new HttpGet(url);		
		//ִ������,�൱��postman�ϵ�����Ͱ�ť��Ȼ��ֵ��HttpResponse�������		
		CloseableHttpResponse httpResponse = httpclient.execute(httpget);				
		//�õ�Http��Ӧ״̬�룬�����200,404,500ȥ�Ƚ�
		httpResponse.getStatusLine().getStatusCode()
		int responseStatusCode = httpResponse.getStatusLine().getStatusCode();		
		System.out.println("response status code -->"+responseStatusCode);				
		//����Ӧ���ݴ洢���ַ�������		
		String responseString = EntityUtils.toString(httpResponse.getEntity(),"UTF-8");				
		//����Json���󣬰������ַ������л���Json����	
		JSONObject responseJson = JSON.parseObject(responseString);		
		System.out.println("respon json from API-->" + responseJson);				
		//��ȡ��Ӧͷ��Ϣ,������һ������		
		Header[] headerArray = httpResponse.getAllHeaders();		
		//����һ��hashmap����ͨ��postman���Կ���������Ӧͷ��Ϣ����Key��value����ʽ����������������HashMap		
		HashMap<String, String> hm = new HashMap<String, String>();		
		//��ǿforѭ������headerArray���飬���ΰ�Ԫ����ӵ�hashmap����		
		for(Header header : headerArray) {			
		hm.put(header.getName(), header.getValue());		
		}				
		//��ӡhashmap		
		System.out.println("response headers -->"+ hm);
	}
}
