/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Pagamento;
import Model.Venda;
import Utils.Conector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Ayrto
 */
public class VendaDAO {
    public static Conector conector;
    
    public void VendaDAO()
    {
        Conector conector = new Conector();
    }
    
    public static Venda consultaPedidoFinalizacao(int codigoPedido)
    {
        Connection conexao = conector.conectaBanco();
        PreparedStatement instrucaoSQL = null;
        Venda retorno = new Venda();
        ResultSet resultado = null;
        try {

                instrucaoSQL = conexao.prepareStatement("SELECT * FROM pedido_mestre WHERE numero_pedido = ? AND status = 'Aberto'");
                instrucaoSQL.setInt(1, codigoPedido);
                resultado = instrucaoSQL.executeQuery();
                            
                while(resultado.next())
                {
                    retorno.setCodigoCliente(resultado.getInt("codigo_cliente"));
                    retorno.setNumeroPedido(codigoPedido);
                    retorno.setCodigoVendedor(resultado.getInt("codigo_vendedor"));
                    retorno.setTotal(resultado.getDouble("total"));
                    return retorno;
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }finally{
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
    
    public static String[] salvar(Venda obj) {
        
            boolean retorno = false;
            Connection conexao = conector.conectaBanco();
            PreparedStatement instrucaoSQL = null;
            ArrayList<Integer> pedidosIncluidos = new ArrayList<>();
            String[] status = new String[2];
            
            try {

                instrucaoSQL = conexao.prepareStatement("INSERT INTO pedido_mestre (codigo_vendedor, codigo_cliente, frete, desconto, subtotal, total, status) VALUES(?, ?, ?, ?, ?, ?, ?)"
                , Statement.RETURN_GENERATED_KEYS);

                instrucaoSQL.setInt(1, obj.getCodigoVendedor());
                instrucaoSQL.setInt(2, obj.getCodigoCliente());
                instrucaoSQL.setDouble(3, obj.getFrete());
                instrucaoSQL.setDouble(4, obj.getDesconto());
                instrucaoSQL.setDouble(5, obj.getSubtotal());
                instrucaoSQL.setDouble(6, obj.getTotal());
                instrucaoSQL.setString(7, obj.getStatus());
                
                int linhasAfetadas = instrucaoSQL.executeUpdate();

                if(linhasAfetadas > 0)
                {   
                    int codigoPedido = 0;
                    ResultSet idPedido = instrucaoSQL.getGeneratedKeys();
                    int detalheSucesso = 0;
                    if (idPedido.next()) {
                        codigoPedido = idPedido.getInt(1);
                    }
                    for (String[] pedidoDetalhe : obj.getPedidoDetalhe() ){
                        instrucaoSQL = conexao.prepareStatement("SELECT estoque_atual FROM produto WHERE codigo_produto = ?");
                        instrucaoSQL.setInt(1, Integer.parseInt(pedidoDetalhe[1]));
                        ResultSet consulta = instrucaoSQL.executeQuery();
                        if(consulta.next()){
                            if (consulta.getInt("estoque_atual") < Integer.parseInt(pedidoDetalhe[2])) {
                                excluiItens(codigoPedido);
                                status[0] = "falha";
                                status[1] = "Você incluiu a quantidade do produto de código "+pedidoDetalhe[1]+" maior de que temos em estoque.";
                                return status;
                            } else {
                                instrucaoSQL = conexao.prepareStatement("INSERT INTO pedido_detalhe (codigo_pedido, codigo_produto, quantidade, preco, total) VALUES(?, ?, ?, ?, ?)"
                                , Statement.RETURN_GENERATED_KEYS);
                                instrucaoSQL.setInt(1, codigoPedido);
                                instrucaoSQL.setInt(2, Integer.parseInt(pedidoDetalhe[1]));
                                instrucaoSQL.setInt(3, Integer.parseInt(pedidoDetalhe[2]));
                                instrucaoSQL.setDouble(4, Double.parseDouble(pedidoDetalhe[3]));
                                instrucaoSQL.setDouble(5, Double.parseDouble(pedidoDetalhe[4]));
                                instrucaoSQL.executeUpdate();
                            }  
                        }               
                    }
                    status[0] = "sucesso";
                    status[1] = "O pedido foi inserido com sucesso, ID: "+codigoPedido;
                }else{
                    status[0] = "falha";
                    status[1] = "Não foi possível inserir o seu pedido, verifique os dados informados.";
                }

            } catch (SQLException e) {
                status[0] = "falha";
                status[1] = "Houve o seguinte erro de SQL: "+e.getMessage();
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
            
            return status;
        }
     
    public static boolean excluiItens (int numeroPedido)
    {   
        Connection conexao = conector.conectaBanco();
        PreparedStatement instrucaoSQL = null;
        
        try {

                instrucaoSQL = conexao.prepareStatement("DELETE FROM pedido_detalhe WHERE codigo_pedido = ?");
                instrucaoSQL.setInt(1, numeroPedido);
                int linhasAfetadas = instrucaoSQL.executeUpdate();
                
                instrucaoSQL = conexao.prepareStatement("DELETE FROM pedido_mestre WHERE numero_pedido = ?");
                instrucaoSQL.setInt(1, numeroPedido); 
                linhasAfetadas = instrucaoSQL.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }finally{
                try {
                    if(instrucaoSQL!=null)
                        instrucaoSQL.close();

                    if(conexao!=null){
                        conexao.close();
                    }

                  } catch (SQLException ex) {
                 }
            }
        
        return true;
    }
    
    public static boolean cancelaCompra (int numeroPedido)
    {   
        Connection conexao = conector.conectaBanco();
        PreparedStatement instrucaoSQL = null;
        
        try {

                instrucaoSQL = conexao.prepareStatement("DELETE FROM pedido_detalhe WHERE codigo_pedido = ?");
                instrucaoSQL.setInt(1, numeroPedido);
                int linhasAfetadas = instrucaoSQL.executeUpdate();
                
                instrucaoSQL = conexao.prepareStatement("UPDATE pedido_mestre SET status = 'Cancelado' WHERE numero_pedido = ?");
                instrucaoSQL.setInt(1, numeroPedido); 
                linhasAfetadas = instrucaoSQL.executeUpdate();
                return true;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }finally{
                try {
                    if(instrucaoSQL!=null)
                        instrucaoSQL.close();

                    if(conexao!=null){
                        conexao.close();
                    }

                  } catch (SQLException ex) {
                 }
            }
    }
    
    public static boolean finalizaPedido (int numeroPedido, ArrayList<Pagamento> pagamentos)
    {
        Connection conexao = conector.conectaBanco();
        PreparedStatement instrucaoSQL = null;
        
        try {
                
                for (Pagamento payments : pagamentos ){
                    instrucaoSQL = conexao.prepareStatement("INSERT INTO pagamento (codigo_pedido, forma, parcelas, valor)VALUES(?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

                    instrucaoSQL.setInt(1, numeroPedido);
                    instrucaoSQL.setString(2, payments.getForma());
                    instrucaoSQL.setDouble(3, payments.getParcelas());
                    instrucaoSQL.setDouble(4, payments.getValor());
                    instrucaoSQL.executeUpdate();
                }
                
                instrucaoSQL = conexao.prepareStatement("UPDATE pedido_mestre SET status = 'Finalizado' WHERE numero_pedido = ? ");
                instrucaoSQL.setInt(1, numeroPedido);
                instrucaoSQL.executeUpdate();
                return true;
                
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return true;
            }finally{
                try {
                    if(instrucaoSQL!=null)
                        instrucaoSQL.close();

                    if(conexao!=null){
                        conexao.close();
                    }

                  } catch (SQLException ex) {
                 }
            }
    }
}
