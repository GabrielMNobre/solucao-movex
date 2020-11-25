/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import static DAO.FuncionarioDAO.conexao;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author gabriel.nobre
 */
public class Conector {
    
    public static boolean conectaBanco() {
        
        String URL = "jdbc:mysql://localhost:3306/solucao_movex?useTimezone=true&serverTimezone=UTC&useSSL=false";
        String LOGIN = "gabriel";
        String SENHA = "24102002";
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection(URL, LOGIN, SENHA);
            return true;
        } catch(ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Driver não encontrado");
            return false;
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }
    
}
