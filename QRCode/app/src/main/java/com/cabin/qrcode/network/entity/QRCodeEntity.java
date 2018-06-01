package com.cabin.qrcode.network.entity;

/**
 * <pre>
 *     author : wenxin
 *     e-mail : wenxin2233@outlook.com
 *     time   : 2018/05/29
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class QRCodeEntity {

    /**
     * data : d99f4ea0219f11d46e989302c2ad7e267e27d95a4d63dd92
     * resultCode : 00
     * message : 成功
     */

    private String data;
    private String resultCode;
    private String message;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
