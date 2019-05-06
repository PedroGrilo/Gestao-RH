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
public class AnoClass implements Ano {

    private ArrayList<Mes> meses;
    private int ano;
    private String[] months = {"Janeiro", "Fevereiro", "Marco", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};

    public AnoClass(Date DataEntradaEmpresa, int anoExtenso) {

        ano = anoExtenso;
        meses = new ArrayList<Mes>();
        Date dataAtual = new DateClass();

        int nrMeses = 12;
        int startMonth = DataEntradaEmpresa.getMonth() - 1;

        if (DataEntradaEmpresa.getYear() == anoExtenso) { // ou seja, o primeiro ano
            nrMeses -= DataEntradaEmpresa.getMonth();
            for (int i = 0; i <= nrMeses; i++) {
                meses.add(new MesClass(months[startMonth + i]));
            }

        } else if (dataAtual.getYear() == anoExtenso) {
            nrMeses = dataAtual.getMonth() - 1;
            for (int i = 0; i < nrMeses; i++) {
                meses.add(new MesClass(months[i]));
            }
        } else {
            for (int i = 0; i < nrMeses; i++) {
                meses.add(new MesClass(months[i]));
            }
        }

    }

    public AnoClass(int nrMeses, int ano, Date dataEntradaEmpresa) {

        this.ano = ano;
        meses = new ArrayList<Mes>();
        int startMonth = dataEntradaEmpresa.getMonth() - 1;;

        for (int i = 0; i < nrMeses; i++) {
            meses.add(new MesClass(months[startMonth + i]));
        };

    }

    public void setDiasQueTrabalhou(int diasQueTrabalhou, int mes) {
        meses.get(mes - 1).setDiasQueTrabalhou(diasQueTrabalhou);
    }

    public int getAno() {
        return ano;
    }

}
