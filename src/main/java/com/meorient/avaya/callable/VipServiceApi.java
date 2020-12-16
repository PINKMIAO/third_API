package com.meorient.avaya.callable;

import java.util.concurrent.Callable;

import com.meorient.avaya.HttpClient;

@Deprecated
public class VipServiceApi implements Callable<String>{

	private String num;
	
	public VipServiceApi(String num) {
		this.num = num;
	}

	@Override
	public String call() throws Exception {
		String result = HttpClient.sendGet("http://api.online-service.vip/phone?number=" + num);
		if (result.indexOf("0571") < 0) {
			num = "0" + num;
		}
		return num;
	}
};
