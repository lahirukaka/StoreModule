
package lk.vcnet.storemodule.server;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import lk.vcnet.storemodule.controller.exceptions.BrandException;
import lk.vcnet.storemodule.db.Contracts;
import me.colhh.mysqlmanager.MysqlAdapter;



   public class Brand {
   private String BID,BName;
   private int Cnt,Rev;
   ResultSet Rs=null;

    public String getBID()
    {
        return BID;
    }

    public String getBName() {
        return BName;
    }

    public void setBName(String BName) {
        this.BName = BName;
    }

    public int getCnt() {
        return Cnt;
    }

    public void setCnt(int Cnt) {
        this.Cnt = Cnt;
    }  

    public int getRev() {
        return Rev;
    }

    public void setRev(int Rev) {
        this.Rev = Rev;
    }
    
    
    public void addBrand() throws IOException, SQLException
    {
        
        String sqlqueryinsert="insert into "+Contracts.Brand.TABLE_NAME+"("+Contracts.Brand.TABLE_COLUMN_BID+","+Contracts.Brand.TABLE_COLUMN_BNAME+","+Contracts.Brand.TABLE_COLUMN_COUNT+","+Contracts.Brand.TABLE_COLUMN_REV+")values (?,?,?,?)";
        try {            
            String sqlsrch="select * from "+Contracts.Brand.TABLE_NAME+" where "+Contracts.Brand.TABLE_COLUMN_BNAME+"=?";
            boolean res=MysqlAdapter.exists(sqlsrch, BName);
            if(!res)
            {
              String SID=ServerMain.newID(Contracts.Brand.TABLE_NAME);
              Integer COUNT=ServerMain.countRecords(Contracts.Brand.TABLE_NAME);
              int executeSql = MysqlAdapter.executeSql(sqlqueryinsert,SID,BName,COUNT,0);
            }
            else
            {
                throw new BrandException(BrandException.Exceptions.BRAND_UPDATED);
            }
        } 
        catch (Exception e)
        {
            System.err.println(e.getMessage());
        }
    }
    
    public void searchBrand()
    {
        try 
        {
            Rev=ServerMain.getRev(Contracts.Brand.TABLE_NAME,Contracts.Brand.TABLE_COLUMN_BID,BID);
            String sqlquerysearch="select * from "+Contracts.Brand.TABLE_NAME+" where "+Contracts.Brand.TABLE_COLUMN_BNAME+"=?";
            ResultSet Rs=null;
            {
                Rs=MysqlAdapter.querySql(sqlquerysearch,BID);
                if(Rs.first())
                {
                    BName=Rs.getString(2);                 
                }          
            }
        }
        catch (Exception e) 
        {
            System.err.println(e.getMessage());
        }
    }
    public void updateBrand()
    {
        String sqlqueryupdate="update "+Contracts.Brand.TABLE_NAME+" set "+Contracts.Brand.TABLE_COLUMN_BID+"=?,"+Contracts.Brand.TABLE_COLUMN_BNAME+"=?,"+Contracts.Brand.TABLE_COLUMN_REV+"=? where "+Contracts.Brand.TABLE_COLUMN_BID+"=?";
        try 
        {
            int checkval=ServerMain.getRev(Contracts.Brand.TABLE_NAME,Contracts.Brand.TABLE_COLUMN_BID,BID);
            if(Rev==checkval)
            {
                checkval=checkval+1;
                MysqlAdapter.executeSql(sqlqueryupdate,Contracts.Brand.TABLE_COLUMN_BID,Contracts.Brand.TABLE_COLUMN_BNAME,Contracts.Brand.TABLE_COLUMN_COUNT,checkval);
            }
        }
        catch (Exception e) 
        {
            System.err.println(e.getMessage());
        }
    }
    
    public void deleteBrand()
    {     
        try 
        {
            String sqlquerydelt="delete from "+Contracts.Brand.TABLE_NAME+" where "+Contracts.Brand.TABLE_COLUMN_BID+"=?";
            boolean RES=MysqlAdapter.exists(sqlquerydelt,BID);
            if(RES)
            {
                sqlquerydelt="delete from "+Contracts.Brand.TABLE_NAME+" where "+Contracts.Brand.TABLE_COLUMN_BID+"=?";
                int x=MysqlAdapter.executeSql(sqlquerydelt,BID);
            }
            else
            {
                throw new BrandException(BrandException.Exceptions.BRAND_NOT_EXISTING);
            }
            
        }
        catch (Exception e) 
        {
            System.err.println(e.getMessage());
        }
    }    
}
