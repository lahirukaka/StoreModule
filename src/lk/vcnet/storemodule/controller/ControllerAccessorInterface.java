/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.vcnet.storemodule.controller;

import java.rmi.Remote;
import me.colhh.usermanager.rmi.first.controller.UserControllerInterface;

/**
 *
 * @author Lahiru Udana <lahirukaka@gmail.com>
 */
public interface ControllerAccessorInterface extends Remote {
    
    public UserControllerInterface getDBAdapter() throws Exception;
}
