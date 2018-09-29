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
	
	//1.get����
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException{
		//����һ���ɹرյ�client����
		CloseableHttpClient httpClient=HttpClients.createDefault();
		
		//����һ��HttpGet����
		HttpGet httpGet=new HttpGet(url);
		
		//ִ�������൱��postman���send���ͣ�Ȼ��ֵ��HttpResponse�������
		CloseableHttpResponse response=httpClient.execute(httpGet);
		
		return response;
	}
	
	//1.get���󣬴�headerͷ��Ϣ
	public CloseableHttpResponse get(String url,Map<String,String> headmap) throws ClientProtocolException, IOException{
		//����һ���ɹرյ�client����
		CloseableHttpClient httpClient=HttpClients.createDefault();
		
		//����һ��HttpGet����
		HttpGet httpGet=new HttpGet(url);
		
		//��ͷ��Ϣ���ص�get��������ȥ
		for(Map.Entry<String, String> entry:headmap.entrySet()){
			httpGet.addHeader(entry.getKey(), entry.getValue());
		}
		
		//ִ������
		CloseableHttpResponse response=httpClient.execute(httpGet);	
		return response;
	}
	
	//2.post����
	public CloseableHttpResponse post(String url,String entityString,Map<String,String> headermap) throws Exception{
		//����һ���ɹرյ�client����
		CloseableHttpClient httpClient=HttpClients.createDefault();
		
		//����һ��post����
		HttpPost httpPost=new HttpPost(url);
		
		//����payLoad
		httpPost.setEntity(new StringEntity(entityString));
		
		//��ͷ��Ϣ���ص�������ȥ
		for(Map.Entry<String, String> entity:headermap.entrySet()){
			httpPost.addHeader(entity.getKey(), entity.getValue());
		}
		
		//����CloseableResponse��������
		CloseableHttpResponse response=httpClient.execute(httpPost);
		return response;
		
	}

}
