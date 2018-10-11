package com.injoin.ijboss.vo;

/**
 * @author figo
 */
public class YunxinAccRespVo {
    /**
     * 错误码，200为正常返回
     */
    private int code;
    /**
     * 云信账号返回详情
     */
    private YuxunAccDetailVo info;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public YuxunAccDetailVo getInfo() {
        return info;
    }

    public void setInfo(YuxunAccDetailVo info) {
        this.info = info;
    }
}
