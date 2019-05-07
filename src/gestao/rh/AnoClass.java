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
    private ArrayList<String> monthsAdd;

    public AnoClass(Date DataEntradaEmpresa, int anoExtenso) {

        ano = anoExtenso;
        meses = new ArrayList<Mes>();
        Date dataAtual = new DateClass();
        monthsAdd = new ArrayList<>();

        int nrMeses = 12;
        int startMonth = DataEntradaEmpresa.getMonth() - 1;

        if (DataEntradaEmpresa.getYear() == anoExtenso) { // ou seja, o primeiro ano
            nrMeses -= DataEntradaEmpresa.getMonth();
            for (int i = 0; i <= nrMeses; i++) {
                meses.add(new MesClass(months[startMonth + i]));
                monthsAdd.add(months[startMonth + i]);
            }

        } else if (dataAtual.getYear() == anoExtenso) {
            nrMeses = dataAtual.getMonth() - 1;
            for (int i = 0; i < nrMeses; i++) {
                meses.add(new MesClass(months[i]));
                monthsAdd.add(months[i]);
            }
        } else {
            for (int i = 0; i < nrMeses; i++) {
                meses.add(new MesClass(months[i]));
                monthsAdd.add(months[i]);
            }
        }

    }

    public AnoClass(int nrMeses, int ano, Date dataEntradaEmpresa) {

        this.ano = ano;
        meses = new ArrayList<Mes>();
        int startMonth = dataEntradaEmpresa.getMonth() - 1;;

        for (int i = 0; i < nrMeses; i++) {
            meses.add(new MesClass(months[startMonth + i]));
            monthsAdd.add(months[startMonth + i]);
        };

    }

    public void setDiasQueTrabalhou(int diasQueTrabalhou, String mes) {
        for (int i = 0; i < monthsAdd.size(); i++) {
            if (monthsAdd.get(i) == mes) {
                meses.get(i).setDiasQueTrabalhou(diasQueTrabalhou);
                break;
            }
        }
    }

    public int getDiasQueTrabalhou(String mes) {
        for (int i = 0; i < monthsAdd.size(); i++) {
            if (monthsAdd.get(i) == mes) {
                return meses.get(i).getDiasQueTrabalhou();
            }
        }
        return -1;
    }

    public int getAno() {
        return ano;
    }

}
