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
public class NormalClass extends EmpregadoAbsClass implements Normal {

    /**
     *
     * @param nome
     * @param codigo
     * @param day
     * @param month
     * @param year
     */
    public NormalClass(String nome, int codigo, int day, int month, int year) {
        super(nome, codigo, day, month, year);
    }

    @Override
    public double getBonus(int mes, int ano) {
        throw new UnsupportedOperationException("Função indisponivel!");
    }

    @Override
    public void setBonus(double valor, int mes, int ano) {
        throw new UnsupportedOperationException("Função indisponivel!");
    }

}
