/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lk.vcnet.storemodule.db;

/**
 *
 * @author Lahiru Udana <lahirukaka@gmail.com>
 */
public class Contracts {

    public static final String DB_NAME = "storemodule";
    public static final int DB_VERSION = 1;
    
    public static class Brand
    {
        public static final String TABLE_NAME = "brand";
        public static final String TABLE_COLUMN_BID = "BID";
        public static final String TABLE_COLUMN_BNAME = "BName";
        public static final String TABLE_COLUMN_COUNT = "Cnt";
        public static final String TABLE_COLUMN_REV = "Rev";
    }
    
    public static class Category
    {
        public static final String TABLE_NAME = "category";
        public static final String TABLE_COLUMN_ID = "CGID";
        public static final String TABLE_COLUMN_NAME = "CGName";
        public static final String TABLE_COLUMN_COUNT = "Cnt";
        public static final String TABLE_COLUMN_REV = "Rev";
    }
    
    public static class Inventory
    {
        public static final String TABLE_NAME = "inventory";
        public static final String TABLE_COLUMN_ID = "IVID";
        public static final String TABLE_COLUMN_QTY = "Qty";
        public static final String TABLE_COLUMN_PID_F = Product.TABLE_COLUMN_ID;
        public static final String TABLE_COLUMN_COUNT = "Cnt";
        public static final String TABLE_COLUMN_REV = "Rev";
    }
    
    public static class Product
    {
        public static final String TABLE_NAME = "product";
        public static final String TABLE_COLUMN_ID = "PID";
        public static final String TABLE_COLUMN_NAME = "PName";
        public static final String TABLE_COLUMN_MAN_DATE = "ManDate";
        public static final String TABLE_COLUMN_EXP_DATE = "ExpDate";
        public static final String TABLE_COLUMN_PRICE = "Price";
        public static final String TABLE_COLUMN_WARRANTY = "WarrantyPeriod";
        public static final String TABLE_COLUMN_WEIGHT = "Weight";
        public static final String TABLE_COLUMN_MAN_COUNTRY = "ManufCntry";
        public static final String TABLE_COLUMN_BRAND_ID_F = Brand.TABLE_COLUMN_BID;
        public static final String TABLE_COLUMN_CATEGORY_ID_F = Category.TABLE_COLUMN_ID;
        public static final String TABLE_COLUMN_COUNT = "Cnt";
        public static final String TABLE_COLUMN_REV = "Rev";
        
        //public static final String QUERY_FROM_ID = "SELECT * FROM " + TABLE_NAME + " WHERE " + TABLE_COLUMN_ID + "=?";
    }
    
    public static class Order
    {
        public static final String TABLE_NAME = "order";
        public static final String TABLE_COLUMN_ID = "OID";
        public static final String TABLE_COLUMN_DATE = "Date";
        public static final String TABLE_COLUMN_CHECK = "Check";
        public static final String TABLE_COLUMN_SUPPLIER_ID_F = Supplier.TABLE_COLUMN_ID;
        public static final String TABLE_COLUMN_COUNT = "Cnt";
        public static final String TABLE_COLUMN_REV = "Rev";
    }
    
    public static class Supplier
    {
        public static final String TABLE_NAME = "supplier";
        public static final String TABLE_COLUMN_ID = "SID";
        public static final String TABLE_COLUMN_NAME = "SName";
        public static final String TABLE_COLUMN_ADDRESS = "SAddress";
        public static final String TABLE_COLUMN_EXP_TELEPHONE = "Tele";
        public static final String TABLE_COLUMN_CITY= "City";
        public static final String TABLE_COLUMN_COUNT = "Cnt";
        public static final String TABLE_COLUMN_REV = "Rev";
        
        public static final String QUERY_FROM_ID = "SELECT * FROM " + TABLE_NAME + " WHERE " + TABLE_COLUMN_ID + "=?";
    }
    
    public static class OrderProduct
    {
        public static final String TABLE_NAME = "orderproduct";
        public static final String TABLE_COLUMN_ORDER_ID_F = Order.TABLE_COLUMN_ID;
        public static final String TABLE_COLUMN_PRODUCT_ID_F = Product.TABLE_COLUMN_ID;
        public static final String TABLE_COLUMN_Qty = "Qty";
        public static final String TABLE_COLUMN_COUNT = "Cnt";
        public static final String TABLE_COLUMN_REV = "Rev";
    }
    
    public static class PO
    {
        public static final String TABLE_NAME = "po";
        public static final String TABLE_COLUMN_ID = "ID";
        public static final String TABLE_COLUMN_TRANSACTION_ID = Transaction.TABLE_COLUMN_ID;
        public static final String TABLE_COLUMN_COUNT = "Cnt";
        public static final String TABLE_COLUMN_REV = "Rev";
    }
    
    public static class Transaction
    {
        public static final String TABLE_NAME = "transaction";
        public static final String TABLE_COLUMN_ID = "TRSID";
        public static final String TABLE_COLUMN_DATE = "Date";
        public static final String TABLE_COLUMN_AMOUNT = "Amount";
        public static final String TABLE_COLUMN_BALANCE = "Balance";
        public static final String TABLE_COLUMN_ORDER_ID_F = Order.TABLE_COLUMN_ID;
        public static final String TABLE_COLUMN_COUNT = "Cnt";
        public static final String TABLE_COLUMN_REV = "Rev";
    }
    
    public static class Payment
    {
        public static final String TABLE_NAME = "payment";
        public static final String TABLE_COLUMN_ID = "PAYID";
        public static final String TABLE_COLUMN_DATE = "Date";
        public static final String TABLE_COLUMN_AMOUNT = "PaymentAmount";
        public static final String TABLE_COLUMN_TRANSACTION_ID = Transaction.TABLE_COLUMN_ID;
        public static final String TABLE_COLUMN_COUNT = "Cnt";
        public static final String TABLE_COLUMN_REV = "Rev";
    }
}