package jhss.interfaces.base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import jhss.interfaces.base.JhssUtil;

public class TestBase {
	
	private static String ak = "0630010010000";
	private static String token;
	private static String userId;
	
	JhssUtil JhssUtil;

    @Parameters({"host", "url", "ak"})
    @BeforeSuite(alwaysRun = true)
	public void beforeSuite() {
		JhssUtil = new JhssUtil();
		String result = JhssUtil.login();
		token = JhssUtil.getToken(result);
		userId = JhssUtil.getUserId(result);	
	}

	@AfterSuite
	public void afterSuite() {
		
	}
	
	@BeforeMethod
	public void beforeMethod() {
		
	}
	
	@AfterMethod
	public void afterMethod() {
		
	}
}
