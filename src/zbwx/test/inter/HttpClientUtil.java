package zbwx.test.inter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
	static String url = "http://192.168.1.82:7073/quote/info/newslist?code=20600000&fromId=0&limit=20";
	static String login_url = "http://119.253.36.116/jhss/member/dologonnew/503001001/13222222222/123456?flag=-99";
	
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
		System.out.println(doGet(login_url));
		String tem = doGet(login_url);
		if(tem.contains("\"status\":\"0000\"")){
			System.out.println("返回0000，访问成功");
		}else{
			System.out.println("返回非0000，检查参数。");
			System.out.println(tem);
		}
	}
	
	
	public static String doGet(String url) {  
        return doGet(url, new ArrayList<BasicNameValuePair>());  
    }  
  
    public static String doGet(String url, List<BasicNameValuePair> data) {  
        /* 建立HTTP Get连线 */  
        HttpGet httpGet = new HttpGet(url);  
        try {  
            HttpResponse httpResponse = HttpClientUtil.getInstance().execute(httpGet);  
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {  
            	String result = EntityUtils.toString(httpResponse.getEntity());
//                return result;
            	return XmlBase64.decode(result);  
            } else {  
                System.out.println("doGet Error Response: " + httpResponse.getStatusLine().toString());  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return null;  
    }  
    
    /**
     * 带header参数的Get请求
     * @param url
     * @param header HashMap保存header参数
     * @return
     */
    public static String doGet(String url, HashMap header){
    	HttpGet httpGet = new HttpGet(url);  
    	
    	Set keys = header.keySet( );  
    	if(keys != null) {  
    	Iterator iterator = keys.iterator( );  
    		while(iterator.hasNext( )) {  
    			String key = (String) iterator.next( );  
    			String value = (String) header.get(key);  
    			httpGet.addHeader(key, value);	//添加header参数
    		}  
    	}
        try {  
//        	httpGet.addHeader("ak", "0630010010000");
//        	httpGet.addHeader("token","201606031014016471999");
//        	httpGet.addHeader("userid","6471999");
            HttpResponse httpResponse = HttpClientUtil.getInstance().execute(httpGet);  
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {  
            	String result = EntityUtils.toString(httpResponse.getEntity());
            	
                return XmlBase64.decode(result);  
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
                // 取出回应字串  ,base64解码
                return XmlBase64.decode(EntityUtils.toString(httpResponse.getEntity()));  
            } else {  
                System.out.println("doPost Error Response: " + httpResponse.getStatusLine().toString());  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return null;  
    } 
	
	
    public static String doPost(String url, List<BasicNameValuePair> data, HashMap header) {  
        /* 建立HTTP Post连线 */  
        HttpPost httpPost = new HttpPost(url);  
        Set keys = header.keySet( );  
    	if(keys != null) {  
    	Iterator iterator = keys.iterator( );  
    		while(iterator.hasNext( )) {  
    			String key = (String) iterator.next( );  
    			String value = (String) header.get(key);  
    			httpPost.addHeader(key, value);	//添加header参数
    		}  
    	}
        try {  
            // 发出HTTP request  
            // httpPost.setEntity(new UrlEncodedFormEntity(data, HTTP.UTF_8));  
            httpPost.setEntity(new UrlEncodedFormEntity(data, "UTF-8"));  
            // 取得HTTP response  
            HttpResponse httpResponse = HttpClientUtil.getInstance().execute(httpPost);  
            // 若状态码为200 ok  
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {  
                // 取出回应字串  ,base64解码
                return XmlBase64.decode(EntityUtils.toString(httpResponse.getEntity()));  
            } else {  
                System.out.println("doPost Error Response: " + httpResponse.getStatusLine().toString());  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return null;  
    } 
    
}
