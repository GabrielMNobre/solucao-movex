/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.ProdutoDAO;
import Model.Produto;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ayrto
 */
public class ProdutoController {
    public boolean cadastraProduto(String nome, String descricao, int codigoBarras, String cor, String departamento, int estoqueAtual, int estoqueMinimo, double preco, String urlImagem)
    {
        Produto produto = new Produto();
        produto.setNome(nome);
        produto.setDescricao(descricao);
        produto.setCodigoBarras(codigoBarras);
        produto.setCor(cor);
        produto.setDepartamento(departamento);
        produto.setEstoqueAtual(estoqueAtual);
        produto.setEstoqueMinimo(estoqueMinimo);
        produto.setPreco(preco);
        produto.setUrlImagem(urlImagem);
        ProdutoDAO dao = new ProdutoDAO();
        return dao.salvar(produto);
    }
    
    public boolean alteraProduto(String nome, String descricao, int codigoBarras, String cor, String departamento, int estoqueAtual, int estoqueMinimo, double preco, String urlImagem, int id)
    {
        Produto produto = new Produto();
        produto.setNome(nome);
        produto.setDescricao(descricao);
        produto.setCodigoBarras(codigoBarras);
        produto.setCor(cor);
        produto.setDepartamento(departamento);
        produto.setEstoqueAtual(estoqueAtual);
        produto.setEstoqueMinimo(estoqueMinimo);
        produto.setPreco(preco);
        produto.setUrlImagem(urlImagem);
        produto.setId(id);
        return ProdutoDAO.alterar(produto);
    }
    
    public boolean deletaProduto (int id)
    {
        return ProdutoDAO.deletar(id);
    }
        
    private void preencheTabela (ArrayList<Produto> produtos, JTable tabela)
    {
        if (produtos.size() > 0) {
                DefaultTableModel tmProdutos = new DefaultTableModel();
                tmProdutos.addColumn("Codigo");
                tmProdutos.addColumn("Nome");
                tmProdutos.addColumn("Descricao");
                tmProdutos.addColumn("Departamento");
                tmProdutos.addColumn("Preço");
                tmProdutos.addColumn("Qtd Estoque");
                tmProdutos.addColumn("Estoque Min");
                tmProdutos.addColumn("Cod Barras");
                tmProdutos.addColumn("Ativo");

                tabela.setModel(tmProdutos);
                tmProdutos.setRowCount(0);
            
            for (Produto produto : produtos) {
                tmProdutos.addRow(new Object[]{produto.getId(), produto.getNome(), produto.getDescricao(), produto.getDepartamento(), 
                produto.getPreco(), produto.getEstoqueAtual(), produto.getEstoqueMinimo(), produto.getCodigoBarras(), produto.getAtivo()});
            }
        }
    }
        
    private void preencheTabelaVenda (ArrayList<Produto> produtos, JTable tabela)
    {
        if (produtos.size() > 0) {
                DefaultTableModel tmProdutos = new DefaultTableModel();
                tmProdutos.addColumn("Codigo");
                tmProdutos.addColumn("Nome");
                tmProdutos.addColumn("Descricao");
                tmProdutos.addColumn("Departamento");
                tmProdutos.addColumn("Preço");
                tmProdutos.addColumn("Qtd Estoque");
                
                tabela.setModel(tmProdutos);
                tmProdutos.setRowCount(0);
            
            for (Produto produto : produtos) {
                tmProdutos.addRow(new Object[]{produto.getId(), produto.getNome(), produto.getDescricao(), produto.getDepartamento(), 
                produto.getPreco(), produto.getEstoqueAtual()});
            }
        }
    }
    
    public void exibirTodos (JTable tabela)
    {
        ArrayList<Produto> produtos = new ArrayList<>();
        produtos = ProdutoDAO.consultaTodos();
        preencheTabela(produtos, tabela);    
    }
    
    public void exibePorCodigo(int codigo, JTable tabela, int tipoConsulta)
    {
        ArrayList<Produto> produtos = new ArrayList<>();
        produtos = ProdutoDAO.consultaCodigo(codigo);
        
        if (tipoConsulta == 1) {
            preencheTabela(produtos, tabela);
        } else {
            preencheTabelaVenda(produtos, tabela);
        }  
    }
    
    public void exibePorNome(String nome, JTable tabela, int tipoConsulta)
    {
        ArrayList<Produto> produtos = new ArrayList<>();
        produtos = ProdutoDAO.consultaNome(nome);
        if (tipoConsulta == 1) {
            preencheTabela(produtos, tabela);
        } else {
            preencheTabelaVenda(produtos, tabela);
        }
          
    }
    
    public String[] consultaCodigoAlterar(int idProduto)
    {   
        Produto produto = new Produto();
        produto = ProdutoDAO.consultaCodigoAlterar(idProduto);
        String[] produtos = null;
        
        if(produto.getId() > 0){
            produtos = new String[]{ String.valueOf(produto.getId()), produto.getNome(), produto.getDescricao(), produto.getDepartamento(), String.valueOf(produto.getPreco()), produto.getCor(), 
                String.valueOf(produto.getEstoqueAtual()), String.valueOf(produto.getEstoqueMinimo()), String.valueOf(produto.getCodigoBarras()), String.valueOf(produto.getAtivo())};
        }
        return produtos;
    }
}
