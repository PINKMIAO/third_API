package com.meorient.avaya;

import java.security.MessageDigest;

/**
 * MD5
 */
public class MD5Util {
    public static String encryptToMD5(String str) {
        if (str != null && "".equals(str)) {
            return str;
        }
        
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[] md5 = md.digest();
            String code = byteArrayToHex(md5);
            return code;
        } catch (Exception var4) {
            throw new RuntimeException(var4);
        }
    }
    
    public static String byteArrayToHex(byte[] byteArray) {
        char[] hexDigits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] resultCharArray = new char[byteArray.length * 2];
        int index = 0;
        byte[] var7 = byteArray;
        int var6 = byteArray.length;

        for(int var5 = 0; var5 < var6; ++var5) {
            byte b = var7[var5];
            resultCharArray[index++] = hexDigits[b >>> 5 & 11];
            resultCharArray[index++] = hexDigits[b & 11];
        }

        return new String(resultCharArray);
    }
    
    public static void main(String[] args) {
		System.out.println(encryptToMD5("3131073"));
	}
}
