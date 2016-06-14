package jhss.interfaces.base;

import java.util.HashMap;
import java.util.Map;

import jhss.interfaces.base.ApiUtil;
import jhss.interfaces.base.HttpClientUtil;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class TestMaster {
	
	ApiUtil apiutil = new ApiUtil();
	
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
	 * 模块列表（公用）
	 */
	@Test
	public void testModuleList() {
		HashMap  map =new  HashMap(); 
		String url = "http://119.253.36.116/asteroid/rank/module?type=2";
		String ak = "0630010010000";
		String token = apiutil.getToken();
		String userId = apiutil.getUserId();

		map.put("token", token);
		map.put("ak", ak);
		map.put("userid", userId);
		String result = HttpClientUtil.doGet(url,map);
		System.out.println(result);
		Assert.assertEquals("", "");
		
	}
  
	
	@Test
	public void testRecommend(){
		String url = "http://119.253.36.116/asteroid/rank/recommend";
		String result = HttpClientUtil.doGet(url);
		Assert.assertEquals("", "");
		
	}
	
}
