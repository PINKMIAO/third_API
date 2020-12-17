package com.meorient.avaya.mapper;

import com.meorient.avaya.pojo.PhoneCache;

import java.util.List;
import java.util.Map;

public interface PhoneMapper {

    // 测试、获取全信息
    List<PhoneCache> getAllMsg();
    // 插入单条电话信息
    int insertMsg(Map<String, String> map);
    // 获取单条电话信息
    PhoneCache isExist(String num);

}
