/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.vcnet.storemodule.server;

import java.sql.*;
import javax.swing.JOptionPane;
import lk.vcnet.storemodule.controller.exceptions.TransactionException;
import lk.vcnet.storemodule.db.Contracts;
import me.colhh.mysqlmanager.MysqlAdapter;

/**
 *
 * @author HP™
 */
public class Transactions {
    
    private long date;
    private double amnt,bal;
    private int cnt,rv,pay;
    private String trsid,oid,qry;
    private Order order;
    ResultSet rs;
    
    public Transactions(Order order)
    {
        this.order = order;
    }
    
    public void makeTransaction()throws Exception
    {
        String pid;
        int qty;
        ResultSet rs2;
        try{
            qry="select "+Contracts.Transaction.TABLE_COLUMN_ID+" from "+Contracts.Transaction.TABLE_NAME+" where "+Contracts.Transaction.TABLE_COLUMN_ORDER_ID_F+"=?";
            boolean res=MysqlAdapter.exists(qry, oid);
            if(!res)
            {
                cnt=ServerMain.countRecords(Contracts.Transaction.TABLE_NAME);
                trsid=ServerMain.newID(Contracts.Transaction.TABLE_NAME);
                int list_count = order.getOrderList().size();
                qry="select "+Contracts.Product.TABLE_COLUMN_PRICE+" from "+Contracts.Product.TABLE_NAME+" where "+Contracts.Product.TABLE_COLUMN_ID+"=?";
                double unit_price = 0;

                for(Order_Product op : order.getOrderList())
                {
                    unit_price = MysqlAdapter.querySql(qry, op.getPID()).getDouble(1);
                    amnt += unit_price * op.getQty();
                }
                
                qry="insert into "+Contracts.Transaction.TABLE_NAME+" values(?,?,?,?,?,?,?)";
                bal=amnt-pay;
                int x=MysqlAdapter.executeSql(qry,trsid,date,amnt,bal,oid,cnt,0);
            }
        }catch (Exception e){
            throw new TransactionException(TransactionException.Exceptions.TRANSACTION_ALREADY_EXISTS);
        }
    }
    
    public void searchTransactionInOrderID()throws Exception
    {
        try {
            rv=ServerMain.getRev(Contracts.Transaction.TABLE_NAME, Contracts.Transaction.TABLE_COLUMN_ID, trsid);
            qry="select * from "+Contracts.Transaction.TABLE_NAME+" where "+Contracts.Transaction.TABLE_COLUMN_ORDER_ID_F+"=?";
            rs=MysqlAdapter.querySql(qry, oid);
            if(rs.first())
            {
                trsid=rs.getString(1);
                date=rs.getLong(2);
                amnt=rs.getDouble(3);
                bal=rs.getDouble(4);
            }
        } catch (Exception e) {
            throw new TransactionException(TransactionException.Exceptions.TRANSACTION_NOT_EXISTING);
        }
    }
    
    public void searchTransactionInTRSID()throws Exception
    {
        try {
            rv=ServerMain.getRev(Contracts.Transaction.TABLE_NAME, Contracts.Transaction.TABLE_COLUMN_ID, trsid);
            qry="select * from "+Contracts.Transaction.TABLE_NAME+" where "+Contracts.Transaction.TABLE_COLUMN_ID+"=?";
            rs=MysqlAdapter.querySql(qry, oid);
            if(rs.first())
            {
                trsid=rs.getString(1);
                date=rs.getLong(2);
                amnt=rs.getDouble(3);
                bal=rs.getDouble(4);
                oid=rs.getString(5);
            }
        } catch (Exception e) {
            throw new TransactionException(TransactionException.Exceptions.TRANSACTION_NOT_EXISTING);
        }
    }
    
    public void updateTransaction()throws Exception
    {
        int y;
        qry="update "+Contracts.Transaction.TABLE_NAME+" set "+Contracts.Transaction.TABLE_COLUMN_ID+"=?,"+Contracts.Transaction.TABLE_COLUMN_DATE+"=?,"+Contracts.Transaction.TABLE_COLUMN_AMOUNT+"=?,"+Contracts.Transaction.TABLE_COLUMN_BALANCE+"=?,"+Contracts.Transaction.TABLE_COLUMN_REV+"=? where "+Contracts.Transaction.TABLE_COLUMN_ORDER_ID_F+"=?";
        try {
            y=ServerMain.getRev(Contracts.Transaction.TABLE_NAME, Contracts.Transaction.TABLE_COLUMN_ID, trsid);
            if(rv==y)
            {
                y=y+1;
                int res=MysqlAdapter.executeSql("qry", trsid,date,amnt,bal,y,oid);
            }
            else
                throw new TransactionException(TransactionException.Exceptions.TRANSACTION_UPDATED);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void deleteTransaction()throws Exception
    {
        qry="select "+Contracts.Transaction.TABLE_COLUMN_ID+" from "+Contracts.Transaction.TABLE_NAME+" where "+Contracts.Transaction.TABLE_COLUMN_ORDER_ID_F+"=?";
        try {
            boolean res=MysqlAdapter.exists(qry, oid);
            if(res)
            {
                qry="delete from "+Contracts.Transaction.TABLE_NAME+" where "+Contracts.Transaction.TABLE_COLUMN_ORDER_ID_F+"=?";
                int x=MysqlAdapter.executeSql(qry, oid);
            }
            else
                throw new TransactionException(TransactionException.Exceptions.TRANSACTION_NOT_EXISTING);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
}
