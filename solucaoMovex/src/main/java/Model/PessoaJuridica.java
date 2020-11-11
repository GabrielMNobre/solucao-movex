/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author gabriel
 */
public class PessoaJuridica extends Pessoa {
    
    private String cnpj;

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    
    public PessoaJuridica(String dados[]){
        setNome(dados[0]);
        setEmail(dados[1]);
        setTelefone(dados[2]);
        setCelular(dados[3]);
        setUf(dados[4]);
        setCidade(dados[5]);
        setCep(dados[6]);
        setLogradouro(dados[7]);
        setNumeroLogradouro(Integer.parseInt(dados[8]));
        setComplementoLogradouro(dados[9]);
        setCnpj(dados[10]);
    }
    
}
