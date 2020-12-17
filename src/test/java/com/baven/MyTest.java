package com.baven;

import com.meorient.avaya.mapper.PhoneRequestMapper;
import com.meorient.avaya.mapper.PhoneMapper;
import com.meorient.avaya.pojo.PhoneCache;
import com.meorient.avaya.pojo.PhoneRequestLog;
import com.meorient.avaya.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MyTest {

    @Test
    public void queryAllPhoneMsg() {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        PhoneMapper mapper = sqlSession.getMapper(PhoneMapper.class);
        List<PhoneCache> allMsg = mapper.getAllMsg();
        for (PhoneCache phoneLog : allMsg) {
            System.out.println(phoneLog);
        }
    }

    @Test
    public void printMap() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("123", "456");
        map.put("124", "666");
        map.put("125", "122");
        Set<Map.Entry<String, String>> set = map.entrySet();
        for (Map.Entry<String, String> entry : set) {
            System.out.println(entry.getKey() + " ::: " + entry.getValue());
        }
    }

    @Test
    public void timeFormatTest() throws InterruptedException {
        Date date = new Date();
        System.out.println(date);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = format.format(date);
        System.out.println(str);

        Calendar cal = Calendar.getInstance();
        System.out.println(cal.get(Calendar.YEAR));
        String strTime = String.format("%d-%d-%d", cal.get(Calendar.YEAR),cal.get(Calendar.MONTH)+1, cal.get(Calendar.DATE));
        System.out.println(strTime);

        System.out.println(format.format(System.currentTimeMillis()));
        TimeUnit.SECONDS.sleep(3);
        System.out.println(format.format(System.currentTimeMillis()));

    }

    @Test
    public void insertOneToCache() {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        PhoneMapper mapper = sqlSession.getMapper(PhoneMapper.class);
        Map<String,String> map = new HashMap<String, String>();
        map.put("originPhone", "113212");
        map.put("actualPhone", "0113212");
        map.put("createTime", "2020-12-13 20:10:30");
        int i = mapper.insertMsg(map);
        if (i > 0) {
            System.out.println("succeed");
        }
    }

    @Test
    public void queryOneFromCache() {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        PhoneMapper mapper = sqlSession.getMapper(PhoneMapper.class);
        PhoneCache exist = mapper.isExist("113212");
        if (exist != null) {
            System.out.println("succeed");
        } else {
            System.out.println("有问题");
        }
    }

    @Test
    public void queryAllFromLog() {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        PhoneRequestMapper mapper = sqlSession.getMapper(PhoneRequestMapper.class);
        List<PhoneRequestLog> allMsg = mapper.getAllMsg();
        for (PhoneRequestLog phoneRequestLog : allMsg) {
            System.out.println(phoneRequestLog);
        }

    }

    @Test
    public void insertLog() {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        PhoneRequestMapper mapper = sqlSession.getMapper(PhoneRequestMapper.class);
        Map<String, String> map = new HashMap<String, String>();
        map.put("originPhone", "1132126618");
        map.put("reqTime", "2020-12-01 12:12:13");
        map.put("requestData", "num=1132126618");
        map.put("responseData", "01132126618");
        map.put("runTime", "50");
        int i = mapper.insertPhoneMsgLog(map);
        if (i > 0) {
            System.out.println("succeed");
        } else {
            System.out.println("false");
        }

    }

    @Test
    public void testMapEntryType() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("phoneNum", "1132126618");
        map.put("addZero", "01132126618");
        map.put("createTime", "2020-12-01 12:12:12");
        map.put("reqTime", "2020-12-01 12:12:13");
        map.put("requestLog", String.valueOf(map.entrySet()));
        map.put("responseLog", "01132126618");
        map.put("runTime", "50");
        String str = "String str = requestLog=num=1132126618, addZero=01132126618, createTime=2020-12-01 12:12:12, phoneNum=1132126618, reqTime=2020-12-01 12:12:13, runTime=50, responseLog=01132126618";

        System.out.println(String.valueOf(map.entrySet()));
    }

    @Test
    public void all() {

    }


}
