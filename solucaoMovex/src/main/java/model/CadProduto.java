/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Administrador
 */
public class CadProduto {
    
    //Atributos
    private int id;
    private String nome;
    private int estoqueAtual;
    private int estoqueMinimo;
    private int codigoBarras;
    private double preco;
    private String cor;
    private String observacoes;
    private String urlImagem;
    private String departamento;
    
    //Construtor
    public CadProduto(String dados[]){
        setId(Integer.parseInt(dados[0]));
        setPreco(Double.parseDouble(dados[1]));
        setCor(dados[2]);
        setDepartamento(dados[3]);
        setNome(dados[4]);
        setCodigoBarras(Integer.parseInt(dados[5]));
        setEstoqueMinimo(Integer.parseInt(dados[6]));
        setEstoqueAtual(Integer.parseInt(dados[7]));
    } 

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
        return observacoes;
    }
    public void setObservacoes(String pObservacoes){
        this.observacoes = pObservacoes;
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
