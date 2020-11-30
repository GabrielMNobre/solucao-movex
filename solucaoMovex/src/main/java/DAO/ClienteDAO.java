/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Cliente;
import Utils.Conector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author gabriel.nobre
 */
public class ClienteDAO {
    
    public static PreparedStatement st;
    public static ResultSet resultado = null;
    
    public static boolean insereInfo(Cliente cli) {
        Connection conexao = Conector.conectaBanco();
        int affectedRows = 0;
        
        if(conexao != null) {
            try {
                st = conexao.prepareStatement(
                   "INSERT INTO cliente(nome, cpf, telefone, celular, endereco, cep, cidade, email, sexo) "
                   + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)");
                st.setString(1, cli.getNome());
                st.setString(2, cli.getCpf());
                st.setString(3, cli.getTelefone());
                st.setString(4, cli.getCelular());
                st.setString(5, cli.getLogradouro());
                st.setString(6, cli.getCep());
                st.setString(7, cli.getCidade());
                st.setString(8, cli.getEmail());
                st.setString(9, cli.getSexo().equals("Masculino") ? "M" : "F");
                affectedRows = st.executeUpdate();
                return affectedRows > 0;
            } catch(SQLException e) {
                System.out.println(e);
                return false;
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
            return false;
        }
    }
    
    public static ArrayList<Cliente> selectAll() {
        Connection conexao = Conector.conectaBanco();
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        
        if(conexao != null) {
            try {
               st = conexao.prepareStatement("SELECT * FROM cliente WHERE ativo <> false");
               resultado = st.executeQuery();
               
               while(resultado.next()) {
                   Cliente cli = new Cliente();
                   
                   cli.setNome(resultado.getString("nome"));
                   cli.setCpf(resultado.getString("cpf"));
                   cli.setEmail(resultado.getString("email"));
                   cli.setTelefone(resultado.getString("telefone"));
                   cli.setLogradouro(resultado.getString("endereco"));
                   cli.setStatus(resultado.getInt("ativo"));
                   
                   listaClientes.add(cli);
               }
            } catch(SQLException e) {
               listaClientes = null;
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
           listaClientes = null;
        }
        
        return listaClientes;
    }
    
    public static Cliente selectByUnique(String cpf) {
        Connection conexao = Conector.conectaBanco();
        Cliente retorno = new Cliente();
        
        if(conexao != null) {
            try {
               st = conexao.prepareStatement("SELECT * FROM cliente WHERE cpf = ? AND ativo <> false ");
               st.setString(1, cpf);
               resultado = st.executeQuery();
               
               while(resultado.next()) {
                   Cliente cli = new Cliente();
                   
                   cli.setNome(resultado.getString("nome"));
                   cli.setCpf(resultado.getString("cpf"));
                   cli.setEmail(resultado.getString("email"));
                   cli.setTelefone(resultado.getString("telefone"));
                   cli.setLogradouro(resultado.getString("endereco"));
                   cli.setStatus(resultado.getInt("ativo"));
                   retorno = cli;
               }
            } catch(SQLException e) {
               retorno = null;
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
           retorno = null;
        }
        
        return retorno;
    }
    
    public static Cliente select(String cpf) {
        Connection conexao = Conector.conectaBanco();
        Cliente retorno = new Cliente();
        
        if(conexao != null) {
            try {
               st = conexao.prepareStatement("SELECT * FROM cliente WHERE cpf = ? AND ativo <> false");
               st.setString(1, cpf);
               resultado = st.executeQuery();
               
               while(resultado.next()) {
                   Cliente cli = new Cliente();
                   
                   cli.setNome(resultado.getString("nome"));
                   cli.setEmail(resultado.getString("email"));
                   cli.setCpf(resultado.getString("cpf"));
                   cli.setSexo(resultado.getString("sexo"));
                   cli.setTelefone(resultado.getString("telefone"));
                   cli.setCelular(resultado.getString("celular"));
                   cli.setCidade(resultado.getString("cidade"));
                   cli.setCep(resultado.getString("cep"));
                   cli.setLogradouro(resultado.getString("endereco"));
                   cli.setId(resultado.getInt("codigo_cliente"));
                   retorno = cli;
               }
            } catch(SQLException e) {
               retorno = null;
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
           retorno = null;
        }
        
        return retorno;
    }
    
    public static ArrayList<Cliente> selectByName(String nome) {
        Connection conexao = Conector.conectaBanco();
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        
        if(conexao != null) {
            try {
               st = conexao.prepareStatement("SELECT * FROM cliente WHERE nome LIKE ? ");
               st.setString(1, "%" + nome + "%");
               
               resultado = st.executeQuery();
          
               while(resultado.next()) {
                   Cliente cli = new Cliente();
                   cli.setNome(resultado.getString("nome"));
                   cli.setCpf(resultado.getString("cpf"));
                   cli.setEmail(resultado.getString("email"));
                   cli.setTelefone(resultado.getString("telefone"));
                   cli.setLogradouro(resultado.getString("endereco"));
                   cli.setStatus(resultado.getInt("ativo"));
                   cli.setId(Integer.parseInt(resultado.getString("codigo_cliente")));                   
                   listaClientes.add(cli);
               }
            } catch(SQLException e) {
               listaClientes = null;
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
           listaClientes = null;
        }
        
        return listaClientes;
    }
    
    public static boolean update(Cliente cli, int id) {
        Connection conexao = Conector.conectaBanco();
        int affectedRows = 0;
        
        if(conexao != null) {
            try {
                st = conexao.prepareStatement(
                   "UPDATE cliente SET nome = ? , "
                                    + "cpf = ? , "
                                    + "telefone = ? , "
                                    + "celular = ? , "
                                    + "endereco = ? , "
                                    + "cep = ? , "
                                    + "cidade = ? , "
                                    + "email = ? , "
                                    + "sexo = ? "
                    + "WHERE codigo_cliente = ? "
                );
                st.setString(1, cli.getNome());
                st.setString(2, cli.getCpf());
                st.setString(3, cli.getTelefone());
                st.setString(4, cli.getCelular());
                st.setString(5, cli.getLogradouro());
                st.setString(6, cli.getCep());
                st.setString(7, cli.getCidade());
                st.setString(8, cli.getEmail());
                st.setString(9, cli.getSexo().equals("Masculino") ? "M" : "F");
                st.setInt(10, id);
                System.out.println(st);
                affectedRows = st.executeUpdate();
                return affectedRows > 0;
            } catch(SQLException e) {
                System.out.println(e);
                return false;
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
            return false;
        }
    }
    
    public static boolean delete(String cpf) {
        Connection conexao = Conector.conectaBanco();
        int affectedRows = 0;
        
        if(conexao != null) {
            try {
                st = conexao.prepareStatement("UPDATE cliente SET ativo = false WHERE cpf = ? ");
                st.setString(1, cpf);
                
                affectedRows = st.executeUpdate();
                return affectedRows > 0;
            } catch(SQLException e) {
                return false;
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
            return false;
        }
    
    }
    
}
