package com.freenet.tools;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.freenet.common.MailContent;

public class MailTools {

	public static String api_User;
	public static String api_Key;

	public String getApi_User() {
		return api_User;
	}

	public void setApi_User(String api_User) {
		MailTools.api_User = api_User;
	}

	public String getApi_Key() {
		return api_Key;
	}

	public void setApi_Key(String api_Key) {
		MailTools.api_Key = api_Key;
	}

	/**
	 * 转换成xsmtpapijson字符串
	 * 
	 * @param dataList
	 * @return
	 */
	public static String convert(List<MailContent> dataList) {

		JSONObject ret = new JSONObject();
		JSONArray to = new JSONArray();
		JSONArray names = new JSONArray();
		JSONArray contents = new JSONArray();

		for (MailContent a : dataList) {
			to.put(a.getAddress());
			names.put(a.getName());
			contents.put(a.getContent());
		}

		JSONObject sub = new JSONObject();
		sub.put("%name%", names);
		sub.put("%content%", contents);
		ret.put("to", to);
		ret.put("sub", sub);
		return ret.toString();
	}

	/**
	 * 发送模板邮件
	 * 
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static void send_template(MailContent content) throws ClientProtocolException, IOException {

		final String url = "http://api.sendcloud.net/apiv2/mail/sendtemplate";

		final String apiUser = api_User;
		final String apiKey = api_Key;

		String subject = "邮箱绑定激活";

		List<MailContent> dataList = new ArrayList<>();
		dataList.add(new MailContent(content.getAddress(), content.getName(), content.getContent()));

		final String xsmtpapi = convert(dataList);

		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("apiUser", apiUser));
		params.add(new BasicNameValuePair("apiKey", apiKey));
		params.add(new BasicNameValuePair("xsmtpapi", xsmtpapi));
		params.add(new BasicNameValuePair("templateInvokeName", "cg_test_template"));
		params.add(new BasicNameValuePair("from", "freenet@163.com"));
		params.add(new BasicNameValuePair("fromName", "freenet团队"));
		params.add(new BasicNameValuePair("subject", subject));

		httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

		HttpResponse response = httpClient.execute(httpPost);

		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) { // 正常返回
			System.out.println(EntityUtils.toString(response.getEntity()));
		} else {
			System.err.println("error");
		}
		httpPost.releaseConnection();
	}

	/**
	 * 增加模板邮件
	 * 
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static void add_template() throws ClientProtocolException, IOException {

		final String url = "http://api.sendcloud.net/apiv2/template/add";

		final String apiUser = "cgsj_test_Iy3XaL";
		final String apiKey = "";
		final String invoke_name = "";
		final String name = "";
		final String html = "";

		String subject = "";

		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("apiUser", apiUser));
		params.add(new BasicNameValuePair("apiKey", apiKey));
		params.add(new BasicNameValuePair("invokeName", invoke_name));
		params.add(new BasicNameValuePair("name", name));
		params.add(new BasicNameValuePair("html", html));
		params.add(new BasicNameValuePair("templateType", "1"));
		params.add(new BasicNameValuePair("subject", subject));

		httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

		HttpResponse response = httpClient.execute(httpPost);

		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) { // 正常返回
			System.out.println(EntityUtils.toString(response.getEntity()));
		} else {
			System.err.println("error");
		}
		httpPost.releaseConnection();
	}

	public static void main(String[] args) {
		MailContent content = new MailContent("837718548@qq.com", "陈钢", "https://www.baidu.com");
		try {
			send_template(content);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
