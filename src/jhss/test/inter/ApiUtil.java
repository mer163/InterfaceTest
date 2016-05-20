package jhss.test.inter;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;

public class ApiUtil {  
	  
    private static final String OAUTH_COMSUMER_KEY = "key";  
    private static final String OAUTH_COMSUMER_SECRET = "password";  
    private static final String API_URL = "http://localhost/api";  
  
    private static String token = null;  
  
    public static String getToken() {  
        if (token == null) {  
            token = accountToken(OAUTH_COMSUMER_KEY, OAUTH_COMSUMER_SECRET);  
        }  
  
        return token;  
    }  
  
    // Oauth的accountToken的获得  
    public static String accountToken(String key, String secret) {  
        List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>(0);  
        params.add(new BasicNameValuePair("oauth_consumer_key", key));  
        params.add(new BasicNameValuePair("oauth_consumer_secret", secret));  
  
        String xml = HttpClientUtil.doPost(API_URL + "/accountToken", params);  
        if (hasText(xml)) {  
            if (xml.indexOf("errorCode") == -1) {  
                return XmlUtil.getContentFromXml(xml, "accountToken");  
            } else {  
                // 存在错误信息则返回null  
                return null;  
            }  
        } else {  
            return null;  
        }  
    }  
  
    // 用户登录接口  
    public static boolean login(String username, String password) {  
        return login(username, password, null);  
    }  
    public static boolean login(String username, String password, String userType) {  
        List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>(0);  
        params.add(new BasicNameValuePair("account_token", getToken()));  
        params.add(new BasicNameValuePair("username", username));  
        params.add(new BasicNameValuePair("password", password));  
        if (userType != null) {  
            params.add(new BasicNameValuePair("userType", userType));  
        }  
  
        String xml = HttpClientUtil.doPost(API_URL + "/login", params);  
        if (!hasText(xml)) {  
            return false;  
        }  
  
        if (xml.indexOf("errorCode") == -1) {  
            return true;  
        } else {  
            return false;  
        }  
    }  
  
    private static boolean hasText(String strText) {  
        return strText != null && !"".equals(strText);  
    }  
    
    
    
    public static void main(String[] argus) {  
        System.out.println(ApiUtil.getToken());  
      
        ApiUtil.login("chengesheng@gmail.com", "password");  
    }  
    
}  