
package lk.vcnet.storemodule.server;

import java.sql.ResultSet;
import lk.vcnet.storemodule.controller.CategoryControllerInterface;
import lk.vcnet.storemodule.controller.exceptions.CategoryException;
import lk.vcnet.storemodule.db.Contracts;
import me.colhh.mysqlmanager.MysqlAdapter;


public class Category implements CategoryControllerInterface{
    
    private String vCGID, vCGName;
    private int vCnt,msgconfirm,vrev;
    ResultSet rs=null;

    public String getvCGID() {
        return vCGID;
    }

    public void setvCGID(String vCGID) {
        this.vCGID = vCGID;
    }

    public String getvCGName() {
        return vCGName;
    }

    public void setvCGName(String vCGName) {
        this.vCGName = vCGName;
    }

    public Integer getvCnt() {
        return vCnt;
    }

    public void setvCnt(Integer vCnt) {
        this.vCnt = vCnt;
    }

    public ResultSet getRs() {
        return rs;
    }

    public void setRs(ResultSet rs) {
        this.rs = rs;
    }

    @Override
    public void addCategory(){        
        try {
            String chkAddCtgry="select "+Contracts.Category.TABLE_COLUMN_ID+" from "+Contracts.Category.TABLE_NAME+" where "+Contracts.Category.TABLE_COLUMN_NAME+"=?";
            boolean bl=MysqlAdapter.exists(chkAddCtgry, vCGName);
            if(!bl){
                String sqladdcategory="insert into "+Contracts.Category.TABLE_NAME+" values(?,?,?)";
                String strNewID = ServerMain.newID(Contracts.Category.TABLE_NAME);
                Integer intNewID =ServerMain.countRecords(Contracts.Category.TABLE_NAME);
                MysqlAdapter.executeSql(sqladdcategory,strNewID,vCGName,intNewID,0);
            }
            else{
                throw new CategoryException(CategoryException.Exceptions.CATEGORY_ALREADY_EXISTS);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    
    public void searchCategory(){
        try {
            vrev=ServerMain.getRev(Contracts.Category.TABLE_NAME, Contracts.Category.TABLE_COLUMN_ID, vCGID);
            String sqlSearchCategry="select * from "+Contracts.Category.TABLE_NAME+" where "+Contracts.Category.TABLE_COLUMN_ID+"=?";
            MysqlAdapter.executeSql(sqlSearchCategry,vCGID);
            if(rs.first()){
                vCGID=rs.getString(1);
                vCGName=rs.getString(2);
                vCnt=rs.getInt(3);
            }
            else{
                throw new CategoryException(CategoryException.Exceptions.CATEGORY_NOT_EXISTING);
            }
            
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    
    private void updateCategory(){
        try {
            int chkup=ServerMain.getRev(Contracts.Category.TABLE_NAME, Contracts.Category.TABLE_COLUMN_ID, vCGID);
            if(vrev==chkup){
                chkup=chkup+1;
                String sqlUpdtCategry="update category set CGName=?,Rev=? where CGID=?";
                MysqlAdapter.executeSql(sqlUpdtCategry,vCGName,chkup,vCGID);
            }
            else{
                throw new CategoryException(CategoryException.Exceptions.CATEGORY__UPDATED);
            }
            
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    
    private void deltCategory(){
        try {
            String checkCatgory="select "+Contracts.Category.TABLE_COLUMN_NAME+" from "+Contracts.Category.TABLE_NAME+" where "+Contracts.Category.TABLE_COLUMN_ID+"=?";
            boolean bl=MysqlAdapter.exists(checkCatgory,vCGID);
            if(bl){
                String sqldeltCatgry="delete from "+Contracts.Category.TABLE_NAME+" where "+Contracts.Category.TABLE_COLUMN_ID+"=?";
                MysqlAdapter.executeSql(sqldeltCatgry,vCGID);
            }
            else{
               throw new CategoryException(CategoryException.Exceptions.CATEGORY_NOT_EXISTING);
            }                       
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
