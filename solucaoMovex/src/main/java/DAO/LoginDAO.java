/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Utils.Conector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author gabriel.nobre
 */
public class LoginDAO {
    
    public static PreparedStatement st;
    public static ResultSet resultado = null;
    
    public static int verificaQtd() {
        Connection conexao = Conector.conectaBanco();
        int retorno = 0;
        
        if(conexao != null) {
            try {
               st = conexao.prepareStatement("SELECT COUNT(codigo_funcionario) qtd FROM funcionario WHERE ativo <> false");
               resultado = st.executeQuery();
               
               while(resultado.next()) {
                   retorno++;
               }
            } catch(SQLException e) {
               retorno = 0;
            } finally {
                if(st != null) {
                    try {
                        st.close();
                    } catch(SQLException e) {
                        System.out.println(e);
                    }
                }
                
                if(conexao != null) {
                    try {
                        conexao.close();
                    } catch(SQLException e) {
                        System.out.println(e);
                    }
                }
            }
        } else {
           retorno = 0;
        }
        
        return retorno;
    }
    
    public static int verificaLogin(String login, String senha) {
        Connection conexao = Conector.conectaBanco();
        int retorno = 0;
        
        if(conexao != null) {
            try {
               st = conexao.prepareStatement("SELECT * FROM funcionario WHERE login = ? AND senha = ? AND ativo <> false");
               st.setString(1, login);
               st.setString(2, senha);
               resultado = st.executeQuery();
               
               while(resultado.next()) {
                   retorno++;
               }
            } catch(SQLException e) {
               retorno = 0;
            } finally {
                if(st != null) {
                    try {
                        st.close();
                    } catch(SQLException e) {
                        System.out.println(e);
                    }
                }
                
                if(conexao != null) {
                    try {
                        conexao.close();
                    } catch(SQLException e) {
                        System.out.println(e);
                    }
                }
            }
        } else {
           retorno = 0;
        }
        
        return retorno;
    }
    
}
