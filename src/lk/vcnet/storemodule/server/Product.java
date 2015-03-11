package lk.vcnet.storemodule.server;

import java.sql.ResultSet;
import javax.swing.JOptionPane;
import lk.vcnet.storemodule.controller.exceptions.ProductException;
import lk.vcnet.storemodule.db.Contracts;
import me.colhh.mysqlmanager.MysqlAdapter;


public class Product {
    
    private  int vcnt,msgconfirm,vrev;
    private  String vpid,vbid,vcid,vpnam,vwarrntperd,vmanfctcontry;
    private  double vprice,vweight;   
    private long vmandt,vexpdt;
    ResultSet rs=null;

    public Integer getVcnt() {
        return vcnt;
    }

    public void setVcnt(Integer vcnt) {
        this.vcnt = vcnt;
    }

    public Integer getVrev() {
        return vrev;
    }

    public void setVrev(Integer vrev) {
        this.vrev = vrev;
    }

    public String getVpid() {
        return vpid;
    }

    public void setVpid(String vpid) {
        this.vpid = vpid;
    }

    public String getVbid() {
        return vbid;
    }

    public void setVbid(String vbid) {
        this.vbid = vbid;
    }

    public String getVcid() {
        return vcid;
    }

    public void setVcid(String vcid) {
        this.vcid = vcid;
    }

    public String getVpnam() {
        return vpnam;
    }

    public void setVpnam(String vpnam) {
        this.vpnam = vpnam;
    }

    public String getVwarrntperd() {
        return vwarrntperd;
    }

    public void setVwarrntperd(String vwarrntperd) {
        this.vwarrntperd = vwarrntperd;
    }

    public String getVmanfctcontry() {
        return vmanfctcontry;
    }

    public void setVmanfctcontry(String vmanfctcontry) {
        this.vmanfctcontry = vmanfctcontry;
    }

    public double getVprice() {
        return vprice;
    }

    public void setVprice(double vprice) {
        this.vprice = vprice;
    }

    public double getVweight() {
        return vweight;
    }

    public void setVweight(double vweight) {
        this.vweight = vweight;
    }

    public long getVmandt() {
        return vmandt;
    }

    public void setVmandt(long vmandt) {
        this.vmandt = vmandt;
    }

    public long getVexpdt() {
        return vexpdt;
    }

    public void setVexpdt(long vexpdt) {
        this.vexpdt = vexpdt;
    }

    public ResultSet getRs() {
        return rs;
    }

    public void setRs(ResultSet rs) {
        this.rs = rs;
    }     
    
