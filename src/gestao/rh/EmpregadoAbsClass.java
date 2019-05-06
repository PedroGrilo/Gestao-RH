/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;

/**
 *
 * @author Pedro Grilo
 */
public abstract class EmpregadoAbsClass implements Empregado {

    private String nome;
    private int codigo;
    private ArrayList<Ano> ano;
    private int bonusPorAntiguidade;
    private int bonus;
    private Date dataEntradaEmpresa;

    public EmpregadoAbsClass(String nome, int codigo, int day, int month, int year) {

        this.nome = nome;
        this.codigo = codigo;

        dataEntradaEmpresa = new DateClass(day, month, year);
        ano = new ArrayList<Ano>();

        Date dataAtual = new DateClass();
        int yearsTilToday = dataAtual.getYear() - dataEntradaEmpresa.getYear();

        if (dataEntradaEmpresa.getYear() < dataAtual.getYear()) {
            for (int i = 0; i <= yearsTilToday; i++) {
                ano.add(new AnoClass(dataEntradaEmpresa, (dataEntradaEmpresa.getYear() + i)));
            }
        } else {  //este ano
            ano.add(new AnoClass(dataAtual.getMonth() - dataEntradaEmpresa.getMonth(), dataEntradaEmpresa.getYear(), dataEntradaEmpresa));
        }
    }

    public void setNumeroDeDiasQueTrabalhou(int setDiasDeTrabalho, String setMes, int setAno) {

        for (Ano anoToCompare : ano) {
            if (anoToCompare.getAno() == setAno) {
                anoToCompare.setDiasQueTrabalhou(setDiasDeTrabalho, setMes);
                break;
            }
        }

    }

    public int getNumeroDiasQueTrabalhou(int mes) {
        /*
         * if (mes > 0 && mes <= 12) { return
         * meses.get(mes).getDiasQueTrabalhou(); }
         */ return -1;

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

}
