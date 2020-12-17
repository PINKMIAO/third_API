package com.meorient.avaya.utils;

import com.meorient.avaya.mapper.PhoneMapper;
import com.meorient.avaya.pojo.PhoneCache;
import org.apache.ibatis.session.SqlSession;

import java.util.Map;

/**
 * 插入判断好的电话 以及 判断数据库中是否存在
 */
public class QueryOrInsertPhoneCache {
    // 插入数据
    public static int insertPhone(Map<String, String> map) {
        System.out.println("insertPhone => 执行");
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        PhoneMapper mapper = sqlSession.getMapper(PhoneMapper.class);
        int i = mapper.insertMsg(map);
        sqlSession.close();
        if (i > 0) {
            System.out.println("insertPhone => 插入成功");
        }
        return i;
    }
    // 判断是否存在
    public static boolean isExist(String num) {
        System.out.println("isExist => 执行");
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        PhoneMapper mapper = sqlSession.getMapper(PhoneMapper.class);
        PhoneCache exist = mapper.isExist(num);
        // 想法：这里可以添加数据记录，可是缺少时间、请求、响应，所以要在这之前建立记录
        sqlSession.close();
        if (exist != null) {
            System.out.println("isExist => 数据库查询：电话已存在");
            return true;
        }
        return false;
    }

}
