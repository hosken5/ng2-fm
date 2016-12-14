package com.yimei.cron.basic.exception;

/**
 * Created by xiangyang on 16/8/8.
 */
public class BankException extends RuntimeException {


    public BankException(String message) {
        super(message);
    }

    public BankException(String message, Throwable cause) {
        super(message, cause);
    }
}
