package gestaorh;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
import gestaorh.GestaoErro;
import gestaorh.GestaoException;
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

    public EmpregadoAbsClass(String nome, int codigo, int day, int month, int year) {
        this.nome = nome;
        this.codigo = codigo;
        dataEntradaEmpresa = new DateClass(day, month, year);
        datasQueTrabalhou = new ArrayList<>();

    }

    public int getDiasTrabalho(int mes, int ano) {
        int diasDeTrabalho = 0;
        for (Date dataToCompare : datasQueTrabalhou) {
            if (dataToCompare.getMonth() == mes && dataToCompare.getYear() == ano) {
                diasDeTrabalho++;
            }
        }
        return diasDeTrabalho;
    }

    public int getAnosTrabalho() {
        for (Date dataToCompare : datasQueTrabalhou) {

        }
        return dataEntradaEmpresa.getYearsTilToday();
    }

    public int getDiasTrabalhoTotal() {
        return datasQueTrabalhou.size();

    }

    public void setDiaTrabalho(int dia, int mes, int ano) throws GestaoException {

        Date data = new DateClass(dia, mes, ano);
        Date dataAtual = new DateClass();

        for (Date dataToCompare : datasQueTrabalhou) {
            if (dataToCompare.toString().equals(data.toString())) {
                throw new GestaoException(GestaoErro.DATA_EXISTENTE);
            }
        }

        if ((!verifyDate(data, dataEntradaEmpresa)) || (!verifyDate(dataAtual, data))) {
            throw new GestaoException(GestaoErro.DATA_INVALIDA);
        }

        datasQueTrabalhou.add(data);
    }

    public boolean verifyDate(Date data1, Date data2) {
        if (data1.getYear() < data2.getYear()) {
            return false;
        }
        if (data1.getMonth() < data2.getMonth() && data1.getYear() <= data2.getYear()) {
            return false;
        }

        if (data1.getDay() < data2.getDay() && data1.getMonth() <= data2.getMonth() && data1.getYear() <= data2.getYear()) {
            return false;
        }
        return true;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Date getDataEntradaEmpresa() {
        return dataEntradaEmpresa;
    }

    public abstract double getBonus();

    public abstract void setBonus(double valor);

}
