/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.RelatorioDAO;
import Model.Relatorio;
import Model.RelatorioEspecifico;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author gabriel.nobre
 */
public class RelatorioController {
    
    public static ArrayList<String[]> selectPeriodo(Date inicio, Date fim) {
        
        ArrayList<Relatorio> listaVendas = RelatorioDAO.selectPeriodo(inicio, fim);
        ArrayList<String[]> retorno = new ArrayList<>();
        
        for (Relatorio rel : listaVendas) {
            retorno.add(new String[]{
                String.valueOf(rel.getNumeroPedido()),
                String.valueOf(rel.getCodVendedor()),
                String.valueOf(rel.getCodCliente()),
                String.valueOf(rel.getValorTotal()),
                rel.getStatus()
            });
        }
        
        return retorno;
        
    }
    
    public static ArrayList<String[]> selectPedido(int id) {
        
        ArrayList<RelatorioEspecifico> listaProdutos = RelatorioDAO.selectPedido(id);
        ArrayList<String[]> retorno = new ArrayList<>();
        
        for (RelatorioEspecifico rel : listaProdutos) {
            retorno.add(new String[]{
                String.valueOf(rel.getIdPedido()),
                String.valueOf(rel.getCodProduto()),
                rel.getNomeProduto(),
                String.valueOf(rel.getQtdProduto()),
                String.valueOf(rel.getPrecoUnitario()),
                String.valueOf(rel.getSubtotal()),
            });
        }
        
        return retorno;
        
    }
    
}
