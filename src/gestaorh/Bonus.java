package gestaorh;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Pedro Grilo
 */
public class Bonus {

    private int ano;
    private int mes;
    private double valor;

    /**
     *
     * @param ano
     * @param mes
     * @param valor
     */
    public Bonus(int ano, int mes, double valor) {
        this.ano = ano;
        this.mes = mes;
        this.valor = valor;
    }

    /**
     *
     * @return
     */
    public int getAno() {
        return ano;
    }

    /**
     *
     * @param ano
     */
    public void setAno(int ano) {
        this.ano = ano;
    }

    /**
     *
     * @return
     */
    public int getMes() {
        return mes;
    }

    /**
     *
     * @param mes
     */
    public void setMes(int mes) {
        this.mes = mes;
    }

    /**
     *
     * @return
     */
    public double getValor() {
        return valor;
    }

    /**
     *
     * @param valor
     */
    public void setValor(double valor) {
        this.valor += valor;
    }

}
