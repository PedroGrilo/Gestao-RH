/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestaorh;

/**
 *
 * @author Pedro Grilo
 */
public class VendasClass implements Vendas {

    private String tipoVenda;
    private String descricao;
    private Date dataVenda;
    private double preco;

    public VendasClass(String tipoVenda, String descricao, double preco, Date DataVenda) {
        this.tipoVenda = tipoVenda;
        this.descricao = descricao;
        this.dataVenda = dataVenda;
        this.preco = preco;
    }

    public String getTipoVenda() {
        return tipoVenda;
    }

    public void setTipoVenda(String tipoVenda) {
        this.tipoVenda = tipoVenda;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

}
