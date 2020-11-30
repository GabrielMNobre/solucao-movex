/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.LoginDAO;

/**
 *
 * @author gabriel.nobre
 */
public class LoginController {
    
    public static int verificaQtd() {
        
        return LoginDAO.verificaQtd();
        
    }
    
    public static int verificaLogin(String login, String senha) {
        
        return LoginDAO.verificaLogin(login, senha);
        
    }
    
}
