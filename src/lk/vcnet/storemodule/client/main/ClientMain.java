/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.vcnet.storemodule.client.main;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.rmi.Naming;
import lk.vcnet.storemodule.client.user_interfaces.Welcome;
import lk.vcnet.storemodule.controller.ControllerAccessorInterface;
import lk.vcnet.storemodule.server.ServerMain;
import me.colhh.usermanager.rmi.first.controller.UserControllerInterface;

/**
 *
 * @author user
 */
public class ClientMain {
    
    private static ControllerAccessorInterface accessor;
    private static UserControllerInterface user_stub;
    
    public static Dimension getDisplaySize()
    {
        return Toolkit.getDefaultToolkit().getScreenSize();
    }
    
    public static void main(String[] args) {
        try{
            findAccessor();
        }catch(Exception e){System.out.println(e.getMessage());}
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Welcome.getInstance().setVisible(true);
            }
        });
    }
    
    public static synchronized ControllerAccessorInterface findAccessor() 
            throws Exception
    {
        if(accessor == null) {
            accessor = (ControllerAccessorInterface) 
                Naming.lookup("rmi://localhost:9293/" + ServerMain.ACCESSOR_OBJECT_NAME);
            user_stub = (UserControllerInterface) accessor.getDBAdapter();
        }
        return accessor;
    }
    
    public static UserControllerInterface getUserStub() throws Exception
    {
        return user_stub;
    }
}
