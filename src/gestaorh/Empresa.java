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
public interface Empresa {

    /**
     *
     * @param nome
     * @param codigo
     * @param day
     * @param month
     * @param year
     */
    void addEmpregadoComercial(String nome, int codigo, int day, int month, int year);

    /**
     *
     * @param nome
     * @param codigo
     * @param day
     * @param month
     * @param year
     */
    void addEmpregadoGestor(String nome, int codigo, int day, int month, int year);

    /**
     *
     * @param nome
     * @param codigo
     * @param day
     * @param month
     * @param year
     */
    void addEmpregadoMotorista(String nome, int codigo, int day, int month, int year);

    /**
     *
     * @param nome
     * @param codigo
     * @param day
     * @param month
     * @param year
     */
    void addEmpregadoNormal(String nome, int codigo, int day, int month, int year);

    /**
     *
     * @param codigo
     * @param valor
     * @param mes
     * @param ano
     * @return
     * @throws GestaoException
     */
    boolean adicionarBonus(int codigo, double valor, int mes, int ano) throws GestaoException;

    /**
     *
     * @param periodo
     * @return
     */
    String calcularCustos(Periodos periodo);

    /**
     *
     * @param codigo
     * @param categoria
     * @return
     * @throws GestaoException
     */
    boolean checkCategoriaCodigo(int codigo, String categoria) throws GestaoException;

    /**
     *
     * @param emp
     * @return
     */
    boolean empregadoIsExists(Empregado emp);

    /**
     *
     * @param codigo
     * @return
     */
    boolean empregadoIsExists(int codigo);

    /**
     *
     * @param e
     * @return
     * @throws GestaoException
     */
    String getCategoria(Empregado e) throws GestaoException;

    /**
     *
     * @param codigo
     * @return
     * @throws GestaoException
     */
    String getEmpregadoString(int codigo) throws GestaoException;

    /**
     *
     * @param categoria
     * @return
     */
    int getNumeroEmpregados(String categoria);

    /**
     *
     * @param e
     * @param mes
     * @param ano
     * @return
     */
    double getSalarioBase(Empregado e, int mes, int ano);

    /**
     *
     * @return
     */
    double getSalarioPorDia();

    /**
     *
     * @return
     */
    double getSubsidioAlimentacao();

    /**
     *
     * @return
     */
    String getTotalEmpregadoFiltrados();

    /**
     *
     * @return
     */
    String getTotalEmpregados();

    /**
     *
     * @return
     */
    String getTotalSalariosPagar();

    /**
     *
     * @param codigo
     * @param ano
     * @param mes
     * @param dia
     * @return
     * @throws GestaoException
     */
    boolean picarDiaTrabalho(int codigo, int ano, int mes, int dia) throws GestaoException;

    /**
     *
     * @param empregado
     * @return
     */
    String printEmpregado(Empregado empregado);

    /**
     *
     * @param codigo
     * @return
     */
    Empregado getEmpregado(int codigo);

    /**
     *
     * @return
     */
    ArrayList<Empregado> getEmpregado();

    /**
     *
     * @param empregado
     */
    void setEmpregado(ArrayList<Empregado> empregado);

    /**
     *
     * @param codigo
     * @throws GestaoException
     */
    void checkCodigo(int codigo) throws GestaoException;

    /**
     *
     * @param data1
     * @param data2
     * @return
     */
    boolean verifyDate(Date data1, Date data2);

    /**
     *
     * @param empregados
     */
    void inserirEmpregados(Empregado[] empregados);

}
