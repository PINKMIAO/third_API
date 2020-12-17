package com.baven;

import com.meorient.avaya.mapper.PhoneLogMapper;
import com.meorient.avaya.mapper.PhoneMapper;
import com.meorient.avaya.pojo.PhoneAttri;
import com.meorient.avaya.pojo.PhoneAttriLog;
import com.meorient.avaya.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MyTest {

    @Test
    public void selectAll() {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        PhoneMapper mapper = sqlSession.getMapper(PhoneMapper.class);
        List<PhoneAttri> allMsg = mapper.getAllMsg();
        for (PhoneAttri phoneLog : allMsg) {
            System.out.println(phoneLog);
        }
    }

    @Test
    public void test02() {
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
    public void test03() throws InterruptedException {
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
    public void insert() {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        PhoneMapper mapper = sqlSession.getMapper(PhoneMapper.class);
        Map<String,String> map = new HashMap<String, String>();
        map.put("phoneNum", "113212");
        map.put("addZero", "0113212");
        map.put("createTime", "2020-12-13 20:10:30");
        int i = mapper.insertMsg(map);
        if (i > 0) {
            System.out.println("succeed");
        }
    }

    @Test
    public void queryOne() {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        PhoneMapper mapper = sqlSession.getMapper(PhoneMapper.class);
        PhoneAttri exist = mapper.isExist("113212");
        if (exist != null) {
            System.out.println("succeed");
        } else {
            System.out.println("有问题");
        }
    }

    @Test
    public void queryOneLog() {
        //insert into phone_attri_log (phone_num, add_zero, create_time, req_time, request_log, response_log, run_time) value ('13134439827', '013134439827', '2020-12-16 15:06:45', '2020-12-16 15:06:45', 'num=13134439827','013134439827', '60');
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        PhoneLogMapper mapper = sqlSession.getMapper(PhoneLogMapper.class);
        List<PhoneAttriLog> allMsg = mapper.getAllMsg();
        for (PhoneAttriLog phoneAttriLog : allMsg) {
            System.out.println(phoneAttriLog);
        }

    }

    @Test
    public void insertLog() {
        //insert into phone_attri_log (phone_num, add_zero, create_time, req_time, request_log, response_log, run_time) value ('13134439827', '013134439827', '2020-12-16 15:06:45', '2020-12-16 15:06:45', 'num=13134439827','013134439827', '60');
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        PhoneLogMapper mapper = sqlSession.getMapper(PhoneLogMapper.class);
        Map<String, String> map = new HashMap<String, String>();
        map.put("phoneNum", "1132126618");
        map.put("addZero", "01132126618");
        map.put("createTime", "2020-12-01 12:12:12");
        map.put("reqTime", "2020-12-01 12:12:13");
        map.put("requestLog", "num=1132126618");
        map.put("responseLog", "01132126618");
        map.put("runTime", "50");
        int i = mapper.insertPhoneMsgLog(map);
        if (i > 0) {
            System.out.println("succeed");
        } else {
            System.out.println("false");
        }

    }

    @Test
    public void testMapEntry() {
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


}
