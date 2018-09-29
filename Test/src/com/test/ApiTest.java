package com.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.sf.json.JSONObject;

public class ApiTest {
	public static void main(String[] args) {
		 Map<String,Object> mapParam = new HashMap<String,Object>();
	        JSONObject str = sendGet("https://�����ַ", mapParam);
	        System.out.println(str);
	}
	
	public static JSONObject sendPost(String url,Map<String,Object> paramMap){
		 JSONObject jsonObj = null;
	        if(url != "https:/Your URL!"){
	        //��ȡtoken��������OAuth 2.0��֤    
	            paramMap.put("access_token", getToken());
	        }
	        try {
	            URL realUrl = new URL(url);
	            URLConnection conn = realUrl.openConnection();
	            //ָ���ͻ����ܹ����յ���������    
	            conn.setRequestProperty("accept", "*/*");
	            conn.setRequestProperty("connection", "Keep-Alive");//�������ӵ�״̬
	            //User-Agent�����ݰ�������������û���Ϣ
	            conn.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
	            conn.setRequestProperty("Charset", "UTF-8");
	            conn.setDoOutput(true);
	            conn.setDoInput(true);

	            PrintWriter out = new PrintWriter(conn.getOutputStream());
	            String param = mapToParam(paramMap);
	            out.print(param);
	            out.flush();
	            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            String line;
	            String str = "";
	            while ((line = in.readLine()) != null) {
	                str += line;
	            }
	            jsonObj = JSONObject.fromObject(str);
	        } catch (MalformedURLException e) {
	            System.err.println("URLЭ�顢��ʽ����·������" + e);
	            e.printStackTrace();
	        } catch (IOException e) {
	            System.err.println("URL����ʧ�ܣ�" + e);
	            e.printStackTrace();
	        } catch (Exception e) {
	            System.err.println("����  POST ��������쳣��" + e);
	            e.printStackTrace();
	        }
	        return jsonObj;
	}
	
	/**
     * ��ָ��url����GET����
     * 
     */
    public static JSONObject sendGet(String url,Map<String,Object> paramMap){
        JSONObject jsonObj = null;
        paramMap.put("access_token", getToken());
        try {
            String requestUrl = url + "?" + mapToParam(paramMap);
            URL realUrl = new URL(requestUrl);

            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Charset", "UTF-8");
            conn.connect();
            System.out.println("�ӿ����ӳɹ�");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String str = "";
            String line;
            while ((line = in.readLine()) != null) {
                str += line;
            }
            jsonObj = JSONObject.fromObject(str);
        } catch (MalformedURLException e) {
            System.err.println("URLЭ�顢��ʽ����·������" + e);
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("URL����ʧ�ܣ�" + e);
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("����  GET ��������쳣��" + e);
            e.printStackTrace();
        }
        return jsonObj;
    }
    
    /**
     * ��Map��ʽ�Ĳ���תΪurl����
     * @Title mapToParam
     */
    public static String mapToParam(Map<String,Object> paramMap){
        //ȡ��Map�������ƴ���������
        String param = "";
        if (paramMap != null && paramMap.size() > 0) {
            Iterator<String> ite = paramMap.keySet().iterator();
            while (ite.hasNext()) {
                String key = ite.next();// key
                Object value = paramMap.get(key);
                param += key + "=" + value + "&";
            }
            param = param.substring(0, param.length() - 1);
        }
        return param;
    }
    
    /**
     * ��ȡaccess_tokenȨ����֤���ƣ�token��
     */
    public static String getToken(){
        Map<String,Object> mapParam = new HashMap<String,Object>();//�������
        mapParam.put("grant_type", "password");
        mapParam.put("������","����ֵ");//����Ĳ�������Ҫ�󴫾ͺ�
        mapParam.put("redirect_uri","http://www.baidu.com/");
        mapParam.put("username","");
        mapParam.put("password","");
        String pathUrl = "��ȡtoken��URL";
        Map<String,Object> result =sendPost(pathUrl, mapParam);
        System.out.println(result.toString());
        return (String) result.get("access_token");
    }

}
