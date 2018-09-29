package com.apiTest.com.client;

import java.io.IOException;
import java.util.Map;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class RestClient {
	
	//1.get请求
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException{
		//创建一个可关闭的client对象
		CloseableHttpClient httpClient=HttpClients.createDefault();
		
		//创建一个HttpGet请求
		HttpGet httpGet=new HttpGet(url);
		
		//执行请求，相当于postman点击send发送，然后赋值给HttpResponse对象接收
		CloseableHttpResponse response=httpClient.execute(httpGet);
		
		return response;
	}
	
	//1.get请求，带header头信息
	public CloseableHttpResponse get(String url,Map<String,String> headmap) throws ClientProtocolException, IOException{
		//创建一个可关闭的client对象
		CloseableHttpClient httpClient=HttpClients.createDefault();
		
		//创建一个HttpGet请求
		HttpGet httpGet=new HttpGet(url);
		
		//奖头信息加载到get请求里面去
		for(Map.Entry<String, String> entry:headmap.entrySet()){
			httpGet.addHeader(entry.getKey(), entry.getValue());
		}
		
		//执行请求
		CloseableHttpResponse response=httpClient.execute(httpGet);	
		return response;
	}
	
	//2.post请求
	public CloseableHttpResponse post(String url,String entityString,Map<String,String> headermap) throws Exception{
		//创建一个可关闭的client对象
		CloseableHttpClient httpClient=HttpClients.createDefault();
		
		//创建一个post请求
		HttpPost httpPost=new HttpPost(url);
		
		//设置payLoad
		httpPost.setEntity(new StringEntity(entityString));
		
		//将头信息加载到请求中去
		for(Map.Entry<String, String> entity:headermap.entrySet()){
			httpPost.addHeader(entity.getKey(), entity.getValue());
		}
		
		//返回CloseableResponse镀锡对象
		CloseableHttpResponse response=httpClient.execute(httpPost);
		return response;
		
	}

}
