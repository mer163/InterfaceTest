package zbwx.test.h5.testcases;

import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import zbwx.test.inter.HttpClientUtil;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class TestLogin {
	
	static HttpClientUtil http = new HttpClientUtil();
	String host = "http://pan.zbwxkj.com"; 
	String url = "/chsea_app/h5/login";
	String correct_pwd = "MTIzNDU2";
	String incorrect_pwd = "123321";
	
	@Test
	public void login_success(){
		List<BasicNameValuePair> data = new ArrayList<BasicNameValuePair>();
		data.add(new BasicNameValuePair("mobile", "13100000061"));
		data.add(new BasicNameValuePair("dealPwd", correct_pwd));
		data.add(new BasicNameValuePair("ts", "123456"));
		String result = http.doPost(host + url, data );
		System.out.println("login_success 返回json:" + result);
//		String param = "?&mobile=13100000056&dealPwd=MTIzNDU2&ts=1467856520734";
//		String result = http.doGet(host + url + param);
		Assert.assertEquals(true, result.contains("resultCode\":\"0\""));
		
	}
	
	
	@Test
	public void login_with_incorrect_pwd(){
		String param = "?&mobile=13100000056&dealPwd=" + incorrect_pwd +"&ts=1467856520734";
		String result = http.doGet(host + url + param);
		System.out.println("login_with_incorrect_pwd 返回json:" + result);
		Assert.assertEquals(true, result.contains("resultCode\":\"1\""));
		
	}
	
	
	
	
}
