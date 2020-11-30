/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Ayrto
 */
public class Pagamento {
    private int codigo_pagamento;
    private int codigo_pedido;
    private String forma;
    private double parcelas;
    private double valor;

    public int getCodigo_pagamento() {
        return codigo_pagamento;
    }

    public void setCodigo_pagamento(int codigo_pagamento) {
        this.codigo_pagamento = codigo_pagamento;
    }

    public int getCodigo_pedido() {
        return codigo_pedido;
    }

    public void setCodigo_pedido(int codigo_pedido) {
        this.codigo_pedido = codigo_pedido;
    }

    public String getForma() {
        return forma;
    }

    public void setForma(String forma) {
        this.forma = forma;
    }

    public double getParcelas() {
        return parcelas;
    }

    public void setParcelas(double parcelas) {
        this.parcelas = parcelas;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    
}
