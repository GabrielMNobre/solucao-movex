/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Produto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Ayrto
 */
public class ProdutoDAO {
    
        static String URL = "jdbc:mysql://localhost:3306/solucao_movex?useTimezone=true&serverTimezone=UTC&useSSL=false";
        static String LOGIN = "root";
        static String SENHA = "wte7841";
        
        public static boolean salvar(Produto obj) {
        
            boolean retorno = false;
            Connection conexao = null;
            PreparedStatement instrucaoSQL = null;

            try {

               Class.forName("com.mysql.cj.jdbc.Driver");
               //Obs: A classe GerenciadorConexao já carrega o Driver e define os parâmetros de conexão
                conexao = DriverManager.getConnection(URL, LOGIN, SENHA);

                instrucaoSQL = conexao.prepareStatement("INSERT INTO produto (descricao, nome, categoria, preco, cor, url_imagem, estoque_minimo, estoque_atual, ativo, codigo_barras) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
                                                        , Statement.RETURN_GENERATED_KEYS);  //Caso queira retornar o ID

                //Adiciono os parâmetros ao meu comando SQL
                
                instrucaoSQL.setString(1, obj.getDescricao());
                instrucaoSQL.setString(2, obj.getNome());
                instrucaoSQL.setString(3, obj.getDepartamento());
                instrucaoSQL.setDouble(4, obj.getPreco());
                instrucaoSQL.setString(5, obj.getCor());
                instrucaoSQL.setString(6, obj.getUrlImagem());
                instrucaoSQL.setInt(7, obj.getEstoqueMinimo());
                instrucaoSQL.setInt(8, obj.getEstoqueAtual());
                instrucaoSQL.setBoolean(9, true);
                instrucaoSQL.setInt(10, obj.getCodigoBarras());

                int linhasAfetadas = instrucaoSQL.executeUpdate();

                if(linhasAfetadas>0)
                {
                    retorno = true;
                }else{
                    retorno = false;
                }

            }catch (SQLException | ClassNotFoundException ex ) {
                System.out.println(ex.getMessage());
                retorno = false;
            }finally{

                //Libero os recursos da memória
                try {
                    if(instrucaoSQL!=null)
                        instrucaoSQL.close();

                    if(conexao!=null){
                        conexao.close();
                    }

                  } catch (SQLException ex) {
                 }
            }
            
            return retorno;
        }
        
        public static boolean alterar(Produto obj) {
        
            boolean retorno = false;
            Connection conexao = null;
            PreparedStatement instrucaoSQL = null;

            try {

               Class.forName("com.mysql.cj.jdbc.Driver");
               //Obs: A classe GerenciadorConexao já carrega o Driver e define os parâmetros de conexão
                conexao = DriverManager.getConnection(URL, LOGIN, SENHA);

                instrucaoSQL = conexao.prepareStatement("UPDATE produto set descricao = ?, nome = ?, categoria = ?, preco = ?, cor = ?, url_imagem = ?, estoque_minimo = ?, estoque_atual = ?, ativo = ?, codigo_barras = ? WHERE codigo_produto = ?");
                //Adiciono os parâmetros ao meu comando SQL
                
                instrucaoSQL.setString(1, obj.getDescricao());
                instrucaoSQL.setString(2, obj.getNome());
                instrucaoSQL.setString(3, obj.getDepartamento());
                instrucaoSQL.setDouble(4, obj.getPreco());
                instrucaoSQL.setString(5, obj.getCor());
                instrucaoSQL.setString(6, obj.getUrlImagem());
                instrucaoSQL.setInt(7, obj.getEstoqueMinimo());
                instrucaoSQL.setInt(8, obj.getEstoqueAtual());
                instrucaoSQL.setBoolean(9, true);
                instrucaoSQL.setInt(10, obj.getCodigoBarras());
                instrucaoSQL.setInt(11, obj.getId());

                int linhasAfetadas = instrucaoSQL.executeUpdate();

                if(linhasAfetadas>0)
                {
                    retorno = true;
                }else{
                    retorno = false;
                }

            }catch (SQLException | ClassNotFoundException ex ) {
                System.out.println(ex.getMessage());
                retorno = false;
            }finally{

                //Libero os recursos da memória
                try {
                    if(instrucaoSQL!=null)
                        instrucaoSQL.close();

                    if(conexao!=null){
                        conexao.close();
                    }

                  } catch (SQLException ex) {
                 }
            }
            
            return retorno;
        }
        
