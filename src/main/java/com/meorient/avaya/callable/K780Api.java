package com.meorient.avaya.callable;

import java.util.concurrent.Callable;

import com.meorient.avaya.HttpClient;

public class K780Api implements Callable<String>{

	private String num;

	public K780Api(String num) {
		this.num = num;
	}


	public String call() throws Exception {
		String result = HttpClient.sendGet("http://api.k780.com/?app=phone.get&phone="+num+"&appkey=*&sign=*&format=json");
		if(result.indexOf("\"success\":\"0\"")>0) {
			// 超过免费次数，弃用
			Thread.sleep(8000L);
			return num;
		}
		if (result.indexOf("0571") < 0) {
			num = "0" + num;
		}
		return num;
	}

	public static void main(String[] args) throws Exception {
		K780Api api = new K780Api("13857772230");
		System.out.println(api.call());
	}
};
