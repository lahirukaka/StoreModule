/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.vcnet.storemodule.server;

import java.io.FileInputStream;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.*;
import java.util.Properties;
import javax.swing.JOptionPane;
import me.colhh.mysqlmanager.MysqlAdapter;
import me.colhh.usermanager.rmi.first.Adapter.DBAdapter;
import me.colhh.usermanager.rmi.first.Holder.User;

/**
 *
 * @author user
 */
public class ServerMain {
    
    private static int  v1;
    public static final String ACCESSOR_OBJECT_NAME = "RemoteObject";
    
    
    public static int countRecords(String ptbl)throws Exception
    {
        try 
        {
            ResultSet rs=null;
            String tbl=ptbl;
            String qry="select max(Cnt) from "+tbl;
            rs=MysqlAdapter.querySql(qry);
            if(rs.first())
            {
                v1=rs.getInt(1)+1;
            }
            return v1;
        }
        catch (Exception e) 
        {
            System.out.println(e);
            throw new Exception();
        }        
    }
    
    public static int getRev(String ptbl,String id1,String id2)throws Exception
    {
        String qry;
        ResultSet rs;
        try 
        {
            qry="select Rev from "+ptbl+" where "+id1+"=?";
            rs=MysqlAdapter.querySql(qry, id2);
            return rs.getInt(1);
            
        } catch (Exception e) 
        {
            JOptionPane.showMessageDialog(null, e);
            throw new Exception();
        }
    }
    
    public static String newID(String ptbl)throws Exception
    {
        try 
        {
            String lt=null;
            if(ptbl.equalsIgnoreCase("Brand"))
            {
                lt="B";
            }
            else
            {
                if(ptbl.equalsIgnoreCase("Category"))
                {
                    lt="C";
                }
                else
                {
                    if(ptbl.equalsIgnoreCase("Inventory"))
                    {
                        lt="IV";
                    }
                    else
                    {
                        if(ptbl.equalsIgnoreCase("Order"))
                        {
                            lt="O";
                        }
                        else
                        {
                            if(ptbl.equalsIgnoreCase("OrderProduct"))
                            {
                                lt="OP";
                            }
                            else
                            {
                                if(ptbl.equalsIgnoreCase("PO"))
                                {
                                    lt="PO";
                                }
                                else
                                {
                                    if(ptbl.equalsIgnoreCase("Product"))
                                    {
                                        lt="P";
                                    }
                                    else
                                    {
                                        if(ptbl.equalsIgnoreCase("Supplier"))
                                        {
                                            lt="S";
                                        }
                                        else
                                        {
                                            if(ptbl.equalsIgnoreCase("Transaction"))
                                            {
                                                lt="T";
                                            }
                                            else
                                            {
                                                JOptionPane.showMessageDialog(null, "Wrong Table Name");
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }            
            }            
            int v1=ServerMain.countRecords(ptbl);
            String v2=String.valueOf(v1);
            v2=lt+v2;
            return v2;
            
        } 
        catch (Exception e) 
        {
            System.out.println(e);
            throw new Exception();
        }
    }
    
    @SuppressWarnings("ConvertToTryWithResources")
    public static boolean registerAdmin()
    {
        try {
            String qry="select "+me.colhh.usermanager.rmi.first.Adapter.Contracts.FIELD_NAME_UID+" from "+me.colhh.usermanager.rmi.first.Adapter.Contracts.TABLE_NAME+" where "+me.colhh.usermanager.rmi.first.Adapter.Contracts.FIELD_NAME_IS_WRITABLE+"=?";
            boolean res=MysqlAdapter.exists(qry, true);
            if(!res)
            {
                FileInputStream fis=new FileInputStream("Resources/admin.properties");
                Properties prp=new Properties();
                prp.load(fis);
                fis.close();
                
                DBAdapter adapter = new DBAdapter();
                User us=new User(adapter.getNextUid(), prp.getProperty("uname", "admin"));
                us.setPasswordHash(prp.getProperty("password", "abc@123"))
                .setIsWritable(true);
                adapter.addUser(us);
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println(e.getMessage() + e.getClass());
            return false;
        }
        
    }
    
    private static void startServer() throws RemoteException, Exception
    {
        System.setProperty("java.rmi.server.hostname", "localhost");
        Registry reg=LocateRegistry.createRegistry(9293);
        reg.rebind(ACCESSOR_OBJECT_NAME, new ControllersAccessor());
        System.out.println("Server Running.......[OK]");
    }
    
    public static void main(String[] args) 
    {
        try {
            registerAdmin();
            startServer();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getStackTrace());
            System.exit(1);
        }
        
    }
}
