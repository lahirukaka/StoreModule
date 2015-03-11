/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.vcnet.storemodule.client.user_interfaces;

import lk.vcnet.storemodule.client.*;
import lk.vcnet.storemodule.server.Supplier;

/**
 *
 * @author user
 */
public class CLt {
    public static void main(String[] args) 
    {
        try
        {
            Supplier sup=new Supplier();
            sup.setSid("S4");
            sup.setSname("Sunil");
            sup.setSadr("DEWW Rd");
            sup.setCity("Colombo");
            sup.setTele("0412235599");
            
            sup.registerSupplier();
            
            //sup.updateSupplier();
            
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
}
