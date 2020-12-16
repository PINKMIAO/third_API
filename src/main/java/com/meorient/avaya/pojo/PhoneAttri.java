package com.meorient.avaya.pojo;

public class PhoneAttri {

    private String phoneNum;
    private String addZero;
    private String createTime;

    public PhoneAttri() {
    }

    public PhoneAttri(String phoneNum, String addZero, String createTime) {
        this.phoneNum = phoneNum;
        this.addZero = addZero;
        this.createTime = createTime;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getAddZero() {
        return addZero;
    }

    public void setAddZero(String addZero) {
        this.addZero = addZero;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "PhoneAttri{" +
                "phoneNum='" + phoneNum + '\'' +
                ", addZero='" + addZero + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
