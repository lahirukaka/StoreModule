/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.vcnet.storemodule.server;

import java.sql.ResultSet;
import javax.swing.JOptionPane;
import lk.vcnet.storemodule.controller.exceptions.PaymentException;
import lk.vcnet.storemodule.controller.exceptions.TransactionException;
import lk.vcnet.storemodule.db.Contracts;
import me.colhh.mysqlmanager.MysqlAdapter;

/**
 *
 * @author HP™
 */
public class Payments {
    private String payId,transId,qry;
    private long date;
    private double payAmt;
    private int cnt,rv;
  
    public String getPayId() {
        return payId;
    }

    public void setPayId(String payId) {
        this.payId = payId;
    }

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public String getQry() {
        return qry;
    }

    public void setQry(String qry) {
        this.qry = qry;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public double getPayAmt() {
        return payAmt;
    }

    public void setPayAmt(double payAmt) {
        this.payAmt = payAmt;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }
    
     public void makePayment() throws PaymentException
    {
        try 
        {
            cnt=ServerMain.countRecords(Contracts.Payment.TABLE_NAME);
            payId=ServerMain.newID(Contracts.Payment.TABLE_NAME);
            qry="insert into "+Contracts.Payment.TABLE_NAME+" values(?,?,?,?,?,?)";
            int x=MysqlAdapter.executeSql(qry, payId,date,payAmt,transId,cnt,0);            
        } catch (Exception e) {
            throw new PaymentException(PaymentException.Exceptions.PAYMENT_NOT_EXISTING);
        }
    }
    
    
    public void searchPayment(){
        
        try 
        {
            ResultSet rs=null;
            rv=ServerMain.getRev(Contracts.Payment.TABLE_NAME, Contracts.Payment.TABLE_COLUMN_ID, payId);
            qry="select * from "+Contracts.Payment.TABLE_NAME+" where "+Contracts.Payment.TABLE_COLUMN_TRANSACTION_ID+"=?";
            
                
                rs=MysqlAdapter.querySql(qry, transId);
                if(rs.first())
                {
                    payId=rs.getString(2);
                    date=rs.getLong(3);
                    payAmt=rs.getDouble(4);
                    
                }else{
                 throw new PaymentException(PaymentException.Exceptions.PAYMENT_NOT_EXISTING);   
                }   
        } 
        catch (Exception e) 
        {
            JOptionPane.showMessageDialog(null, e);
        }   
        
    }
}
    

