package com.meorient.avaya.utils;

import com.meorient.avaya.mapper.PhoneLogMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.Map;

/**
 * 插入用户使用的使用的记录
 */
public class InsertPhoneAttriLog {

    public static int insetPhoneLog(Map<String, String> map) {
        System.out.println("insetPhoneLog => 执行");
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        PhoneLogMapper mapper = sqlSession.getMapper(PhoneLogMapper.class);
        int i = mapper.insertPhoneMsgLog(map);
        sqlSession.close();
        if (i > 0) {
            System.out.println("insetPhoneLog => 插入成功");
        }
        return i;
    }

}
