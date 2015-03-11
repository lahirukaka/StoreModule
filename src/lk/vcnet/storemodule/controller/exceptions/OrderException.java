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
public class OrderException  extends Exception{
    
    public enum Exceptions{
        ORDER_ALREADY_EXISTS("Order Already Exists..!!!",11),
        ORDER_NOT_EXISTING("Order Not Existing..!!!",12),
        ORDER_UPDATED("Order Record Has Been Chaged..!!!",13);
        
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
    
    public OrderException(Exceptions ex){
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
