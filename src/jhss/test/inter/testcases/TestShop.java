package jhss.test.inter.testcases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jhss.interfaces.base.ApiUtil;
import jhss.interfaces.base.Constant;
import jhss.interfaces.base.HttpClientUtil;
import jhss.interfaces.base.JhssUtil;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class TestShop {
	
	private static String login_result;
	private static String ak = "0630010010000";
	private static String token;
	private static String userId;
	private static String userName;
	
	JhssUtil JhssUtil;
	
	private static String ip = "http://119.253.36.116";
//	private static String ip = "http://boss.youguu.com";
	
	
	public TestShop(){
		JhssUtil = new JhssUtil();
		login_result = JhssUtil.login();
		token = JhssUtil.getToken(login_result);
		userId = JhssUtil.getUserId(login_result);
		userName = JhssUtil.getUserName(login_result);
	}
	
	@BeforeClass
	public void beforeClass() {
		
	}

	@AfterClass
	public void afterClass() {
	}

	@BeforeTest
	public void beforeTest() {

	}

	@AfterTest
	public void afterTest() {
	}
  
	/**
	 * 牛人徽章列表
	 */
	@Test
	public void testQueryExpertBadge(){
		HashMap  header =new  HashMap(); 
		String url = "/pay/shop/v1/queryExpertBadge";
		String address = ip +url;
		String result;	//保存完整的接口返回结果内容
		header.put("token", token);
		header.put("userid", userId);
		header.put("ak", ak);
		result = HttpClientUtil.doGet(address,header);
		System.out.println(result);
		Assert.assertEquals( result.contains(Constant.STATUS_OK) && result.contains(Constant.BADGELIST) && result.contains(Constant.DETAIL), true);
		
	}
	
	/**
	 * 订单生成接口
	 */
	@Test(enabled=false)
	public void testOrder(){
		String url = "http://119.253.36.116/pay/v1/order";
		url = url +"?userName=" + userName + "&productId=" + "2" + "&payType=" + "101" ;
		HashMap header = new HashMap();
		String result;
		header.put("token", token);
		header.put("userid", userId);
		header.put("ak", ak);
		result = HttpClientUtil.doGet(url, header);
		System.out.println(result);
		Assert.assertEquals(result.contains(Constant.STATUS_OK ) && result.contains("result"), true);
	}
	
	
	/**
	 *  查询已有牛人徽章
	 */
	@Test
	public void testQueryMyBadge(){
		String url = "http://119.253.36.116/pay/shop/v1/queryMyBadge";
		HashMap header = new HashMap();
		String result;
		header.put("token", token);
		header.put("userid", userId);
		header.put("ak", ak);
		result = HttpClientUtil.doGet(url, header);
		System.out.println(result);
		Assert.assertEquals(result.contains(Constant.STATUS_OK) && result.contains("badgeList"), true);
		
	}
	
	
	/**
	 * 使用牛人徽章
	 */
	@Test
	public void testUseBadge(){
		String url = "http://119.253.36.116/pay/props/v1/useBadge";
		HashMap header = new HashMap();
		String result;
		header.put("token", token);
		header.put("userid", userId);
		header.put("ak", ak);
		List<BasicNameValuePair> data = new ArrayList<BasicNameValuePair>();
		data.add(new BasicNameValuePair("productId","1"));
		data.add(new BasicNameValuePair("touid", "1"));
		
		result = HttpClientUtil.doPost(url, data, header);
		System.out.println(result);
		Assert.assertEquals(result.contains(Constant.STATUS_OK) && result.contains("message"), true);
	}
	
	
	/**
	 * 商城列表
	 */
	@Test
	public void testHome(){
		String url = "http://119.253.36.116/pay/shop/v1/home";
		HashMap header = new HashMap();
		header.put("token", token);
		header.put("userid", userId);
		header.put("ak", ak);
		String result = HttpClientUtil.doGet(url, header);
		System.out.println(result);
		Assert.assertEquals(result.contains(Constant.STATUS_OK) && result.contains("productList"), true);
	}
	
	
	/**
	 * 金币支付购买
	 */
	@Test
	public void testBuyProps(){
		String url = "http://119.253.36.116/pay/props/buyProps";
		url = url + "?userId=" +userId + "&userName=" +userName + "&propsId=L18000001";
		HashMap header = new HashMap();
		header.put("token", token);
		header.put("ak", ak);
		String result = HttpClientUtil.doGet(url, header);
		System.out.println(result);
	}
	
	
	
	
}