    public void addProduct(){        
        try {    
               String sqlinsert="insert into "+Contracts.Product.TABLE_NAME+" values (?,?,?,?,?,?,?,?,?,?,?,?)";  
               String strNewID = ServerMain.newID(Contracts.Product.TABLE_NAME);
               Integer intNewID =ServerMain.countRecords(Contracts.Product.TABLE_NAME);            
               MysqlAdapter.executeSql(sqlinsert,strNewID,vpnam,vmandt,vexpdt,vprice,vwarrntperd,vweight,vmanfctcontry,vbid,vcid,intNewID,0);                  
               JOptionPane.showMessageDialog(null,"INSERT SUCCESSFUL","PRODUCT",JOptionPane.INFORMATION_MESSAGE);                    
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    
    public void searchProductID(){        
        try {
            vrev=ServerMain.getRev(Contracts.Product.TABLE_NAME, Contracts.Product.TABLE_COLUMN_ID, vpid);
            String sqlsearch="select * from "+Contracts.Product.TABLE_NAME+" where "+Contracts.Product.TABLE_COLUMN_ID+"=?";
            MysqlAdapter.executeSql(sqlsearch,vpid);               
            if(rs.first()){                
                    vpid=rs.getString(1);
                    vpnam=rs.getString(2);
                    vmandt=rs.getLong(3);
                    vexpdt=rs.getLong(4);
                    vprice=rs.getDouble(5);
                    vwarrntperd=rs.getString(6);
                    vweight=rs.getFloat(7);
                    vmanfctcontry=rs.getString(8);
                    vbid=rs.getString(9);
                    vcid=rs.getString(10);
                    vcnt=rs.getInt(11);              
            }
            else{
                throw new ProductException(ProductException.Exceptions.PRODUCT_NOT_EXISTING);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }   
    
    public void searchProductName(){
        try {
            vrev=ServerMain.getRev(Contracts.Product.TABLE_NAME, Contracts.Product.TABLE_COLUMN_NAME, vpnam);
            String sqlsearch="select * from "+Contracts.Product.TABLE_NAME+" where "+Contracts.Product.TABLE_COLUMN_NAME+"=?";
            MysqlAdapter.executeSql(sqlsearch,vpnam);               
            if(rs.first()){                
                    vpid=rs.getString(1);
                    vpnam=rs.getString(2);
                    vmandt=rs.getLong(3);
                    vexpdt=rs.getLong(4);
                    vprice=rs.getDouble(5);
                    vwarrntperd=rs.getString(6);
                    vweight=rs.getFloat(7);
                    vmanfctcontry=rs.getString(8);
                    vbid=rs.getString(9);
                    vcid=rs.getString(10);
                    vcnt=rs.getInt(11);               
            }
            else{
                throw new ProductException(ProductException.Exceptions.PRODUCT_NOT_EXISTING);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    
    public void serachProductBrand(){
        try {
            vrev=ServerMain.getRev(Contracts.Product.TABLE_NAME, Contracts.Product.TABLE_COLUMN_BRAND_ID_F, vbid);
            String sqlsearch="select * from "+Contracts.Product.TABLE_NAME+" where "+Contracts.Product.TABLE_COLUMN_BRAND_ID_F+"=?";
            MysqlAdapter.executeSql(sqlsearch,vbid);               
            if(rs.first()){               
                    vpid=rs.getString(1);
                    vpnam=rs.getString(2);
                    vmandt=rs.getLong(3);
                    vexpdt=rs.getLong(4);
                    vprice=rs.getDouble(5);
                    vwarrntperd=rs.getString(6);
                    vweight=rs.getFloat(7);
                    vmanfctcontry=rs.getString(8);
                    vbid=rs.getString(9);
                    vcid=rs.getString(10);
                    vcnt=rs.getInt(11);                
            }
            else{
                throw new ProductException(ProductException.Exceptions.PRODUCT_NOT_EXISTING);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    
    public void searchProductCategry(){
        try {
            vrev=ServerMain.getRev(Contracts.Product.TABLE_NAME, Contracts.Product.TABLE_COLUMN_CATEGORY_ID_F, vcid);
            String sqlsearch="select * from "+Contracts.Product.TABLE_NAME+" where "+Contracts.Product.TABLE_COLUMN_CATEGORY_ID_F+"=?";
            MysqlAdapter.executeSql(sqlsearch,vcid);               
            if(rs.first()){                
                    vpid=rs.getString(1);
                    vpnam=rs.getString(2);
                    vmandt=rs.getLong(3);
                    vexpdt=rs.getLong(4);
                    vprice=rs.getDouble(5);
                    vwarrntperd=rs.getString(6);
                    vweight=rs.getFloat(7);
                    vmanfctcontry=rs.getString(8);
                    vbid=rs.getString(9);
                    vcid=rs.getString(10);
                    vcnt=rs.getInt(11);               
            }
            else{
                throw new ProductException(ProductException.Exceptions.PRODUCT_NOT_EXISTING);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    
    public void serachManuContry(){
        try {            
            String sqlsearch="select "+Contracts.Product.TABLE_COLUMN_MAN_COUNTRY+" from product where "+Contracts.Product.TABLE_COLUMN_CATEGORY_ID_F+"=? AND "+Contracts.Product.TABLE_COLUMN_BRAND_ID_F+"=?";
            MysqlAdapter.executeSql(sqlsearch,vcid);               
            if(rs.first()){                                   
                    vmanfctcontry=rs.getString(1);    
                    vprice=rs.getDouble(2);                
            }
            else{
                throw new ProductException(ProductException.Exceptions.PRODUCT_NOT_EXISTING);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    
    public void searchProNamePrice(){
        try {            
            String sqlsearch="select "+Contracts.Product.TABLE_COLUMN_NAME+","+Contracts.Product.TABLE_COLUMN_PRICE+" from "+Contracts.Product.TABLE_NAME+" where "+Contracts.Product.TABLE_COLUMN_CATEGORY_ID_F+"=? AND "+Contracts.Product.TABLE_COLUMN_PRICE+"=?";
            MysqlAdapter.executeSql(sqlsearch,vcid);               
            if(rs.first()){                                
                    vpnam=rs.getString(1);
                    vprice=rs.getDouble(2);                 
            }
            else{
                throw new ProductException(ProductException.Exceptions.PRODUCT_NOT_EXISTING);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    
    public void updateProduct(){        
        try {
            int chkupProdut=ServerMain.getRev(Contracts.Product.TABLE_NAME, Contracts.Product.TABLE_COLUMN_ID, vpid);
            if(vrev==chkupProdut){
                chkupProdut=chkupProdut+1;
                String sqlupdate="update "+Contracts.Product.TABLE_NAME+" set "+Contracts.Product.TABLE_COLUMN_ID+"=?,"+Contracts.Product.TABLE_COLUMN_NAME+"=?,"+Contracts.Product.TABLE_COLUMN_MAN_DATE+"=?,"+Contracts.Product.TABLE_COLUMN_EXP_DATE+"=?,"+Contracts.Product.TABLE_COLUMN_PRICE+"=?,"+Contracts.Product.TABLE_COLUMN_WARRANTY+"=?,"+Contracts.Product.TABLE_COLUMN_WEIGHT+"=?,"+Contracts.Product.TABLE_COLUMN_MAN_COUNTRY+"=?,"+Contracts.Product.TABLE_COLUMN_BRAND_ID_F+"=?,"+Contracts.Product.TABLE_COLUMN_CATEGORY_ID_F+"=?,"+Contracts.Product.TABLE_COLUMN_COUNT+"=? where "+Contracts.Product.TABLE_COLUMN_ID+"=?";
                MysqlAdapter.executeSql(sqlupdate, vpid,vpnam,vmandt,vexpdt,vprice,vwarrntperd,vweight,vmanfctcontry,vbid,vcid,vcnt);
                JOptionPane.showMessageDialog(null,"UPDATE SUCCESSFUL","PRODUCT",JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                throw new ProductException(ProductException.Exceptions.PRODUCT_UPDATED);
            }
            
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    
    public void deleteProduct(){        
        try {
            String checkProduct="select "+Contracts.Product.TABLE_COLUMN_ID+" from "+Contracts.Product.TABLE_NAME+" where "+Contracts.Product.TABLE_COLUMN_ID+"=?";
            boolean bl=MysqlAdapter.exists(checkProduct,vpid);
            if(bl){
                String sqldelt="delete from "+Contracts.Product.TABLE_NAME+" where "+Contracts.Product.TABLE_COLUMN_ID+"=?";////
                MysqlAdapter.executeSql(sqldelt,vpid);
                JOptionPane.showMessageDialog(null,"DELETE SUCCESSFUL","PRODUCT",JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                throw new ProductException(ProductException.Exceptions.PRODUCT_NOT_EXISTING);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    } 
}
