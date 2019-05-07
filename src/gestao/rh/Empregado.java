/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Pedro Grilo
 */
public interface Empregado {

    int getCodigo();

    String getNome();

    void setCodigo(int codigo);

    void setNome(String nome);

    void setNumeroDeDiasQueTrabalhou(int dias, String mes, int anos);

    int getNumeroDiasQueTrabalhou(String getMes, int getAno);

}
