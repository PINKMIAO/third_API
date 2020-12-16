package com.meorient.avaya.pojo;

public class PhoneAttriLog {

    private int id;
    private String phoneNum;
    private String addZero;
    private String createTime;
    private String reqTime;
    private String requestLog;
    private String responseLog;
    private String runTime;

    public PhoneAttriLog() {
    }

    public PhoneAttriLog(int id, String phoneNum, String addZero, String createTime,
                         String reqTime, String requestLog, String responseLog, String runTime) {
        this.id = id;
        this.phoneNum = phoneNum;
        this.addZero = addZero;
        this.createTime = createTime;
        this.reqTime = reqTime;
        this.requestLog = requestLog;
        this.responseLog = responseLog;
        this.runTime = runTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getReqTime() {
        return reqTime;
    }

    public void setReqTime(String reqTime) {
        this.reqTime = reqTime;
    }

    public String getRequestLog() {
        return requestLog;
    }

    public void setRequestLog(String requestLog) {
        this.requestLog = requestLog;
    }

    public String getResponseLog() {
        return responseLog;
    }

    public void setResponseLog(String responseLog) {
        this.responseLog = responseLog;
    }

    public String getRunTime() {
        return runTime;
    }

    public void setRunTime(String runTime) {
        this.runTime = runTime;
    }

    @Override
    public String toString() {
        return "PhoneAttriLog{" +
                "id=" + id +
                ", phoneNum='" + phoneNum + '\'' +
                ", addZero='" + addZero + '\'' +
                ", createTime='" + createTime + '\'' +
                ", reqTime='" + reqTime + '\'' +
                ", requestLog='" + requestLog + '\'' +
                ", responseLog='" + responseLog + '\'' +
                ", runTime='" + runTime + '\'' +
                '}';
    }
}
