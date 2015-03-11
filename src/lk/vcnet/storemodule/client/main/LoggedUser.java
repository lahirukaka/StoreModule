/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lk.vcnet.storemodule.client.main;

import me.colhh.usermanager.rmi.first.Holder.User;

/**
 *
 * @author Lahiru Udana <lahirukaka@gmail.com>
 */
public class LoggedUser extends User{
 
    private static LoggedUser instance;
    
    private LoggedUser()
    {
        setLogged(false);
    }
    
    private LoggedUser(User user)
    {
        setUid(user.getUID());
        setUname(user.getUname());
        setIsWritable(user.Iswritable());
        setLastLogin(user.getLastLogin());
        setLogged(user.isLogged());
        setPassword(user.getPasswordHash());
    }
    
    public static synchronized LoggedUser getInstance(User user)
    {
        if(instance == null) instance = new LoggedUser(user);
        return instance;
    }
    
    public static synchronized LoggedUser getInstance()
    {
        if(instance == null) instance = new LoggedUser();
        return instance;
    }
    
    public void logOff()
    {
        setLogged(false);
    }
}
