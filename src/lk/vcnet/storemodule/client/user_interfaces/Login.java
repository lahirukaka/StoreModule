/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.vcnet.storemodule.client.user_interfaces;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import lk.vcnet.storemodule.client.main.ClientMain;
import lk.vcnet.storemodule.client.main.LoggedUser;
import me.colhh.usermanager.rmi.first.Holder.User;
import me.colhh.usermanager.rmi.first.Holder.UserException;

/**
 *
 * @author user
 */
public class Login extends javax.swing.JInternalFrame {

    /**
     * Creates new form NewJInternalFrame
     */
    
    private static Login instance;
    
    private Login() {
        initComponents();
        setVisible(true);
        Dimension dm=this.getSize();
        this.setLocation((ClientMain.getDisplaySize().width - dm.width)/2, 
                (ClientMain.getDisplaySize().height - 80 - dm.height)/2);
    }

    public static synchronized Login getInstance()
    {
        if(instance==null) instance=new Login();
        return instance;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btLogin = new javax.swing.JButton();
        tunam = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tpas = new javax.swing.JPasswordField();
        btExit = new javax.swing.JButton();

        setResizable(true);
        setTitle("USER LOGIN");
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
        });

        btLogin.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        btLogin.setText("LOGIN");
        btLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLoginActionPerformed(evt);
            }
        });

        tunam.setFont(new java.awt.Font("Courier New", 0, 16)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Courier New", 1, 16)); // NOI18N
        jLabel1.setText("Username");

        jLabel3.setFont(new java.awt.Font("Courier New", 1, 16)); // NOI18N
        jLabel3.setText("Password");

        tpas.setFont(new java.awt.Font("Courier New", 0, 16)); // NOI18N

        btExit.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        btExit.setText("EXIT");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(52, 52, 52)
                        .addComponent(tunam, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(54, 54, 54)
                        .addComponent(tpas, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btExit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(0, 39, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(tunam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tpas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btLogin)
                    .addComponent(btExit))
                .addGap(31, 31, 31))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(79, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLoginActionPerformed
        try {
            tunam.setBackground(Color.WHITE);
            tpas.setBackground(Color.WHITE);
            
            if(!tunam.getText().isEmpty())
            {
                if(tpas.getPassword().length > 0)
                {
                    logInUser();
                }
                else
                    tpas.setBackground(Color.red);
            }
            else
                tunam.setBackground(Color.red);
        } catch (Exception e) {
            tunam.setText("");
            tpas.setText("");
            JOptionPane.showMessageDialog(null, UserException.UserExceptionCodes.USER_NOT_EXISTING.getMessage());
        }
    }//GEN-LAST:event_btLoginActionPerformed

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained
        
    }//GEN-LAST:event_formFocusGained

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened

    }//GEN-LAST:event_formInternalFrameOpened


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btExit;
    private javax.swing.JButton btLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField tpas;
    private javax.swing.JTextField tunam;
    // End of variables declaration//GEN-END:variables

    private void logInUser() throws Exception
    {
        String pass=String.valueOf(tpas.getPassword()); /*getting the password*/
        String qry="select * from "+me.colhh.usermanager.rmi.first.Adapter.Contracts.TABLE_NAME + 
                " where " + me.colhh.usermanager.rmi.first.Adapter.Contracts.FIELD_NAME_USERNAME + "=?";
        ArrayList<User> us= ClientMain.getUserStub().queryUser(qry, tunam.getText());
        /*if user exists*/
        String pswhash = us.get(0).getPasswordHash();
        us.get(0).setPassword("");
        us.get(0).setPasswordHash(pass);

        if(pswhash.equals(us.get(0).getPasswordHash()))
        {
            us.get(0).setLogged(true);
            us.get(0).setLastLoginNow();
            ClientMain.getUserStub().updateUserInfo(us.get(0));
            LoggedUser.getInstance(us.get(0));
            Welcome.getInstance().setUserMenu(us.get(0).getUname());
            this.dispose();
        }else
        {
            JOptionPane.showMessageDialog(null, "Wrong Password");
            tunam.setText("");
            tpas.setText("");
        }
    }

}
