
package lk.vcnet.storemodule.server;

import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import lk.vcnet.storemodule.controller.exceptions.OrderException;
import lk.vcnet.storemodule.db.Contracts;
import me.colhh.mysqlmanager.MysqlAdapter;


public class Order {
    private String vOID, vSID, vCheck;
    private long vDate;
    private int vCnt,msgconfirm,vrev;
    private ArrayList<Order_Product> order_list = new ArrayList<>();    
    ResultSet rs=null;

    public ArrayList<Order_Product> getOrderList() {
        return order_list;
    }
    
    public void setOrderProductObjects(String[] pid,int[] qty)
    {
        Order_Product op;
        for(int x=0;x<pid.length;x++)
        {
            op = new Order_Product();
            op.setPID(pid[x]);
            op.setQty(qty[x]);
            order_list.add(op);
        }
    }    

    public String getvOID() {
        return vOID;
    }

    public void setvOID(String vOID) {
        this.vOID = vOID;
    }

    public String getvSID() {
        return vSID;
    }

    public void setvSID(String vSID) {
        this.vSID = vSID;
    }

    public String getvCheck() {
        return vCheck;
    }

    public void setvCheck(String vCheck) {
        this.vCheck = vCheck;
    }

    public long getvDate() {
        return vDate;
    }

    public void setvDate(long vDate) {
        this.vDate = vDate;
    }

    public int getvCnt() {
        return vCnt;
    }

    public void setvCnt(int vCnt) {
        this.vCnt = vCnt;
    }

    public int getVrev() {
        return vrev;
    }

    public void setVrev(int vrev) {
        this.vrev = vrev;
    }

    public ArrayList<Order_Product> getOrder_list() {
        return order_list;
    }

    public void setOrder_list(ArrayList<Order_Product> order_list) {
        this.order_list = order_list;
    }    

    public void addOrder(){        
        try {          
               String sqladdOrder="Insert into "+Contracts.Order.TABLE_NAME+"values(?,?,?,?,?,?)";
               String strnewID = ServerMain.newID(Contracts.Order.TABLE_NAME);
               int intnweID =ServerMain.countRecords(Contracts.Order.TABLE_NAME);
               MysqlAdapter.executeSql(sqladdOrder,strnewID, vSID, vDate, vCheck, intnweID,0);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    
    public void searchOrder(){        
        try {
            vrev=ServerMain.getRev(Contracts.Order.TABLE_NAME,Contracts.Order.TABLE_COLUMN_ID, vOID);
            String sqlsearchorder="select * from "+Contracts.Order.TABLE_NAME+" where "+Contracts.Order.TABLE_COLUMN_ID+"=?";
            MysqlAdapter.executeSql(sqlsearchorder,vOID);
            if(rs.first()){                  
                    vOID=rs.getString(1);
                    vSID=rs.getString(2);
                    vDate=rs.getLong(3);
                    vCheck=rs.getString(4);
            }
            else{
                throw new OrderException(OrderException.Exceptions.ORDER_NOT_EXISTING);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    
    public void updateOrder(){        
        try {
            int chkord=ServerMain.getRev(Contracts.Order.TABLE_NAME, Contracts.Order.TABLE_COLUMN_ID, vOID);
            if(vrev==chkord){
                chkord=chkord+1;
                String sqlupdateorder="update "+Contracts.Order.TABLE_NAME+" set "+Contracts.Order.TABLE_COLUMN_ID+"=?, "+Contracts.Order.TABLE_COLUMN_SUPPLIER_ID_F+"=?, "+Contracts.Order.TABLE_COLUMN_DATE+"=?, "+Contracts.Order.TABLE_COLUMN_CHECK+"=?, where "+Contracts.Order.TABLE_COLUMN_ID+"=?";
                MysqlAdapter.executeSql(sqlupdateorder, vSID, vDate, vCheck, vOID);
            }
            else{
                throw new OrderException(OrderException.Exceptions.ORDER_UPDATED);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    
    public void deletOrder(){        
        try {
            String checkOrder="select "+Contracts.Order.TABLE_COLUMN_ID+" from "+Contracts.Order.TABLE_NAME+" where "+Contracts.Order.TABLE_COLUMN_ID+"=?";
            boolean bl=MysqlAdapter.exists(checkOrder,vOID);
            if(bl){
                String sqldeltorder="delete from "+Contracts.Order.TABLE_NAME+" where "+Contracts.Order.TABLE_COLUMN_ID+"=?";
                MysqlAdapter.executeSql(sqldeltorder,vOID);
            }
            else{
                throw new OrderException(OrderException.Exceptions.ORDER_NOT_EXISTING);
            }            
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
