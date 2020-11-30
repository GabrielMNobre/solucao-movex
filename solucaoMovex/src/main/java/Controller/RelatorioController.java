/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.RelatorioDAO;
import Model.Relatorio;
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
    
}
