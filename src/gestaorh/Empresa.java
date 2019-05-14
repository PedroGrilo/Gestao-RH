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

    String calcularCustosAnuais();

    String calcularCustosAnual(int ano);

    String calcularSemestreAno(int ano);

    String calcularSemestres();

    String calcularTrimestreAno(int ano);

    String calcularTrimestres();

    boolean empregadoIsExists(Empregado emp);

    boolean empregadoIsExists(int codigo);

    String getCategoria(Empregado e) throws GestaoException;

    String getEmpregado(int codigo) throws GestaoException;

    int getNumeroEmpregados(String categoria);

    double getSalarioBase(Empregado e, int mes, int ano);

    double getSalarioEmp(Empregado e, int mes, int ano);

    double getSalarioPorDia();

    double getSubsidioAlimentacao();

    String getTotalEmpregadoFiltrados();

    String getTotalEmpregados();

    double getTotalSalariosPagar();

    boolean picarDiaTrabalho(int codigo, int ano, int mes, int dia);

    String printEmpregado(Empregado empregado);

    boolean adicionarBonus(int codigo, double valor);

    boolean checkCategoriaCodigo(int codigo, String categoria);

}
