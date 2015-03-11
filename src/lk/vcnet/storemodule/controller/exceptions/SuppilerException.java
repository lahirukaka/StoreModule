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
public class SuppilerException extends Exception{
    private Exceptions ex;
    
    public SuppilerException(Exceptions ex){
        this.ex=ex;
    }

    @Override
    public String getMessage() {
        return ex.getMsg();
    }
    
    public int getCode() {
        return ex.getCode();
    }
    
    public enum Exceptions{
        SUPPLIER_ALREADY_EXISTS("Supplier Already Exists..!!!",11),
        SUPPLIER_NOT_EXISTING("Supplier NOt Existing..!!!",12),
        SUPPLIER_UPDATED("Supplier Record Has Been Chaged..!!!",13);
        
        private final String msg;
        private final int code;

        Exceptions(String msg,int code) {
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
    
}
