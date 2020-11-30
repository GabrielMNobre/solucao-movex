package Model;

import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JTextField;

/**
 *
 * @author user
 */
public class Venda {
    
    private int numeroPedido;
    private int codigoVendedor;
    private int codigoCliente;
    private double frete;
    private double desconto;
    private double total;
    private double subtotal;
    private String status;
    private ArrayList<String[]> pedidoDetalhe = new ArrayList<>();

    public ArrayList<String[]> getPedidoDetalhe() {
        return pedidoDetalhe;
    }

    public void setPedidoDetalhe(String codigoPedido, String codigoProduto, String quantidade, String preco, String total) {
        this.pedidoDetalhe.add(new String[] {
        codigoPedido, codigoProduto, quantidade, preco, total});
    }

    public Venda() {
    }

    public int getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(int numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public int getCodigoVendedor() {
        return codigoVendedor;
    }

    public void setCodigoVendedor(int codigoVendedor) {
        this.codigoVendedor = codigoVendedor;
    }

    public int getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(int codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public double getFrete() {
        return frete;
    }

    public void setFrete(double frete) {
        this.frete = frete;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}