package gestaorh;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
import gestaorh.exceptions.GestaoErro;
import gestaorh.exceptions.GestaoException;
import java.util.ArrayList;

/**
 *
 * @author Pedro Grilo
 */
public abstract class EmpregadoAbsClass implements Empregado {

    private String nome;
    private int codigo;
    private Date dataEntradaEmpresa;
    private ArrayList<Date> datasQueTrabalhou;

    /**
     *
     * @param nome
     * @param codigo
     * @param day
     * @param month
     * @param year
     */
    public EmpregadoAbsClass(String nome, int codigo, int day, int month, int year) {
        this.nome = nome;
        this.codigo = codigo;
        dataEntradaEmpresa = new DateClass(day, month, year);
        datasQueTrabalhou = new ArrayList<>();
    }

    /**
     *
     * @return
     */
    public ArrayList<Date> getDatasQueTrabalhou() {
        return datasQueTrabalhou;
    }

    /**
     *
     * @param mes
     * @param ano
     * @return
     */
    public int getDiasTrabalho(int mes, int ano) {
        int diasDeTrabalho = 0;
        for (Date dataToCompare : datasQueTrabalhou) {
            if (dataToCompare.getMonth() == mes && dataToCompare.getYear() == ano) {
                diasDeTrabalho++;
            }
        }
        return diasDeTrabalho;
    }

    /**
     *
     * @return
     */
    public int getAnosTrabalho() {
        return dataEntradaEmpresa.getYearsTilToday();
    }

    /**
     *
     * @return
     */
    public int getDiasTrabalhoTotal() {
        return datasQueTrabalhou.size();

    }

    /**
     *
     * @param dia
     * @param mes
     * @param ano
     * @throws GestaoException
     */
    public void setDiaTrabalho(int dia, int mes, int ano) throws GestaoException {

        Date data = new DateClass(dia, mes, ano);

        for (Date dataToCompare : datasQueTrabalhou) {
            if (dataToCompare.toString().equals(data.toString())) {
                throw new GestaoException(GestaoErro.DATA_EXISTENTE);
            }
        }

        datasQueTrabalhou.add(data);
    }

    /**
     *
     * @return
     */
    public String getNome() {
        return nome;
    }

    /**
     *
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     *
     * @return
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     *
     * @param codigo
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     *
     * @return
     */
    public Date getDataEntradaEmpresa() {
        return dataEntradaEmpresa;
    }

    /**
     *
     * @param mes
     * @param ano
     * @return
     */
    public abstract double getBonus(int mes, int ano);

    /**
     *
     * @param valor
     * @param mes
     * @param ano
     */
    public abstract void setBonus(double valor, int mes, int ano);

}
