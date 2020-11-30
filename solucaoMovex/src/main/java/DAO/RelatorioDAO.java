/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Relatorio;
import Model.RelatorioEspecifico;
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
    
    public static ArrayList<RelatorioEspecifico> selectPedido(int id) {
        Connection conexao = Conector.conectaBanco();
        ArrayList<RelatorioEspecifico> listaProduto = new ArrayList<>();
        
        if(conexao != null) {
            try {
                st = conexao.prepareStatement("select d.codigo_pedido, d.codigo_produto, p.nome, d.quantidade, d.preco, d.total " +
                                              "from pedido_detalhe d " +
                                              "inner join produto p on p.codigo_produto = d.codigo_produto " +
                                              "where codigo_pedido = ?;");
                st.setInt(1, id);
                System.out.println(st);
                resultado = st.executeQuery();

                while(resultado.next()) {
                    RelatorioEspecifico r = new RelatorioEspecifico();
                    r.setIdPedido(resultado.getInt("codigo_pedido"));
                    r.setCodProduto(resultado.getInt("codigo_produto"));
                    r.setNomeProduto(resultado.getString("nome"));
                    r.setQtdProduto(resultado.getInt("quantidade"));
                    r.setPrecoUnitario(resultado.getDouble("preco"));
                    r.setSubtotal(resultado.getDouble("total"));
                    
                    listaProduto.add(r);
                }
            } catch(SQLException e) {
               listaProduto = null;
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
           listaProduto = null;
        }
        
        return listaProduto;
    }
    
}
