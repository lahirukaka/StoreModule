/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.vcnet.storemodule.server;

import java.sql.*;
import javax.swing.JOptionPane;
import lk.vcnet.storemodule.controller.exceptions.SuppilerException;
import lk.vcnet.storemodule.db.Contracts;
import me.colhh.mysqlmanager.MysqlAdapter;
/**
 *
 * @author user
 */
public class Supplier {
    
    private String sid,sname,sadr,tele,city,qry;
    private int rv;
    ResultSet rs=null;
    Connection conn;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSadr() {
        return sadr;
    }

    public void setSadr(String sadr) {
        this.sadr = sadr;
    }

    public String getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    
    public void registerSupplier()
    {
        try
        {
            //qry="select "+Contracts.Supplier.TABLE_COLUMN_NAME+" from "+Contracts.Supplier.TABLE_NAME+" where "+Contracts.Supplier.TABLE_COLUMN_ID+"=?";
            boolean res=MysqlAdapter.exists(Contracts.Supplier.QUERY_FROM_ID, sid);
            if(!res)
            {
                qry="insert into "+Contracts.Supplier.TABLE_NAME+" values(?,?,?,?,?,?,?)";
                int cnt=ServerMain.countRecords(Contracts.Supplier.TABLE_NAME);
                String id=ServerMain.newID(Contracts.Supplier.TABLE_NAME);
                int x=MysqlAdapter.executeSql(qry, id, sname, sadr,tele,city,cnt,0);
                if(x>0)
                    JOptionPane.showMessageDialog(null, "Entered To Supplier");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void updateSupplier()
    {
        try 
        {
            int y=ServerMain.getRev(Contracts.Supplier.TABLE_NAME, Contracts.Supplier.TABLE_COLUMN_ID, sid);
            if(rv==y)
            {
                y=y+1;
                qry="update "+Contracts.Supplier.TABLE_NAME+" set "+Contracts.Supplier.TABLE_COLUMN_NAME+"=?, "+Contracts.Supplier.TABLE_COLUMN_ADDRESS+"=?, "+Contracts.Supplier.TABLE_COLUMN_EXP_TELEPHONE+"=?, "+Contracts.Supplier.TABLE_COLUMN_CITY+"=?,"+Contracts.Supplier.TABLE_COLUMN_REV+"=? where "+Contracts.Supplier.TABLE_COLUMN_ID+"=?";
                int x=MysqlAdapter.executeSql(qry,sname,sadr,tele,city,y,sid);
                if(x>0)
                    JOptionPane.showMessageDialog(null, "Updated Successfull");
            }
            else
                throw new SuppilerException(SuppilerException.Exceptions.SUPPLIER_UPDATED);
        } 
        catch (Exception e) 
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void searchSupplier()
    {
        try 
        {
            rv=ServerMain.getRev(Contracts.Supplier.TABLE_NAME, Contracts.Supplier.TABLE_COLUMN_ID, sid);
            qry="select "+Contracts.Supplier.TABLE_COLUMN_NAME+" from "+Contracts.Supplier.TABLE_NAME+" where "+Contracts.Supplier.TABLE_COLUMN_ID+"=?";
            boolean res=MysqlAdapter.exists(qry, sid);
            if(res)
            {
                qry="select * from "+Contracts.Supplier.TABLE_NAME+" where "+Contracts.Supplier.TABLE_COLUMN_ID+"=?";
                rs=MysqlAdapter.querySql(qry, sid);
                if(rs.first())
                {
                    sname=rs.getString(2);
                    sadr=rs.getString(3);
                    tele=rs.getString(4);
                    city=rs.getString(5);
                }
            }
            
        } 
        catch (Exception e) 
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void deleteSuppplier()
    {
        try 
        {
            qry="select * from "+Contracts.Supplier.TABLE_NAME+" where "+Contracts.Supplier.TABLE_COLUMN_ID+"=?";
            boolean res=MysqlAdapter.exists(qry, sid);
            if(res)
            {
                qry="delete from "+Contracts.Supplier.TABLE_NAME+" where "+Contracts.Supplier.TABLE_COLUMN_ID+"=?";
                int x=MysqlAdapter.executeSql(qry, sid);
            }
            else
                throw new SuppilerException(SuppilerException.Exceptions.SUPPLIER_NOT_EXISTING);
        } 
        catch (Exception e) 
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }
}
