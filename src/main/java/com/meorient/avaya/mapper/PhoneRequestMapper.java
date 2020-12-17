package com.meorient.avaya.mapper;

import com.meorient.avaya.pojo.PhoneRequestLog;

import java.util.List;
import java.util.Map;

public interface PhoneRequestMapper {

    List<PhoneRequestLog> getAllMsg();
    int insertPhoneMsgLog(Map<String, String> map);

}
