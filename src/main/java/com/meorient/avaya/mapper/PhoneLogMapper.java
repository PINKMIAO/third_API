package com.meorient.avaya.mapper;

import com.meorient.avaya.pojo.PhoneAttriLog;

import java.util.List;
import java.util.Map;

public interface PhoneLogMapper {

    List<PhoneAttriLog> getAllMsg();
    int insertPhoneMsgLog(Map<String, String> map);

}
