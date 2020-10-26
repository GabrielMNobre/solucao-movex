/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author gabriel
 */
public class Validador {
    
    public Validador() {}
    
    public static boolean isNull(JTextField txt, JLabel label) {
        if(txt.getText().replace(".", "").replace("-", "").replace("/", "").replace("(", "").replace(")", "").trim().equals("")) {
            txt.setBackground(Color.YELLOW);
            label.setText("Os campos que estão em amarelo tem preenchimento obrigatório!");
            return true;
        } else {
            txt.setBackground(Color.WHITE);
            return false;
        }
    }
    
    public String ValidarNumero(JTextField txt, JLabel label){
        String retorno = "";
    
        try{
            int valorConvertido = Integer.parseInt(txt.getText());
            txt.setBackground(Color.WHITE);
            retorno = "OK";
            label.setText("");
            
        }catch(NumberFormatException e){
        
            label.setText("Falha ao converter o valor para inteiro!");
            txt.setBackground(Color.RED);
            retorno = "ERR";
        }catch(IllegalArgumentException e){
            label.setText("Digite apenas números ao campo número!");
            txt.setBackground(Color.RED);
            retorno = "ERR";
        }
        
        return retorno;
    }
    
}
