package jhss.interfaces.base;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

	
	
	public static void test(){
		String url = "http://192.168.1.82:7073/quote/info/newslist?code=20600000&fromId=0&limit=20";
		HttpGet get = new HttpGet(url);
		try {
			HttpResponse response = HttpClientUtil.getInstance().execute(get);
			if(response.getStatusLine().getStatusCode()==200){
				HttpEntity entity = response.getEntity();
				InputStream is = entity.getContent();	//得到inputStream
				
				System.out.println(EntityUtils.toString(response.getEntity()));
			}else{
				System.out.println("Do Get failed.");
			}
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
