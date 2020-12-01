/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.FuncionarioDAO;
import DAO.VendaDAO;
import Model.Pagamento;
import Model.Venda;
import java.util.Date;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author Ayrton
 */
public class VendaController {
    
    /**
     * 
     * @author Ayrton
     * @return String[] - Retorna um vetor de String com id e nome dos funcionários
     */
    public static String[] populaVendedores()
    {
        ArrayList<String[]> listaFuncionarios = FuncionarioController.selectAll();
        String[] listagem = new String[listaFuncionarios.size()];
        int n = 0;
        for (String[] func : listaFuncionarios) {
            listagem[n] = func[0]+" - "+func[1];
            n++;
        }
        return listagem;        
    }
    /**
     * @author Ayrton
     * @param lista - ArrayList de String[] - Recebe a lista com id e nome do cliente para popular o combobox
     * @param combo - JComboBox para retornar o valor da consulta para ele
     */
    public static void populaClientes (ArrayList<String[]> lista, JComboBox combo) {
        String[] listagem = new String[lista.size()];
        int n = 0;
        for (String[] cliente : lista) {
            listagem[n] = cliente[6]+" - "+cliente[0];
            n++;
        }
        DefaultComboBoxModel model = new DefaultComboBoxModel(listagem);
        combo.setModel(model);
    }
    /**
     * @author Ayrton
     * @param cliente - String - ID do cliente
     * @param vendedor - String - ID do vendedor
     * @param dataVenda - Date - Data da venda
     * @param carrinho - JTable - Produtos escolhidos pelo cliente
     * @return String[] - String[] -  String[0] - "sucesso" / "falha"
     * String[1] - Texto do sucesso ou da falha / String[2] - ID da venda, no caso de sucesso.
     */
    public static String[] finalizaVenda(String cliente, String vendedor, Date dataVenda, JTable carrinho)
    {   
        double totalPedido = 0;
        Venda venda = new Venda();
        for (int i = 0; i < carrinho.getRowCount(); i++) {
            double subTotal = Double.parseDouble(carrinho.getValueAt(i, 2).toString())*Integer.parseInt(carrinho.getValueAt(i, 3).toString());
            totalPedido += subTotal;
            
            venda.setPedidoDetalhe("A", 
                carrinho.getValueAt(i, 0).toString(), 
                carrinho.getValueAt(i, 3).toString(),
                carrinho.getValueAt(i, 2).toString(),
                Double.toString(subTotal));
        }
        
        String[] vend = vendedor.split(" - ");
        String[] cli = cliente.split(" - ");
        venda.setCodigoVendedor(Integer.parseInt(vend[0]));
        venda.setCodigoCliente(Integer.parseInt(cli[0]));
        venda.setFrete(0);
        venda.setDesconto(0);
        venda.setSubtotal(totalPedido);
        venda.setTotal(totalPedido);
        venda.setStatus("Aberto");
        venda.setDataVenda(dataVenda);
        return VendaDAO.salvar(venda);        
    }
    
    /**
     * @author Ayrton
     * @param numeroPedido - int - número do pedido
     * @param vendedor - JTextField - para popular o nome do vendedor
     * @param cliente - JTextField - para popular o nome do cliente
     * @param valorTotal - JTextField - para popular o valor total da compra
     * @return boolean - true: "Pedido localizado", false:"Pedido não localizado"
     */
    public static boolean consultaPedido(int numeroPedido, JTextField vendedor, JTextField cliente, JTextField valorTotal)
    {
        Venda obj = new Venda();
        obj = VendaDAO.consultaPedidoFinalizacao(numeroPedido);
        if (obj.getNumeroPedido() > 0) {
            String[] dado = FuncionarioDAO.selectByCod(obj.getNumeroPedido());
            if(dado != null) {
                vendedor.setText(dado[0]);
                cliente.setText(dado[1]);
                valorTotal.setText(Double.toString(obj.getTotal()));
            }
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * @author Ayrton
     * @param numeroPedido - int com o número do pedido
     * @return boolean - true:"Compra cancelada", false: "Não foi possível cancelar a compra"
     */
    public static boolean cancelaCompra (int numeroPedido)
    {
        return VendaDAO.cancelaCompra(numeroPedido);
    }
    
    /**
     * @author Ayrton
     * @param numeroPedido - int com o número do pedido.
     * @param pagamentos - JTable com a descrição dos pagamentos
     * @return boolean - true: "Pedido concluído com sucesso", false:"Não foi possível concluir o pedido"
     */
    public static boolean finalizaCompra (int numeroPedido, JTable pagamentos) {
        ArrayList<Pagamento> payments = new ArrayList<>();
        
        for (int i = 0; i < pagamentos.getRowCount(); i++) {
            Pagamento pagamento = new Pagamento();
            pagamento.setCodigo_pedido(numeroPedido);
            pagamento.setForma(pagamentos.getValueAt(i, 0).toString());
            pagamento.setParcelas(0);
            pagamento.setValor(Double.parseDouble(pagamentos.getValueAt(i, 1).toString()));
            payments.add(pagamento);
        }
        
        return VendaDAO.finalizaPedido(numeroPedido, payments);
    }
}
