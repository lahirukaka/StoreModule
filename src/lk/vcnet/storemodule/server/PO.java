
package lk.vcnet.storemodule.server;
import java.io.IOException;
import java.sql.*;
import javax.naming.spi.DirStateFactory;
import javax.swing.JOptionPane;
import lk.vcnet.storemodule.controller.exceptions.POException;
import lk.vcnet.storemodule.db.Contracts;
import me.colhh.mysqlmanager.MysqlAdapter;

public class PO 
{
    private String POID,TRSID;
    private Integer cnt,Rev;
    ResultSet RS=null;

    public String getPOID() {
        return POID;
    }

    public void setPOID(String POID) {
        this.POID = POID;
    }

    public String getTRSID() {
        return TRSID;
    }

    public void setTRSID(String TRSID) {
        this.TRSID = TRSID;
    }
    
    
    public  void addPO()
    {
        try
        {
            String sqlqueryinsert=("insert into "+Contracts.PO.TABLE_NAME+"("+Contracts.PO.TABLE_COLUMN_ID+","+Contracts.PO.TABLE_COLUMN_TRANSACTION_ID+","+Contracts.PO.TABLE_COLUMN_COUNT+","+Contracts.PO.TABLE_COLUMN_REV+")values(?,?,?,?)");
            String SID=ServerMain.newID(Contracts.PO.TABLE_NAME);
            Integer COUNT=ServerMain.countRecords(Contracts.PO.TABLE_NAME);
            int executeaql=MysqlAdapter.executeSql(sqlqueryinsert,SID,TRSID,COUNT,0);
            JOptionPane.showMessageDialog(null,"INSERT SUCCESFUL","PO",JOptionPane.INFORMATION_MESSAGE);
            
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
    }
    public void searchPO()
    {
         String sqlquerysearch="select * from "+Contracts.PO.TABLE_NAME+" where "+Contracts.PO.TABLE_COLUMN_ID+"=?";
        try {
              
               RS=MysqlAdapter.querySql(sqlquerysearch,POID);  
               if(RS.first())
                {
                    POID=RS.getString(2);  
                    TRSID=RS.getString(3);              
                }   
            } 
        catch (Exception e) 
        {
            System.err.println(e.getMessage());
        }
    }
    public  void updatePO()
    {
         String sqlqueryupdate="Update "+Contracts.PO.TABLE_NAME+" set "+Contracts.PO.TABLE_COLUMN_ID+"=?,"+Contracts.PO.TABLE_COLUMN_TRANSACTION_ID+"=?,"+Contracts.PO.TABLE_COLUMN_COUNT+"=?,"+Contracts.PO.TABLE_COLUMN_REV+"=?";
            
          try 
        {
            int checkvalpo=ServerMain.getRev(Contracts.PO.TABLE_NAME,Contracts.PO.TABLE_COLUMN_ID,POID);
            if(Rev==checkvalpo)
            {
                checkvalpo=checkvalpo+1;
                MysqlAdapter.executeSql(sqlqueryupdate,POID,TRSID,cnt,checkvalpo);
                JOptionPane.showMessageDialog(null,"UPDATE SUCCESSFUL","Update of PO",JOptionPane.INFORMATION_MESSAGE);
            }
        } 
          
        catch (Exception e) 
        {
            System.err.println(e.getMessage());
        }
    }
    public void deletePO()
    {
        String sqlquerydelt="delete from "+Contracts.PO.TABLE_NAME+" where "+Contracts.PO.TABLE_COLUMN_ID+"=?";
        try {
           
            MysqlAdapter.executeSql(sqlquerydelt,POID);
            boolean RES=MysqlAdapter.exists(sqlquerydelt,POID);
             if(RES)
              {
                sqlquerydelt="delete from "+Contracts.PO.TABLE_NAME+" where "+Contracts.PO.TABLE_COLUMN_ID+"=?";
                int x=MysqlAdapter.executeSql(sqlquerydelt,POID);
                JOptionPane.showMessageDialog(null,"DELETE SUCCESSFUL","Delete Of PO",JOptionPane.INFORMATION_MESSAGE);
             }
             else
             {
               throw new POException(POException.Exceptions.PO_NOT_EXISTING);
             }
        } 
        
        catch (Exception e) 
        {
            System.err.println(e.getMessage());
        }
    }
}
