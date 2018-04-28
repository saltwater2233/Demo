package com.cabin.parkingmanager.network.entity;

/**
 * <pre>
 *     author : wenxin
 *     e-mail : wenxin2233@outlook.com
 *     time   : 2018/04/27
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class UploadEntity {

    /**
     * code : 00
     * msg : request success
     * data :
     */

    private String code;
    private String msg;
    private String data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
