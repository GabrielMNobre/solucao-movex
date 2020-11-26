/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.ClienteDAO;
import Model.Cliente;
import java.util.ArrayList;

/**
 *
 * @author gabriel.nobre
 */
public class ClienteController {
    
    public static boolean insereInfo(String dados[]) {
        
        Cliente cli = new Cliente(dados);
        
        return ClienteDAO.insereInfo(cli);
    }
    
    public static ArrayList<String[]> selectAll() {
        
        ArrayList<Cliente> listaClientes = ClienteDAO.selectAll();
        ArrayList<String[]> retorno = new ArrayList<>();
        
        for(Cliente cli : listaClientes) {
            retorno.add(new String[] {
                cli.getNome(),
                cli.getCpf(),
                cli.getEmail(),
                cli.getTelefone(),
                cli.getLogradouro(),
                cli.getStatus() == 1 ? "Ativo" : "Inativo"
            });
        }
        
        return retorno;
    }
    
    public static String[] selectByUnique(String cpf) {
        
        Cliente cli = ClienteDAO.selectByUnique(cpf);
        String cliRetorno[] = null;
        
        if(cli != null) {
            cliRetorno = new String[] {
                cli.getNome(),
                cli.getCpf(),
                cli.getEmail(),
                cli.getTelefone(),
                cli.getLogradouro(),
                cli.getStatus() == 1 ? "Ativo" : "Inativo"
            };
        }
        
        return cliRetorno;
    }
    
    public static ArrayList<String[]> selectByName(String nome) {
        
        ArrayList<Cliente> listaClientes = ClienteDAO.selectByName(nome);
        ArrayList<String[]> retorno = new ArrayList<>();
        
        for(Cliente cli : listaClientes) {
            retorno.add(new String[] {
                cli.getNome(),
                cli.getCpf(),
                cli.getEmail(),
                cli.getTelefone(),
                cli.getLogradouro(),
                cli.getStatus() == 1 ? "Ativo" : "Inativo"
            });
            System.out.println(cli);
        }
        
        return retorno;
    }
    
    public static ArrayList<String[]> selectByStatus(int status) {
        
        ArrayList<Cliente> listaClientes = ClienteDAO.selectByStatus(status);
        ArrayList<String[]> retorno = new ArrayList<>();
        
        for(Cliente cli : listaClientes) {
            retorno.add(new String[] {
                cli.getNome(),
                cli.getCpf(),
                cli.getEmail(),
                cli.getTelefone(),
                cli.getLogradouro(),
                cli.getStatus() == 1 ? "Ativo" : "Inativo"
            });
        }
        
        return retorno;
    }
    
    public static String[] select(String cpf) {
        
        Cliente cli = ClienteDAO.select(cpf);
        String cliRetorno[] = null;
        
        if(cli != null) {
            cliRetorno = new String[] {
                cli.getNome(),
                cli.getEmail(),
                cli.getCpf(),
                cli.getSexo(),
                cli.getTelefone(),
                cli.getCelular(),
                cli.getCidade(),
                cli.getCep(),
                cli.getLogradouro(),
                String.valueOf(cli.getId())
            };
        }
        
        return cliRetorno;
    }
    
    public static boolean update(String dados[], int id) {
        
        Cliente cli = new Cliente(dados);
        
        return ClienteDAO.update(cli, id);
    }
    
    public static boolean delete(String cpf) {
        
        return ClienteDAO.delete(cpf);
        
    }
}
