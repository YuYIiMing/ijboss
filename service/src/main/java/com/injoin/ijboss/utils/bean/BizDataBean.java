package com.injoin.ijboss.utils.bean;

/**
 * @author figo
 */
public class BizDataBean {

    private String bizDataCode;

    private String paramData;

    public BizDataBean(String bizDataCode, String paramData) {
        this.bizDataCode = bizDataCode;
        this.paramData = paramData;
    }

    public String getBizDataCode() {
        return bizDataCode;
    }

    public void setBizDataCode(String bizDataCode) {
        bizDataCode = bizDataCode;
    }

    public String getParamData() {
        return paramData;
    }

    public void setParamData(String paramData) {
        this.paramData = paramData;
    }
}
