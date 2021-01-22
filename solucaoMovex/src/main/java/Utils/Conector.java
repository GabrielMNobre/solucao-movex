/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author gabriel.nobre
 */
public class Conector {
    
    public static Connection conexao = null;
    
    public static Connection conectaBanco() {
        
        String URL = "jdbc:mysql://localhost:3306/solucao_movex?useTimezone=true&serverTimezone=UTC&useSSL=false";
        String LOGIN = "root";//seu usuário do banco
        String SENHA = "root";//sua senha do banco
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection(URL, LOGIN, SENHA);
        } catch(ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Driver não encontrado");
            conexao = null;
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            conexao = null;
        }
        return conexao;
    }
    
}
