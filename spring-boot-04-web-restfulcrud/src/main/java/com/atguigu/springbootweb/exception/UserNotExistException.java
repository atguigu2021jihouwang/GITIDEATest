package com.atguigu.springbootweb.exception;

/**
 * Created by JHW on 2021/8/26.
 */
public class UserNotExistException extends RuntimeException {

    public UserNotExistException() {
        super("用户不存在");
    }
}
