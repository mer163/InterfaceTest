package jhss.test.inter;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpClientUtil {
	
	public static DefaultHttpClient httpClient = null;  
	static String test = "http://www.baidu.com";
	
	public static HttpClient getInstance() {  
        if (httpClient == null) {  
            httpClient = new DefaultHttpClient();  
        }  
        return httpClient;  
    }  
	
	public static void disconnect() {  
        httpClient = null;  
    }  
	
	public static void main(String[] args) {
		System.out.println(doGet(test));
				
	}
	
	
	public static String doGet(String url) {  
        return doGet(url, new ArrayList<BasicNameValuePair>());  
    }  
  
    public static String doGet(String url, List<BasicNameValuePair> data) {  
        /* 建立HTTP Post连线 */  
        HttpGet httpGet = new HttpGet(url);  
        try {  
            HttpResponse httpResponse = HttpClientUtil.getInstance().execute(httpGet);  
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {  
                return EntityUtils.toString(httpResponse.getEntity());  
            } else {  
                System.out.println("doGet Error Response: " + httpResponse.getStatusLine().toString());  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return null;  
    }  
  
    public static String doPost(String url) {  
        return doPost(url, new ArrayList<BasicNameValuePair>());  
    }  
  
    public static String doPost(String url, List<BasicNameValuePair> data) {  
        /* 建立HTTP Post连线 */  
        HttpPost httpPost = new HttpPost(url);  
        try {  
            // 发出HTTP request  
            // httpPost.setEntity(new UrlEncodedFormEntity(data, HTTP.UTF_8));  
            httpPost.setEntity(new UrlEncodedFormEntity(data, "UTF-8"));  
            // 取得HTTP response  
            HttpResponse httpResponse = HttpClientUtil.getInstance().execute(httpPost);  
            // 若状态码为200 ok  
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {  
                // 取出回应字串  
                return EntityUtils.toString(httpResponse.getEntity());  
            } else {  
                System.out.println("doPost Error Response: " + httpResponse.getStatusLine().toString());  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return null;  
    } 
	
	
}
