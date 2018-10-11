package com.injoin.ijboss.api.enums;

import in.togetu.tscommon.model.ResultCode;

public enum IjuserErrorCode implements ResultCode {

    //nearby code
    USER_NOT_XXX("IJUSER_10001", "Just Example"),
    FOLLOW_EXIST("IJUSER_10020", "you has bean followed "),
    FOLLOW_NOT_EXIST("IJUSER_10021", "you has not bean followed "),
    //resubmit code
    REPEAT_SUBMIT("IJUSER_10002", "Please do not repeat operation"),
    //update black code
    BLACK_UPDATE_FAIL("IJUSER_10003", "Fail,please try again later"),
    //save black code
    BLACK_SAVE_FAIL("IJUSER_10004", "Fail,please try again later"),
    BATCH_READ_FAIL("IJUSER_10005", "read info failed"),
    EDIT_FAIL("IJUSER_10006", "edit failed"),
    NEARBT_FAIL("IJUSER_10007", "No one is in the area"),
    LOGIN_SMS_FAIL("IJUSER_10010", "SMS verification code error"),
    LOGIN_MOBILE_FAIL("IJUSER_10011", "Network exception,please try again later"),
    YUNXIN_ACC_CREATE_ERROR("IJUSER_10012", "create error");


    IjuserErrorCode(String code, String message) {
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

    public static IjuserErrorCode getByCode(String code) {
        if (null != code) {
            for (IjuserErrorCode acErrorCode : values()) {
                if (acErrorCode.getCode().equals(code)) {
                    return acErrorCode;
                }
            }
        }
        return null;
    }
}
