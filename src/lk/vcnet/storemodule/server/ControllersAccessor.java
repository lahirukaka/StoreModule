/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lk.vcnet.storemodule.server;

import java.rmi.server.UnicastRemoteObject;
import lk.vcnet.storemodule.controller.ControllerAccessorInterface;
import me.colhh.usermanager.rmi.first.Adapter.DBAdapter;
import me.colhh.usermanager.rmi.first.controller.UserControllerInterface;

/**
 *
 * @author Lahiru Udana <lahirukaka@gmail.com>
 */
public class ControllersAccessor extends UnicastRemoteObject 
        implements ControllerAccessorInterface
{

    public ControllersAccessor() throws Exception {}

    @Override
    public UserControllerInterface getDBAdapter() throws Exception {
        return new DBAdapter();
    }
}
