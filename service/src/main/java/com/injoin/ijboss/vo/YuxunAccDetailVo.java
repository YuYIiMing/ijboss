package com.injoin.ijboss.vo;

/**
 * @author figo
 */
public class YuxunAccDetailVo {
    /**
     * 云信tid
     */
    private String accid;
    /**
     *云信token
     */
    private String token;

    /**
     * 名称
     */
    private String name;

    public String getAccid() {
        return accid;
    }

    public void setAccid(String accid) {
        this.accid = accid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
