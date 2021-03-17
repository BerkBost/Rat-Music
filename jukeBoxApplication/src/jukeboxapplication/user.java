/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jukeboxapplication;

import java.util.ArrayList;

/**
 *
 * @author berkb
 */
public class user {
    private String userName ; 
    private String pass ;
    
            

    public user(String userName, String pass) {
        this.userName = userName;
        this.pass = pass;
       
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