        public static boolean deletar(int id) {
        
            boolean retorno = false;
            Connection conexao = null;
            PreparedStatement instrucaoSQL = null;

            try {

               Class.forName("com.mysql.cj.jdbc.Driver");
               //Obs: A classe GerenciadorConexao já carrega o Driver e define os parâmetros de conexão
                conexao = DriverManager.getConnection(URL, LOGIN, SENHA);

                instrucaoSQL = conexao.prepareStatement("UPDATE produto set ativo = false WHERE codigo_produto = ?");
                //Adiciono os parâmetros ao meu comando SQL
                
                instrucaoSQL.setInt(1, id);

                int linhasAfetadas = instrucaoSQL.executeUpdate();

                if(linhasAfetadas>0)
                {
                    retorno = true;
                }else{
                    retorno = false;
                }

            }catch (SQLException | ClassNotFoundException ex ) {
                System.out.println(ex.getMessage());
                retorno = false;
            }finally{

                //Libero os recursos da memória
                try {
                    if(instrucaoSQL!=null)
                        instrucaoSQL.close();

                    if(conexao!=null){
                        conexao.close();
                    }

                  } catch (SQLException ex) {
                 }
            }
            
            return retorno;
        }
        
        public static  ArrayList<Produto> consultaTodos() {
        
            boolean retorno = false;
            Connection conexao = null;
            PreparedStatement instrucaoSQL = null;

            try {

               Class.forName("com.mysql.cj.jdbc.Driver");
               //Obs: A classe GerenciadorConexao já carrega o Driver e define os parâmetros de conexão
                conexao = DriverManager.getConnection(URL, LOGIN, SENHA);
                PreparedStatement consulta = conexao.prepareStatement("SELECT * FROM produto WHERE ativo = 1");
                ResultSet resultado = consulta.executeQuery();
                ArrayList<Produto> produtos = new ArrayList<>();

                while (resultado.next()) {
                    Produto produto = new Produto();
                    produto.setId(resultado.getInt("codigo_produto"));
                    produto.setNome(resultado.getString("nome"));
                    produto.setDescricao(resultado.getString("descricao"));
                    produto.setCodigoBarras(resultado.getInt("codigo_barras"));
                    produto.setCor(resultado.getString("cor"));
                    produto.setDepartamento(resultado.getString("categoria"));
                    produto.setEstoqueAtual(resultado.getInt("estoque_atual"));
                    produto.setEstoqueMinimo(resultado.getInt("estoque_minimo"));
                    produto.setPreco(resultado.getDouble("preco"));
                    produto.setUrlImagem(resultado.getString("url_imagem"));
                    produtos.add(produto);
                }
                return produtos;
                
            }catch (SQLException | ClassNotFoundException ex ) {
                
            }finally{

                //Libero os recursos da memória
                try {
                    if(instrucaoSQL!=null)
                        instrucaoSQL.close();

                    if(conexao!=null){
                        conexao.close();
                    }

                  } catch (SQLException ex) {
                 }
            }
            return null;
        }
        
        public static  ArrayList<Produto> consultaNome(String nome) {
        
            boolean retorno = false;
            Connection conexao = null;
            PreparedStatement instrucaoSQL = null;

            try {

               Class.forName("com.mysql.cj.jdbc.Driver");
               //Obs: A classe GerenciadorConexao já carrega o Driver e define os parâmetros de conexão
                conexao = DriverManager.getConnection(URL, LOGIN, SENHA);
                PreparedStatement consulta = conexao.prepareStatement("SELECT * FROM produto WHERE nome LIKE ? AND ativo = 1");
                consulta.setString(1, nome+"%");
                ResultSet resultado = consulta.executeQuery();
                ArrayList<Produto> produtos = new ArrayList<>();

                while (resultado.next()) {
                    Produto produto = new Produto();
                    produto.setId(resultado.getInt("codigo_produto"));
                    produto.setNome(resultado.getString("nome"));
                    produto.setDescricao(resultado.getString("descricao"));
                    produto.setCodigoBarras(resultado.getInt("codigo_barras"));
                    produto.setCor(resultado.getString("cor"));
                    produto.setDepartamento(resultado.getString("categoria"));
                    produto.setEstoqueAtual(resultado.getInt("estoque_atual"));
                    produto.setEstoqueMinimo(resultado.getInt("estoque_minimo"));
                    produto.setPreco(resultado.getDouble("preco"));
                    produto.setUrlImagem(resultado.getString("url_imagem"));
                    produtos.add(produto);
                }
                return produtos;
                
            }catch (SQLException | ClassNotFoundException ex ) {
                
            }finally{

                //Libero os recursos da memória
                try {
                    if(instrucaoSQL!=null)
                        instrucaoSQL.close();

                    if(conexao!=null){
                        conexao.close();
                    }

                  } catch (SQLException ex) {
                 }
            }
            return null;
        }
        
