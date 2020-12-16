package com.meorient.avaya;

/**
 * 主类
 */
public class Main {
	
	public static void main(String[] args) {
		// call 云客  callA avaya callC 云客私有化
		System.out.println(args[0]);
       	CallService.callA(args);
	}
}
