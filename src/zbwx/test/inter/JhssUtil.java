package zbwx.test.inter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;

public class JhssUtil {
	
	private static final String OAUTH_COMSUMER_KEY = "13910643661";  
    private static final String OAUTH_COMSUMER_SECRET = "mer163";  
    private static final String API_URL = "http://localhost/api";  
    private static final String LOGIN_URL = "http://119.253.36.116/jhss/member/dologonnew/503001001/OAUTH_COMSUMER_KEY/OAUTH_COMSUMER_SECRET";
    private static final String WAP_LOGIN_URL = "http://119.253.36.116/jhss/member/wapLogon";
	
//    private static String loginResult;
    
	
	public void JhssUtils(){
//		loginResult = login();
	}
	
	public static void main(String[] args) {
		System.out.println(wapLogin("13233333333", "123456", "1"));
	}
	
	public static String login(){
    	String result = HttpClientUtil.doGet(LOGIN_URL);
    	if(result.contains("\"status\":\"0000\"")){
    		return result;
    	}else{
    		return null;
    	}
    }
	
	/**
	 * wap手机登录
	 * @param phone
	 * @param password
	 * @param businessType
	 * @return
	 */
	public static String wapLogin(String phone, String password, String businessType){
		
		List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>(0);  
		HashMap  header =new  HashMap(); 
		header.put("ak", "0630010010000");
		
		params.add(new BasicNameValuePair("phone", phone));  
		params.add(new BasicNameValuePair("password", password));  

		if (businessType != null) {  
		    params.add(new BasicNameValuePair("businessType", businessType));  
		}  
 
		return HttpClientUtil.doPost(WAP_LOGIN_URL, params, header);
	}
	
	/**
	 * 得到应用token值
	 * @param loginResult
	 * @return
	 */
	public String getToken(String loginResult){

    	String token;
    	String temp;
    	if(loginResult!=null && loginResult.contains("\"status\":\"0000\"")){
    		temp = loginResult.split("sessionid\":\"")[1];
    		token = temp.split("\"")[0];
    		System.out.println("token:"+token);
    		return token;
    	}
    	return null;
    }
	
	/**
	 * 从登录结果中，截取得到用户Id
	 * @param loginResult
	 * @return
	 */
	public static String getUserId(String loginResult){
    	String userId = null;
    	String temp;
    	if(loginResult!=null && loginResult.contains("\"status\":\"0000\"")){
    		temp = loginResult.split("userid\":")[1];
    		userId = temp.split(",")[0];
    		System.out.println("userid:"+userId);
    		return userId;
    	}
    	
		return userId;
    	
    }
	
}
