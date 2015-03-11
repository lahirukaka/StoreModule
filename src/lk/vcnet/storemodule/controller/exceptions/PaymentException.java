/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.vcnet.storemodule.controller.exceptions;

/**
 *
 * @author HP™
 */
public class PaymentException  extends Exception{
    
    public enum Exceptions{
        PAYMENT_ALREADY_EXISTS("Payment Already Done..!!!",11),
        PAYMENT_NOT_EXISTING("Payment Not Existing..!!!",12),
        PAYMENT_UPDATED("Payment Record Has Been Chaged..!!!",13);
        
        private final String msg;
        private final int code;
       
        
        Exceptions(String msg,int code){
            this.msg=msg;
            this.code=code;  
        }
        
        public String getMsg() {
            return msg;
        }

        public int getCode() {
            return code;
        }
    }
    
    private final Exceptions ex;
    
    public PaymentException(Exceptions ex){
        this.ex=ex;
    }

    @Override
    public String getMessage() {
        return ex.getMsg();
    }
    public int getCode(){
        return ex.getCode();
    }
}
