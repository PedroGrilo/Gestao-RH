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
public interface Vendas {

    String getDescricao();

    double getPreco();

    String getTipoVenda();

    void setDescricao(String descricao);

    void setPreco(double preco);

    void setTipoVenda(String tipoVenda);

}
