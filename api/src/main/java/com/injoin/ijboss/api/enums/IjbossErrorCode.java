package com.injoin.ijboss.api.enums;

import in.togetu.tscommon.model.ResultCode;

public enum IjbossErrorCode implements ResultCode {

    YUNXIN_ACC_CREATE_ERROR("IJBOSS_10012", "create error");


    IjbossErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private String code;

    private String message;

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public static IjbossErrorCode getByCode(String code) {
        if (null != code) {
            for (IjbossErrorCode acErrorCode : values()) {
                if (acErrorCode.getCode().equals(code)) {
                    return acErrorCode;
                }
            }
        }
        return null;
    }
}
