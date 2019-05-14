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
public interface Empregado {

    int getCodigo();

    String getNome();

    void setCodigo(int codigo);

    void setNome(String nome);

    void setDiaTrabalho(int dia, int mes, int ano);

    int getDiasTrabalho(int mes, int ano);

    Date getDataEntradaEmpresa();

    int getDiasTrabalhoTotal();
    
    int getAnosTrabalho();
    
    double getSalario();

}
