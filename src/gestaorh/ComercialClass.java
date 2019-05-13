package gestaorh;

import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
/**
 *
 * @author Pedro Grilo
 */
public class ComercialClass extends EmpregadoAbsClass implements Comercial {

    private ArrayList<Vendas> vendas;

    public ComercialClass(String nome, int codigo, int day, int month, int year) {
        super(nome, codigo, day, month, year);
        vendas = new ArrayList<>();
    }

    private boolean checkString(String field) {
        return (field == null || field == "");
    }

    public void addVenda(String tipoVenda, String descricao, double preco, Date dataVenda) throws GestaoException {

        if (checkString(tipoVenda) || checkString(descricao) || preco < 0) {
            throw new GestaoException(GestaoErro.CAMPOS_VAZIOS);
        }

        Vendas newVenda = new VendasClass(tipoVenda, descricao, preco, dataVenda);

        if (newVenda != null) {
            for (Vendas v : vendas) {
                if (v.getTipoVenda() == newVenda.getTipoVenda() && v.getDescricao() == newVenda.getDescricao() && v.getPreco() == newVenda.getPreco()) {
                    throw new GestaoException(GestaoErro.VENDA_JA_EXISTE);
                }
            }
        }
        vendas.add(newVenda);

    }

}
