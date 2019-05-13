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
public interface Empresa {

    void addEmpregadoComercial(String nome, int codigo, int day, int month, int year);

    void addEmpregadoGestor(String nome, int codigo, int day, int month, int year);

    void addEmpregadoMotorista(String nome, int codigo, int day, int month, int year);

    void addEmpregadoNormal(String nome, int codigo, int day, int month, int year);

}
