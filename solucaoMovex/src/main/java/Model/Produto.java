/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Administrador
 */
public class Produto {
    
    //Atributos
    private int id;
    private String nome;
    private int estoqueAtual;
    private int estoqueMinimo;
    private int codigoBarras;
    private double preco;
    private String cor;
    private String descricao;
    private String urlImagem;
    private String departamento;
    private boolean ativo;

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    
    //Construtor
    public Produto(){} 

    //Métodos Acessores

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getEstoqueAtual(){
        return estoqueAtual;
    }
    public void setEstoqueAtual(int pEstoqueAtual){
        this.estoqueAtual = pEstoqueAtual;
    }
    
    public int getEstoqueMinimo(){
        return estoqueMinimo;
    }
    public void setEstoqueMinimo(int pEstoqueMinimo){
        this.estoqueMinimo = pEstoqueMinimo;
    }
    
    public int getCodigoBarras(){
        return codigoBarras;
    }
    public void setCodigoBarras(int pCodigoBarras){
        this.codigoBarras = pCodigoBarras;
    }
    
    public double getPreco(){
        return preco;
    }
    public void setPreco(double pPreco){
        this.preco = pPreco;
    }
    
     public String getCor(){
        return cor;
    }
    public void setCor(String pCor){
        this.cor = pCor;
    }
    
     public String getObservacoes(){
        return descricao;
    }
    public void setObservacoes(String pObservacoes){
        this.descricao = pObservacoes;
    }
    
     public String getUrlImagem(){
        return urlImagem;
    }
    public void setUrlImagem(String pUrlImagem){
        this.urlImagem = pUrlImagem;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
    
    
}
