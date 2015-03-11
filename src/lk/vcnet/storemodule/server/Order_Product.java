
package lk.vcnet.storemodule.server;
import java.sql.*;
import javax.swing.JOptionPane;
import lk.vcnet.storemodule.controller.exceptions.OrderProductException;
import lk.vcnet.storemodule.db.Contracts;
import me.colhh.mysqlmanager.MysqlAdapter;


public class Order_Product 
{
   private String OID,PID;
   private int Qty,cnt,Rev;
   private float Prc;
   ResultSet rs=null;
   
    public String getOID() {
        return OID;
    }

    public void setOID(String OID) {
        this.OID = OID;
    }

    public String getPID() {
        return PID;
    }

    public void setPID(String PID) {
        this.PID = PID;
    }

    public Integer getQty() {
        return Qty;
    }

    public void setQty(Integer Qty) {
        this.Qty = Qty;
    }

    public float getPrc() {
        return Prc;
    }

    public void setPrc(float Prc) {
        this.Prc = Prc;
    }
   public Integer getCnt() {
        return cnt;
    }

    public Integer getRev() {
        return Rev;
    }

    public void setRev(Integer Rev) {
        this.Rev = Rev;
    }

    public void setCnt(Integer cnt) 
    {
        this.cnt = cnt;
    } 
   
   public void addOrderProduct()
   {
       try
       {
            String sqlqueryinsert="insert into "+Contracts.OrderProduct.TABLE_NAME+" ("+Contracts.OrderProduct.TABLE_COLUMN_ORDER_ID_F+","+Contracts.OrderProduct.TABLE_COLUMN_PRODUCT_ID_F+","+Contracts.OrderProduct.TABLE_COLUMN_Qty+","+Contracts.OrderProduct.TABLE_COLUMN_COUNT+","+Contracts.OrderProduct.TABLE_COLUMN_REV+")values (?,?,?,?,?)";
            Integer COUNT=ServerMain.countRecords(Contracts.OrderProduct.TABLE_NAME);
            int executeSql = MysqlAdapter.executeSql(sqlqueryinsert,OID,PID,COUNT,0);                  
        } 
       catch(Exception e)
       {
           System.err.println(e);
       }         
   }
   public  void searchOrderProduct()
   {
       String sqlquerysearch="Select * from "+Contracts.OrderProduct.TABLE_NAME+" where "+Contracts.OrderProduct.TABLE_COLUMN_ORDER_ID_F+"=?";
       

        try {
               rs=MysqlAdapter.querySql(sqlquerysearch,OID);  
            if(rs.first())
                {
                    PID=rs.getString(2);  
                    Qty=rs.getInt(3); 
                }   
            } 
        
        catch (Exception e) 
        {
            System.err.println(e.getMessage());
        }    
   }
   public  void searchPID()
   {
      String sqlquery="Select * from "+Contracts.OrderProduct.TABLE_NAME+" where "+Contracts.OrderProduct.TABLE_COLUMN_ORDER_ID_F+"=?";
       try
        {
            rs=MysqlAdapter.querySql(sqlquery,Contracts.OrderProduct.TABLE_COLUMN_ORDER_ID_F);
            if(rs.first())
            {
                OID=rs.getString(2);
                Qty=rs.getInt(3);
            }
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
   }
    
   public void updateOrderProduct()
   {
       String sqlqueryupdate="update "+Contracts.OrderProduct.TABLE_NAME+"  set "+Contracts.OrderProduct.TABLE_COLUMN_ORDER_ID_F+"=?,"+Contracts.OrderProduct.TABLE_COLUMN_PRODUCT_ID_F+"=?,"+Contracts.OrderProduct.TABLE_COLUMN_Qty+"=?,"+Contracts.OrderProduct.TABLE_COLUMN_ORDER_ID_F+"=?,"+Contracts.OrderProduct.TABLE_COLUMN_REV+"=?";
        try 
        {
            int checkvalop=ServerMain.getRev(Contracts.OrderProduct.TABLE_NAME,Contracts.OrderProduct.TABLE_COLUMN_ORDER_ID_F,PID);
            if(Rev==checkvalop)
            {
              checkvalop=checkvalop+1;  
              MysqlAdapter.executeSql(sqlqueryupdate,OID,PID,Qty,cnt,checkvalop);
              JOptionPane.showMessageDialog(null,"UPDATE SUCCESSFUL","Update of Order Product",JOptionPane.INFORMATION_MESSAGE);
            } 
        }
        catch (Exception e) 
        {
            System.err.println(e.getMessage());
        }
   }
   
   public  void deleteOrderproduct()
   {
       String sqlquerydelt="delete from "+Contracts.OrderProduct.TABLE_NAME+"  where "+Contracts.OrderProduct.TABLE_COLUMN_ORDER_ID_F+"=?";
        try 
        {
            MysqlAdapter.executeSql(sqlquerydelt,OID);
            boolean RES=MysqlAdapter.exists(sqlquerydelt,OID);
             if(RES)
            {
                sqlquerydelt="delete from "+Contracts.OrderProduct.TABLE_NAME+"  where "+Contracts.OrderProduct.TABLE_COLUMN_ORDER_ID_F+"=?";
                int x=MysqlAdapter.executeSql(sqlquerydelt,OID);
                JOptionPane.showMessageDialog(null,"DELETE SUCCESSFUL","Delete Of Order Product",JOptionPane.INFORMATION_MESSAGE);
            }
            else
             {
                throw  new OrderProductException(OrderProductException.Exceptions.ORDER_PRODUCT_NOT_EXISTING);
             }
        } 
        catch (Exception e) 
        {
            System.err.println(e.getMessage());
        }
   }
}
