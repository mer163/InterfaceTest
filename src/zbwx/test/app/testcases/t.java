package zbwx.test.app.testcases;

import java.util.HashMap;
import java.util.Random;

import zbwx.test.inter.HttpClientUtil;

public class t {

	static HttpClientUtil http = new HttpClientUtil();
	private static int a = 10000;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		for(int i=0;i<=500;i++){
//			sendSms();
//		}
		System.out.println("start:");
		for(int i=0;i<500;i++){
			System.out.println(i+"请求发送短信。");
			a= a + 60000;
			sendSms(a);
			
		}
//		sendSms();
	}

	
	public static void sendSms(int ts){
		Random r = new Random();
//		String ra = String.valueOf(ts + r.nextInt());
//		System.out.println("ra=" +ra);
		String url = "http://pan.zbwxkj.com/chsea_app/h5/sms?ts=1163222" + ts + "&mobile=13100000202";
//		String url = "http://pan.zbwxkj.com/chsea_app/h5/sms?ts=11632&mobile=13100000200";
		System.out.println("url:"+url);
		
		HashMap  header =new  HashMap();
		header.put("Cookie", "token=1179210447572092108");
		header.put("User-Agent", "zhwp");
		String result = http.doGet(url, header);
		System.out.println("result:"+result);
	}
}
