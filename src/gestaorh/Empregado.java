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
public interface Empregado {

    /**
     *
     * @return
     */
    int getCodigo();

    /**
     *
     * @return
     */
    String getNome();

    /**
     *
     * @param codigo
     */
    void setCodigo(int codigo);

    /**
     *
     * @param nome
     */
    void setNome(String nome);

    /**
     *
     * @param dia
     * @param mes
     * @param ano
     */
    void setDiaTrabalho(int dia, int mes, int ano);

    /**
     *
     * @param mes
     * @param ano
     * @return
     */
    int getDiasTrabalho(int mes, int ano);

    /**
     *
     * @return
     */
    Date getDataEntradaEmpresa();

    /**
     *
     * @return
     */
    int getDiasTrabalhoTotal();

    /**
     *
     * @return
     */
    int getAnosTrabalho();

    /**
     *
     * @param mes
     * @param ano
     * @return
     */
    double getBonus(int mes, int ano);

    /**
     *
     * @param valor
     * @param mes
     * @param ano
     */
    void setBonus(double valor, int mes, int ano);

    /**
     *
     * @return
     */
    ArrayList<Date> getDatasQueTrabalhou();

}
