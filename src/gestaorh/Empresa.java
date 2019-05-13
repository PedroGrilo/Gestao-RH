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

    void checkCodigo(int codigo) throws GestaoException;

    String getCategoria(Empregado e) throws GestaoException;

    String getEmpregado(int codigo);

    String printEmpregado(Empregado empregado);

    int getNumeroEmpregados(String categoria);

    double getSalario(Empregado e, int mes, int ano);

    double getSalarioPorDia();

    boolean empregadoIsExists(int codigo);

    String getTotalEmpregados();

    String getTotalEmpregadoFiltrados();

    double getTotalSalariosPagar();
}
