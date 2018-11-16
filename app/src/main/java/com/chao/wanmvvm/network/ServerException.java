package com.chao.wanmvvm.network;

/**
 * Date: 2018/11/16 12:56
 * Author: hansyang
 * Description:
 */
public class ServerException extends RuntimeException {
    public String message;

    public int code;

    public ServerException(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
