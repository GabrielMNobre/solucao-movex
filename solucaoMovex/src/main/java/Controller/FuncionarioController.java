/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.FuncionarioDAO;
import Model.Funcionario;
import java.util.ArrayList;

/**
 *
 * @author gabriel.nobre
 */
public class FuncionarioController {
    
    public static boolean insereInfo(String dados[]) {
        
        Funcionario func = new Funcionario(dados);
        
        return FuncionarioDAO.insereInfo(func);
    }
    
    public static ArrayList<String[]> selectAll() {
        
        ArrayList<Funcionario> listaFunc = FuncionarioDAO.selectAll();
        ArrayList<String[]> retorno = new ArrayList<>();
        
        for (Funcionario func : listaFunc) {
            retorno.add(new String[]{
                String.valueOf(func.getId()),
                func.getNome(),
                func.getCargo(),
                func.getLogin()
            });
        }
        
        return retorno;
        
    }
    
    public static ArrayList<String[]> selectByName(String name) {
        
        ArrayList<Funcionario> listaFunc = FuncionarioDAO.selectByName(name);
        ArrayList<String[]> retorno = new ArrayList<>();
        
        for (Funcionario func : listaFunc) {
            retorno.add(new String[]{
                String.valueOf(func.getId()),
                func.getNome(),
                func.getCargo(),
                func.getLogin()
            });
        }
        
        return retorno;
        
    }
    
    public static String[] selectById(int id) {
        
        Funcionario func = FuncionarioDAO.selectById(id);
        String[] funcRetorno = null;
        
        if(func != null) {
            funcRetorno = new String[] {
                String.valueOf(func.getId()),
                func.getNome(),
                func.getCargo(),
                func.getLogin()
            };
        }
        return funcRetorno;
    }
    
    public static boolean update(String dados[], int id) {
        
        Funcionario func = new Funcionario(dados);
        
        return FuncionarioDAO.update(func, id);
        
    }
    
    public static boolean delete(int id) {
        
        return FuncionarioDAO.delete(id);
        
    }
    
}
