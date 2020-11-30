/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Relatorio;
import Utils.Conector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Ayrto
 *
 */
public class RelatorioDAO {
    
    public static PreparedStatement st;
    public static ResultSet resultado = null;
    
    public static ArrayList<Relatorio> selectPeriodo(Date inicio, Date fim) {
        Connection conexao = Conector.conectaBanco();
        ArrayList<Relatorio> listaVendas = new ArrayList<>();
        
        if(conexao != null) {
            try {
                st = conexao.prepareStatement("SELECT * FROM pedido_mestre WHERE data_criacao BETWEEN ? AND ? ");
                st.setDate(1, new java.sql.Date(inicio.getTime()));
                st.setDate(2, new java.sql.Date(fim.getTime()));
                System.out.println(st);
                resultado = st.executeQuery();

                while(resultado.next()) {
                    Relatorio r = new Relatorio();
                    r.setNumeroPedido(resultado.getInt("numero_pedido"));
                    r.setCodVendedor(resultado.getInt("codigo_vendedor"));
                    r.setCodCliente(resultado.getInt("codigo_cliente"));
                    r.setValorTotal(resultado.getDouble("total"));
                    r.setStatus(resultado.getString("status"));
                    
                    listaVendas.add(r);
                }
            } catch(SQLException e) {
               listaVendas = null;
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
           listaVendas = null;
        }
        
        return listaVendas;
    } 
    
}
