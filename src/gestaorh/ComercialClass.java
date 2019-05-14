package gestaorh;

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

    private double vendas;

    public ComercialClass(String nome, int codigo, int day, int month, int year) {
        super(nome, codigo, day, month, year);
        vendas = 0.0;
    }

    public void addVenda(double preco) throws GestaoException {
        vendas += preco;
    }

    @Override
    public double getBonus() {
        return vendas;
    }

    @Override
    public void setBonus(double valor) {
        vendas += valor;
    }

}
