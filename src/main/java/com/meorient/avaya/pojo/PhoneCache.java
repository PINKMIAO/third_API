package com.meorient.avaya.pojo;

public class PhoneCache {

    private String originPhone;
    private String actualPhone;
    private String createTime;

    public PhoneCache() {
    }

    public PhoneCache(String originPhone, String actualPhone, String createTime) {
        this.originPhone = originPhone;
        this.actualPhone = actualPhone;
        this.createTime = createTime;
    }

    public String getOriginPhone() {
        return originPhone;
    }

    public void setOriginPhone(String originPhone) {
        this.originPhone = originPhone;
    }

    public String getActualPhone() {
        return actualPhone;
    }

    public void setActualPhone(String actualPhone) {
        this.actualPhone = actualPhone;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
