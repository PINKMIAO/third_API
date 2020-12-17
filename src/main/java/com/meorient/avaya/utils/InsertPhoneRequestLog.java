package com.meorient.avaya.utils;

import com.meorient.avaya.mapper.PhoneRequestMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.Map;

/**
 * 插入用户的请求记录
 */
public class InsertPhoneRequestLog {

    public static int insetPhoneLog(Map<String, String> map) {
        System.out.println("insetPhoneLog => 执行");
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        PhoneRequestMapper mapper = sqlSession.getMapper(PhoneRequestMapper.class);
        int i = mapper.insertPhoneMsgLog(map);
        sqlSession.close();
        if (i > 0) {
            System.out.println("insetPhoneLog => 插入成功");
        }
        return i;
    }

}
