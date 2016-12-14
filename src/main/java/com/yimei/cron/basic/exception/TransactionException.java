package com.yimei.cron.basic.exception;

/**
 * Created by xiangyang on 16/8/8.
 */
public class TransactionException extends  RuntimeException {

   private String message;

   public TransactionException(String message) {
      super(message);
      this.message = message;
   }
}
