/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.VendaDAO;
import Model.Cliente;
import Model.Funcionario;
import Model.Pagamento;
import Model.Venda;
import static View.VendaNova.model;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ayrto
 */
public class VendaController {
    public static String[] populaVendedores()
    {
        FuncionarioController funcionario = new FuncionarioController();
        ArrayList<String[]> listaFuncionarios = funcionario.selectAll();
        String[] listagem = new String[listaFuncionarios.size()];
        int n = 0;
        for (String[] func : listaFuncionarios) {
            listagem[n] = func[0]+" - "+func[1];
            n++;
        }
        return listagem;        
    }
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
    public static String[] finalizaVenda(String cliente, String vendedor, JTable carrinho)
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
        return VendaDAO.salvar(venda);        
    }
    
    public static boolean consultaPedido(int numeroPedido, JTextField vendedor, JTextField cliente, JTextField valorTotal)
    {
        Venda obj = new Venda();
        obj = VendaDAO.consultaPedidoFinalizacao(numeroPedido);
        Cliente cli = new Cliente();
        Funcionario vend = new Funcionario();
        if (obj.getNumeroPedido() > 0) {
            vendedor.setText("Ayrton");
            cliente.setText("Estephanie");
            valorTotal.setText(Double.toString(obj.getTotal()));
            return true;
        } else {
            return false;
        }
    }
    
    public static boolean cancelaCompra (int numeroPedido)
    {
        return VendaDAO.cancelaCompra(numeroPedido);
    }
    
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
