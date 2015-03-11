
package lk.vcnet.storemodule.server;

import java.sql.ResultSet;
import javax.swing.JOptionPane;
import lk.vcnet.storemodule.controller.exceptions.InventoryException;
import lk.vcnet.storemodule.db.Contracts;
import me.colhh.mysqlmanager.MysqlAdapter;


public class Inventory {
    private String invntID,vpid;
    private int vQty,vcnt,msgconfirm,vrev;
    ResultSet rs=null;

    public String getInvntID() {
        return invntID;
    }

    public void setInvntID(String invntID) {
        this.invntID = invntID;
    }

    public String getVpid() {
        return vpid;
    }

    public void setVpid(String vpid) {
        this.vpid = vpid;
    }

    public Integer getvQty() {
        return vQty;
    }

    public void setvQty(Integer vQty) {
        this.vQty = vQty;
    }

    public Integer getVcnt() {
        return vcnt;
    }

    public void setVcnt(Integer vcnt) {
        this.vcnt = vcnt;
    }

    public ResultSet getRs() {
        return rs;
    }

    public void setRs(ResultSet rs) {
        this.rs = rs;
    } 
    
    public void addInventory(){        
        try {
            String chkAddInven="select "+Contracts.Inventory.TABLE_COLUMN_ID+" from "+Contracts.Inventory.TABLE_NAME+" where "+Contracts.Inventory.TABLE_COLUMN_PID_F+"=?";
            boolean bl=MysqlAdapter.exists(chkAddInven, invntID);
            if(!bl){
                String sqlinsert="insert into "+Contracts.Inventory.TABLE_NAME+"values(?,?,?,?,?)";
                String strNewID = ServerMain.newID(Contracts.Inventory.TABLE_NAME);
                Integer intNewID= ServerMain.countRecords(Contracts.Inventory.TABLE_NAME);
                MysqlAdapter.executeSql(sqlinsert,strNewID,vQty,vpid,intNewID,0);            
            }
            else{
                throw new InventoryException(InventoryException.Exceptions.INVENTORY_ALREADY_EXISTS);
            }                                
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    
    public void searchInventory()
    {        
        try {
            vrev=ServerMain.getRev(Contracts.Inventory.TABLE_NAME,Contracts.Inventory.TABLE_COLUMN_ID, invntID);
            String sqlsearch="select * from "+Contracts.Inventory.TABLE_NAME+" where "+Contracts.Inventory.TABLE_COLUMN_ID+"=?";
            rs=MysqlAdapter.querySql(sqlsearch,invntID);             
            if(rs.first()){                
                invntID=rs.getString(1);
                vQty=rs.getInt(2);
                vpid=rs.getString(3);
            }
            else{
                throw new InventoryException(InventoryException.Exceptions.INVENTORY_NOT_EXISTING);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    
    public void updateInventory(){        
        try {    
            int chkInvntry=ServerMain.getRev(Contracts.Inventory.TABLE_NAME,Contracts.Inventory.TABLE_COLUMN_ID, vpid);
            if(vrev==chkInvntry){
                chkInvntry=chkInvntry+1;
                String sqlupdate="update "+Contracts.Inventory.TABLE_NAME+" set "+Contracts.Inventory.TABLE_COLUMN_QTY+"=?,"+Contracts.Inventory.TABLE_COLUMN_PID_F+"=? where "+Contracts.Inventory.TABLE_COLUMN_ID+"=?";
                MysqlAdapter.executeSql(sqlupdate,vQty,vpid);            
            }
            else{
                throw new InventoryException(InventoryException.Exceptions.INVENTORY__UPDATED);
            }
            
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    
    public void deleteInventory(){
        try {
            String checkInventory="select "+Contracts.Inventory.TABLE_COLUMN_ID+" from "+Contracts.Inventory.TABLE_NAME+" where "+Contracts.Inventory.TABLE_COLUMN_ID+"=?";
            boolean bl=MysqlAdapter.exists(checkInventory,invntID);
            if(bl){
                String sqldelet="delete from "+Contracts.Inventory.TABLE_NAME+" where "+Contracts.Inventory.TABLE_COLUMN_ID+"=?";
                MysqlAdapter.executeSql(sqldelet,invntID);
            }
            else{
                throw new InventoryException(InventoryException.Exceptions.INVENTORY_NOT_EXISTING);
            }            
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
