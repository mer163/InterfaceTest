package jhss.test.inter;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.message.BasicNameValuePair;

public class ApiUtil {  
	  
    private static final String OAUTH_COMSUMER_KEY = "key";  
    private static final String OAUTH_COMSUMER_SECRET = "password";  
    private static final String API_URL = "http://localhost/api";  
    private static final String login_url = "http://119.253.36.116/jhss/member/dologonnew/503001001/13222222222/123456";
    
    public static String loginResult = "";
    
    public ApiUtil(){
    	loginResult = login();
    }
  
    public static String getToken(){

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
    
    public static String getUserId(){
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
    
//    public static String getToken() {  
//        if (token == null) {  
//            token = accountToken(OAUTH_COMSUMER_KEY, OAUTH_COMSUMER_SECRET);  
//        }  
//  
//        return token;  
//    }  
  
    public static String login(){
    	String result = HttpClientUtil.doGet(login_url);
    	System.out.println(result);
    	if(result.contains("\"status\":\"0000\"")){
    		return result;
    	}else{
    		return null;
    	}

    }
//    
//    // Oauth的accountToken的获得  
//    public static String accountToken(String key, String secret) {  
//        List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>(0);  
//        params.add(new BasicNameValuePair("oauth_consumer_key", key));  
//        params.add(new BasicNameValuePair("oauth_consumer_secret", secret));  
//  
//        String xml = HttpClientUtil.doPost(API_URL + "/accountToken", params);  
//        if (hasText(xml)) {  
//            if (xml.indexOf("errorCode") == -1) {  
//                return XmlUtil.getContentFromXml(xml, "accountToken");  
//            } else {  
//                // 存在错误信息则返回null  
//                return null;  
//            }  
//        } else {  
//            return null;  
//        }  
//    }  
//  
//    // 用户登录接口  
//    public static boolean login(String username, String password) {  
//        return login(username, password, null);  
//    }  
//    public static boolean login(String username, String password, String userType) {  
//        List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>(0);  
//        params.add(new BasicNameValuePair("account_token", getToken()));  
//        params.add(new BasicNameValuePair("username", username));  
//        params.add(new BasicNameValuePair("password", password));  
//        if (userType != null) {  
//            params.add(new BasicNameValuePair("userType", userType));  
//        }  
//  
//        String xml = HttpClientUtil.doPost(API_URL + "/login", params);  
//        if (!hasText(xml)) {  
//            return false;  
//        }  
//  
//        if (xml.indexOf("errorCode") == -1) {  
//            return true;  
//        } else {  
//            return false;  
//        }  
//    }  
//  
//    private static boolean hasText(String strText) {  
//        return strText != null && !"".equals(strText);  
//    }  
//    
    
    
    public static void main(String[] argus) {  
//        System.out.println(ApiUtil.getToken());  
      ApiUtil a = new ApiUtil();
      System.out.println(getToken());
      System.out.println(getUserId());
//        ApiUtil.login("chengesheng@gmail.com", "password");  
    }  
    
}  