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
public interface Normal extends Empregado {

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
}
