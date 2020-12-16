package com.meorient.avaya;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

public class HttpClient {
    /**
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url 发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param, Map<String,String> headers) {
        PrintWriter out = null;
        BufferedReader in = null;
        StringBuffer result = new StringBuffer();
        try {
            URL realUrl = new URL(url);

            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();

            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Host", "phone.yunkecn.com");
            
            conn.setRequestProperty("partnerId", headers.get("partnerId"));
            conn.setRequestProperty("company", headers.get("company"));
            conn.setRequestProperty("sign", headers.get("sign"));
            conn.setRequestProperty("timestamp", headers.get("timestamp"));
            conn.setRequestProperty("Content-Type", "application/json");

            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);

            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());

            // 发送请求参数
            out.print(param);

            // flush输出流的缓冲
            out.flush();

            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
        	CallService.log(e.toString());
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
            	CallService.log(ex.toString());
            }
        }
        return result.toString();
    }
    
    public static String sendGet(String url) {
    	PrintWriter out = null;
        StringBuffer result = new StringBuffer();
        BufferedReader br = null;
        try {
          URL realUrl = new URL(url);
          URLConnection conn = realUrl.openConnection();

          conn.setRequestProperty("accept", "*/*");
          conn.setRequestProperty("connection", "Keep-Alive");
          conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

//          conn.setDoOutput(true);
//          conn.setDoInput(true);
          
          InputStream in = conn.getInputStream();
          InputStreamReader isr = new InputStreamReader(in,"UTF-8");
          br = new BufferedReader(isr);
          String line = null;
          while ((line = br.readLine()) != null) {
        	  result.append(line);
          }
        }
        catch (Exception e) {
          e.printStackTrace();
          try
          {
            if (out != null) {
              out.close();
            }
            if (br != null)
              br.close();
          }
          catch (IOException ex) {
            ex.printStackTrace();
          }
        }
        finally
        {
          try
          {
            if (out != null) {
              out.close();
            }
            if (br != null)
              br.close();
          }
          catch (IOException ex) {
            ex.printStackTrace();
          }
        }
        return result.toString();
    }
}