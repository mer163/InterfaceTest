package jhss.test.inter.testcases;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import jhss.interfaces.base.ApiUtil;
import jhss.interfaces.base.Constant;
import jhss.interfaces.base.HttpClientUtil;
import jhss.interfaces.base.JhssUtil;
import jhss.interfaces.base.TestBase;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class TestMaster extends TestBase{
	
	private static String ak = "0630010010000";
	private static String token;
	private static String userId;
	
	JhssUtil JhssUtil;
	
	
	private static String ip = "http://119.253.36.116";
//	private static String ip = "http://boss.youguu.com";
	
	
	@BeforeClass
	public void beforeClass() {
		JhssUtil = new JhssUtil();
		String result = JhssUtil.login();
		token = JhssUtil.getToken(result);
		userId = JhssUtil.getUserId(result);

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
	 * 模块列表（公用）
	 */
	@Test
	public void testModuleList() {
		HashMap  header =new  HashMap(); 
		String url = "/asteroid/rank/module?type=2";
		String address = ip +url;
		String result;	//保存完整的接口返回结果内容

		header.put("token", token);
		header.put("userid", userId);
		header.put("ak", ak);
		result = HttpClientUtil.doGet(address,header);
		Assert.assertEquals( result.contains(Constant.STATUS_OK) && result.contains(Constant.DAILY_GOD) && result.contains(Constant.SUCCESS_RANK), true);
	}
  
	
	@Test
	public void testRecommend(){
		String address = ip + "/asteroid/rank/recommend";
		String result = HttpClientUtil.doGet(address);
		Assert.assertEquals( result.contains(Constant.STATUS_OK) && result.contains(Constant.NICKNAME) && result.contains(Constant.USER_ID), true);
	}
	
}
