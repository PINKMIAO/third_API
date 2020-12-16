package com.meorient.avaya;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.meorient.avaya.callable.Ip138Api;
import com.meorient.avaya.callable.K780Api;
import com.meorient.avaya.utils.InsertPhoneAttriLog;
import com.meorient.avaya.utils.QueryAndInsertPhoneAttri;

public class CallService {
	public static void main(String[] args) {
		callA(null);
	}
	
	/**
	 * 杭州、上海AVAYA拨打
	 */
	public static void callA(String[] args) {
		ExecutorService threadPool = Executors.newFixedThreadPool(4);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//OneX oneX = new OneX();
		try {
			Map<String, String> map = new HashMap<String, String>(16);

			String params = args[0].replaceAll("'omex://", "").replaceAll("/", "");
			// 像是 Map.Entry 键值，多条进行拆分让后放进map中。
			for (String param : params.split("&")) {	
				String[] tokens = param.split("=");
				map.put(tokens[0], tokens[1]);
			}

			// 开始时间
			long l1 = System.currentTimeMillis();
			String startTime = simpleDateFormat.format(l1);
			// 存各个记录
			HashMap<String, String> temp = new HashMap<String, String>();

			String num = map.get("num");
			temp.put("phoneNum", num);

			{
//			String cache =									// 这块暂不知有什么用
				CachePropertiy.get(num);
//			if(cache!=null && cache != "") {
//				oneX.call(cache);
//				return;
//			}
			}
		
			if (num.matches("01[0-9]{10}")) {
				// 有加0的手机号，将0去掉
				num = num.substring(1);
			}

			// 判断数据库中是否存在此号码
			if (!QueryAndInsertPhoneAttri.isExist(map.get("num"))) {
				if (num.length() == 11 && num.startsWith("1")) {
					// api判断手机号归属地
					Callable<String> api1= new K780Api(num);
					CompletionService<String> completionService = new ExecutorCompletionService<String>(threadPool);
					completionService.submit(api1);

					try {
						// 执行了查询归属地
						num = completionService.poll(3, TimeUnit.SECONDS).get();
					} catch (NullPointerException e) {
						// 超时处理，进行第二组API调用
						completionService = new ExecutorCompletionService<String>(threadPool);
						Callable<String> api2 = new Ip138Api(num);
						completionService.submit(api2);
						try {
							num = completionService.poll(10, TimeUnit.SECONDS).get();
						} catch (Exception e2) {

						}
					}
				}
				temp.put("addZero", num);
				temp.put("createTime", simpleDateFormat.format(System.currentTimeMillis()));
				// 执行插入数据
				QueryAndInsertPhoneAttri.insertPhone(temp);
			}

			// 将数据写入文档中
			CachePropertiy.set(map.get("num"), num, startTime);

			// 将记录保存下来
			long l2 = System.currentTimeMillis();
			String endTime = simpleDateFormat.format(l2);
			String runTime = String.valueOf(l2 - l1);
			temp.put("reqTime", startTime);
			temp.put("runTime", runTime);
			temp.put("requestLog", String.valueOf(map.entrySet()));
			InsertPhoneAttriLog.insetPhoneLog(temp);

//			oneX.call(num);
		} catch (Exception e) {
			log(e.toString());
			e.printStackTrace();

		} finally {
			threadPool.shutdown();
		}
	}

	/**
	 * 云客用户拨打
	 */
	public static void call(String[] args) {
		Map<String, String> map = new HashMap<String, String>(16);

		System.out.println(args[0]);
		String params = args[0].replaceAll("'omex://", "").replaceAll("/", "").replace("omex:", "");
		System.out.println(params);
		for (String param : params.split("&")) {
			String[] tokens = param.split("=");
			map.put(tokens[0], tokens[1]);
		}
		String num = map.get("num");
		String user = map.get("user");

//			String num = "18368844873";
//			String user = "57035";

		String timestamp = String.valueOf(System.currentTimeMillis());
		Map<String, String> headers = new HashMap<String, String>(16);
		headers.put("timestamp", timestamp);
		headers.put("company", "auyy7f");
		headers.put("signKey", "yk-auyy7f-yu9ixf");
		headers.put("partnerId", MD5Util.encryptToMD5(user));
		headers.put("sign", (CryptUtils
				.GetMD5Code("yk-auyy7f-yu9ixf" + "auyy7f" + MD5Util.encryptToMD5(user) + timestamp).toUpperCase()));

		String param = "{\"phone\":\"" + num + "\"}";

		String result = HttpClient.sendPost("https://phone.yunkecn.com/open/call/partnerCustomer", param, headers);
		log(new Date() + "--" + user + "--" + num + "--" + result);
	}
	
	/**
	 * 云客(私有化) 用户拨打
	 */
	public static void callC(String[] args) {
		Map<String, String> map = new HashMap<String, String>(16);

		System.out.println(args[0]);
		String params = args[0].replaceAll("'omex://", "").replaceAll("/", "").replace("omex:", "");
		System.out.println(params);
		for (String param : params.split("&")) {
			String[] tokens = param.split("=");
			map.put(tokens[0], tokens[1]);
		}
		String num = map.get("num");
		String user = map.get("user");

//		String num = "18368844873";
//		String user = "56548";

		String timestamp = String.valueOf(System.currentTimeMillis());
		Map<String, String> headers = new HashMap<String, String>(16);
		headers.put("timestamp", timestamp);
		headers.put("company", "fey2ib");
		headers.put("signKey", "FE917712BC2F4AC18F5C15A810328538");
		headers.put("partnerId", MD5Util.encryptToMD5(user));
		headers.put("sign", (CryptUtils
				.GetMD5Code("FE917712BC2F4AC18F5C15A810328538" + "fey2ib" + MD5Util.encryptToMD5(user) + timestamp).toUpperCase()));

		String param = "{\"phone\":\"" + num + "\"}";

		String result = HttpClient.sendPost("https://yk.meorient.com/open/call/partnerCustomer", param, headers);
		log(new Date() + "--" + user + "--" + num + "--" + result);
	}

	public static void log(String result) {
		// 简单日志
		File f = new File("D:/yunke.log");
		FileWriter fw = null;
		try {
			if (!f.exists()) {
				f.createNewFile();
			}
			fw = new FileWriter(f, true);
			fw.write(result + "\r\n");
			fw.flush();
		} catch (Exception e) {

		} finally {
			try {
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
