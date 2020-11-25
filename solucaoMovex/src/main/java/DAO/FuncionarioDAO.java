/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Funcionario;
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
public class FuncionarioDAO {
    
    public static Connection conexao = null;
    public static PreparedStatement st;
    public static ResultSet resultado = null;
    
    public static boolean insereInfo(Funcionario func) {
        int affectedRows = 0;
        
        if(Conector.conectaBanco()) {
            try {
                st = conexao.prepareStatement("INSERT INTO funcionario(nome, cargo, login, senha) "
                                            + "VALUES(?, ?, ?, ?)");
                st.setString(1, func.getNome());
                st.setString(2, func.getCargo());
                st.setString(3, func.getLogin());
                st.setString(4, func.getSenha());
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
    
    public static ArrayList<Funcionario> selectAll() {
        ArrayList<Funcionario> listaFuncionarios = new ArrayList<>();
        if(Conector.conectaBanco()) {
            try {
               st = conexao.prepareStatement("SELECT * FROM funcionario WHERE ativo <> false");
               resultado = st.executeQuery();
               
               while(resultado.next()) {
                   Funcionario func = new Funcionario();
                   
                   func.setId(resultado.getInt("codigo_funcionario"));
                   func.setNome(resultado.getString("nome"));
                   func.setCargo(resultado.getString("cargo"));
                   func.setLogin(resultado.getString("login"));
                   
                   listaFuncionarios.add(func);
               }
            } catch(SQLException e) {
               listaFuncionarios = null;
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
           listaFuncionarios = null;
        }
        
        return listaFuncionarios;
    } 
    
    public static ArrayList<Funcionario> selectByName(String name) {
        ArrayList<Funcionario> listaFuncionarios = new ArrayList<>();
        if(Conector.conectaBanco()) {
            try {
               st = conexao.prepareStatement("SELECT * FROM funcionario WHERE nome LIKE ? AND ativo <> false");
               st.setString(1, "%" + name + "%");
               resultado = st.executeQuery();
               
               while(resultado.next()) {
                   Funcionario func = new Funcionario();
                   
                   func.setId(resultado.getInt("codigo_funcionario"));
                   func.setNome(resultado.getString("nome"));
                   func.setCargo(resultado.getString("cargo"));
                   func.setLogin(resultado.getString("login"));
                   
                   listaFuncionarios.add(func);
               }
            } catch(SQLException e) {
               listaFuncionarios = null;
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
           listaFuncionarios = null;
        }
        
        return listaFuncionarios;
    } 
    
    
    public static Funcionario selectById(int id) {
        
        Funcionario retorno = new Funcionario();
        
        if(Conector.conectaBanco()) {
            try {
               st = conexao.prepareStatement("SELECT * FROM funcionario WHERE codigo_funcionario = ? AND ativo <> false");
               st.setInt(1, id);
               resultado = st.executeQuery();
               
               while(resultado.next()) {
                   Funcionario func = new Funcionario();
                   
                   func.setId(resultado.getInt("codigo_funcionario"));
                   func.setNome(resultado.getString("nome"));
                   func.setCargo(resultado.getString("cargo"));
                   func.setLogin(resultado.getString("login"));
                   retorno = func;
               }
               
            } catch(SQLException e) {
                System.out.println(e);
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
            System.out.println("Falha na conexão com o banco");
        }
        
        return retorno;
    }
    
    public static boolean update(Funcionario func, int id) {
        int affectedRows = 0;
        
        if(Conector.conectaBanco()) {
            try {
                st = conexao.prepareStatement("UPDATE funcionario SET nome = ?, "
                                                                   + "cargo = ?, "
                                                                   + "login = ?, "
                                                                   + "senha = ? "
                                            + "WHERE codigo_funcionario = ?");
                st.setString(1, func.getNome());
                st.setString(2, func.getCargo());
                st.setString(3, func.getLogin());
                st.setString(4, func.getSenha());
                st.setInt(5, id);
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
    
    public static boolean delete(int id) {
        int affectedRows = 0;
        
        if(Conector.conectaBanco()) {
            try {
                st = conexao.prepareStatement("UPDATE funcionario SET ativo = false WHERE codigo_funcionario = ? ");
                st.setInt(1, id);
                
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
