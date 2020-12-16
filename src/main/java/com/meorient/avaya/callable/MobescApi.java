package com.meorient.avaya.callable;

import java.util.concurrent.Callable;

import com.meorient.avaya.HttpClient;

@Deprecated
public class MobescApi implements Callable<String>{

	private String num;
	
	public MobescApi(String num) {
		this.num = num;
	}

	@Override
	public String call() throws Exception {
		String result = HttpClient.sendGet("http://mobsec-dianhua.baidu.com/dianhua_api/open/location?tel=" + num);
		if (result.indexOf("杭州") < 0) {
			num = "0" + num;
		}
		return num;
	}
}
