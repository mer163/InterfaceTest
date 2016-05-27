package jhss.test.inter;

import java.io.BufferedReader;  
import java.io.InputStream;  
import java.io.InputStreamReader;  
import org.apache.http.HttpEntity;  
import org.apache.http.HttpResponse;  
import org.apache.http.HttpStatus;  
import org.apache.http.client.HttpClient;  
import org.apache.http.client.methods.HttpGet;  
import org.apache.http.client.params.HttpClientParams;  
import org.apache.http.impl.client.DefaultHttpClient;  
import org.apache.http.params.BasicHttpParams;  
import org.apache.http.params.HttpConnectionParams;  
import org.apache.http.params.HttpParams;  
  
public class ReadingWebContent {  
    /** 
     * 返回内容字符串 
     * **/  
    public static String getContent(String url) throws Exception{  
        String backContent = null;  
        //先建立一个客户端实例，将模拟一个浏览器  
        HttpClient httpclient = null;  
        HttpGet httpget = null;  
        try {  
            //************************************************************  
            // 设置超时时间  
            // 创建 HttpParams 以用来设置 HTTP 参数  
            HttpParams params = new BasicHttpParams();  
            // 设置连接超时和 Socket 超时，以及 Socket 缓存大小  
            HttpConnectionParams.setConnectionTimeout(params, 180 * 1000);  
            HttpConnectionParams.setSoTimeout(params, 180 * 1000);  
            HttpConnectionParams.setSocketBufferSize(params, 8192);  
            // 设置重定向，缺省为 true  
            HttpClientParams.setRedirecting(params, false);  
            //************************************************************     
            httpclient = new DefaultHttpClient(params);  
//          httpclient = new DefaultHttpClient();  
            // 建立一个get方法请求，提交刷新  
            httpget = new HttpGet(url);       
              
            HttpResponse response = httpclient.execute(httpget);   
            //HttpStatus.SC_OK(即:200)服务器收到并理解客户端的请求而且正常处理了  
//          if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {  
//              //对象呼叫中止  
//              httpget.abort();  
//              backContent = "获取不到";  
//          }  
            HttpEntity entity = response.getEntity();  
            if (entity != null) {              
                //start 读取整个页面内容  
                InputStream is = entity.getContent();  
                BufferedReader in = new BufferedReader(new InputStreamReader(is));   
                StringBuffer buffer = new StringBuffer();   
                String line = "";  
                while ((line = in.readLine()) != null) {  
                    buffer.append(line);  
                }  
                //end 读取整个页面内容  
                backContent = buffer.toString();  
            }  
        } catch (Exception e) {  
            httpget.abort();  
            backContent = "有异常,获取不到";     
            System.out.println("-------------异常开始");  
            e.printStackTrace();  
            System.out.println("-------------异常结束");  
        }finally{  
            //HttpClient的实例不再需要时，降低连接，管理器关闭，以确保立即释放所有系统资源  
            if(httpclient != null)  
                httpclient.getConnectionManager().shutdown();  
        }          
        //返回结果  
        return backContent;  
    }  
      
    @SuppressWarnings("static-access")  
    public static void main(String[] args) throws Exception{  
        ReadingWebContent a = new ReadingWebContent();  
        System.out.println(a.getContent("http://192.168.1.82:7073/quote/info/newslist?code=20600000&fromId=0&limit=20"));  
    }  
} 