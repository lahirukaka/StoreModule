/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.vcnet.storemodule.controller;

import java.rmi.Remote;

/**
 *
 * @author user
 */
public interface CategoryControllerInterface extends Remote{
    
    public void addCategory() throws Exception;
}
