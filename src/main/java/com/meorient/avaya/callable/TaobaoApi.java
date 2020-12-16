package com.meorient.avaya.callable;

import java.util.concurrent.Callable;

import com.meorient.avaya.HttpClient;

@Deprecated
public class TaobaoApi implements Callable<String>{

	private String num;
	
	public TaobaoApi(String num) {
		this.num = num;
	}

	@Override
	public String call() throws Exception {
		String result = HttpClient.sendGet("https://tcc.taobao.com/cc/json/mobile_tel_segment.htm?tel=" + num);
		if (result.indexOf("杭州") < 0) {
			num = "0" + num;
		}
		System.out.println("taobao :" + num);
		return num;
	}
}
