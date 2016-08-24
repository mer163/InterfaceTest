package zbwx.test.inter.testcases;

import java.net.CookiePolicy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieSpec;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.testng.reporters.jq.Main;

import zbwx.test.inter.HttpClientUtil;


public class test {

	
	static HttpClientUtil http = new HttpClientUtil();
	public static String token = "";
	
	public static void main(String[] args) {
//		appRegister("13100000046");
//		doLogin();
		appBuy("4185");
		
	}
	
	/**
	 * @param phone
	 * @return
	 */
	public static Boolean register(String phone){
		
		//密码经过MD5加密后请求。MTIzNDU2=123456
		// url = "http://pan.zbwxkj.com/chsea_app/h5/reg?dealPwd=MTIzNDU2&mobile=13000000015&ts=1467855545346&verifyCode=-99";
		
		String url = "http://pan.zbwxkj.com/chsea_app/h5/reg";
		List<BasicNameValuePair> data = new ArrayList<BasicNameValuePair>();
		data.add(new BasicNameValuePair("mobile", phone));
		data.add(new BasicNameValuePair("dealPwd", "MTIzNDU2"));
		data.add(new BasicNameValuePair("verifyCode", "-99"));
		data.add(new BasicNameValuePair("ts", "123456"));
		String result = http.doPost(url, data);

		if(result!=null && result.contains("resultCode\":\"0\"")){
			return true;
		}else{	
			return false;
		}
		
	}

	
	/**
	 * @param phone
	 * @return
	 */
	public static Boolean appRegister(String phone){
		
		//密码经过MD5加密后请求。MTIzNDU2=123456
		// url = "http://pan.zbwxkj.com/chsea_app/h5/reg?dealPwd=MTIzNDU2&mobile=13000000015&ts=1467855545346&verifyCode=-99";
		
		//String url = "http://pan.zbwxkj.com/chsea_app/appClient/reg";
		String url = "http://123.206.23.149:80/chsea_app/appClient/reg";
		List<BasicNameValuePair> data = new ArrayList<BasicNameValuePair>();
		data.add(new BasicNameValuePair("mobile", phone));
		data.add(new BasicNameValuePair("dealPwd", "MTIzNDU2"));
		data.add(new BasicNameValuePair("verifyCode", "-99"));
		data.add(new BasicNameValuePair("ts", "12345632132131"));
//		data.add(new BasicNameValuePair("shareCode", "w001"));
		data.add(new BasicNameValuePair("shareCode", "H000167"));
		String result = http.doPost(url, data);

		if(result!=null && result.contains("resultCode\":\"0\"")){
			return true;
		}else{
			return false;
		}
		
	}
	
	
		
	
	public static void doLogin(){
		// mer163 = bWVyMTYz  

		String url = "http://pan.zbwxkj.com/chsea_app/h5/login?&mobile=13100000056&dealPwd=MTIzNDU2&ts=1467856520734";
		String result = "";
		result = http.doPost(url);
		
		
		
		
	}
	

	
	public static void appBuy(String price){
		String url = "http://pan.zbwxkj.com/chsea_app/appClient/appBuy?ts=1469156593965&itemid=9&count=1&ticket=0&zy=&zs=&dir=U&buyType=1&price=" + price +"&payType=0&spread=0";
		
//		String url = "http://pan.zbwxkj.com/chsea_app/appClient/appBuy";
		HashMap  header =new  HashMap();
		header.put("Cookie", "token=948210290451167755");
		String result = http.doGet(url, header);
		System.out.println("result:"+result);
		
	}
	
		
	
	public static void fixedPriceSell(){
		
	}
	
	/**
	 * 修改止盈止损
	 */
	public static void updateOrder(){
		String url = "/chsea_app/appClient/updateOrder?ts=1468203735699&orderid=361047&type=0&zy=2231&zs=2189";
	}
	
	
}