        public static  ArrayList<Produto> consultaCodigo(int codigo) {
        
            boolean retorno = false;
            Connection conexao = null;
            PreparedStatement instrucaoSQL = null;

            try {

               Class.forName("com.mysql.cj.jdbc.Driver");
               //Obs: A classe GerenciadorConexao já carrega o Driver e define os parâmetros de conexão
                conexao = DriverManager.getConnection(URL, LOGIN, SENHA);
                PreparedStatement consulta = conexao.prepareStatement("SELECT * FROM produto WHERE codigo_produto = ?");
                consulta.setInt(1, codigo);
                ResultSet resultado = consulta.executeQuery();
                ArrayList<Produto> produtos = new ArrayList<>();

                while (resultado.next()) {
                    Produto produto = new Produto();
                    produto.setId(resultado.getInt("codigo_produto"));
                    produto.setNome(resultado.getString("nome"));
                    produto.setDescricao(resultado.getString("descricao"));
                    produto.setCodigoBarras(resultado.getInt("codigo_barras"));
                    produto.setCor(resultado.getString("cor"));
                    produto.setDepartamento(resultado.getString("categoria"));
                    produto.setEstoqueAtual(resultado.getInt("estoque_atual"));
                    produto.setEstoqueMinimo(resultado.getInt("estoque_minimo"));
                    produto.setPreco(resultado.getDouble("preco"));
                    produto.setUrlImagem(resultado.getString("url_imagem"));
                    produtos.add(produto);
                }
                return produtos;
                
            }catch (SQLException | ClassNotFoundException ex ) {
                
            }finally{

                //Libero os recursos da memória
                try {
                    if(instrucaoSQL!=null)
                        instrucaoSQL.close();

                    if(conexao!=null){
                        conexao.close();
                    }

                  } catch (SQLException ex) {
                 }
            }
            return null;
        }
        
        public static Produto consultaCodigoAlterar(int codigo) {
        
            boolean retorno = false;
            Connection conexao = null;
            PreparedStatement instrucaoSQL = null;

            try {

               Class.forName("com.mysql.cj.jdbc.Driver");
               //Obs: A classe GerenciadorConexao já carrega o Driver e define os parâmetros de conexão
                conexao = DriverManager.getConnection(URL, LOGIN, SENHA);
                PreparedStatement consulta = conexao.prepareStatement("SELECT * FROM produto WHERE codigo_produto = ?");
                consulta.setInt(1, codigo);
                ResultSet resultado = consulta.executeQuery();
                Produto produto = new Produto();
                
                while (resultado.next()) {
                    produto.setId(resultado.getInt("codigo_produto"));
                    produto.setNome(resultado.getString("nome"));
                    produto.setDescricao(resultado.getString("descricao"));
                    produto.setCodigoBarras(resultado.getInt("codigo_barras"));
                    produto.setCor(resultado.getString("cor"));
                    produto.setDepartamento(resultado.getString("categoria"));
                    produto.setEstoqueAtual(resultado.getInt("estoque_atual"));
                    produto.setEstoqueMinimo(resultado.getInt("estoque_minimo"));
                    produto.setPreco(resultado.getDouble("preco"));
                    produto.setUrlImagem(resultado.getString("url_imagem"));
                }
                return produto;
                
            }catch (SQLException | ClassNotFoundException ex ) {
                
            }finally{

                //Libero os recursos da memória
                try {
                    if(instrucaoSQL!=null)
                        instrucaoSQL.close();

                    if(conexao!=null){
                        conexao.close();
                    }

                  } catch (SQLException ex) {
                 }
            }
            return null;
        }
}
