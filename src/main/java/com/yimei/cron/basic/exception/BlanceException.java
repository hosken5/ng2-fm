package com.yimei.cron.basic.exception;

/**
 * Created by xiangyang on 16/8/9.
 */
public class BlanceException extends  RuntimeException {
    private String message;

    public BlanceException(String message) {
        super(message);
        this.message = message;
    }
}
