/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 

/**
 *
 * @author Pedro Grilo
 */
public class MesClass implements Mes {

    private int diasQueTrabalhou;
    private String mesExtenso;

    public MesClass(String mesExtenso) {
        this.mesExtenso = mesExtenso;
        diasQueTrabalhou = 0;
    }

    public void setMes(String mes) {
        mesExtenso = mes;
    }

    public int getDiasQueTrabalhou() {
        return diasQueTrabalhou;
    }

    public void setDiasQueTrabalhou(int diasQueTrabalhou) {
        this.diasQueTrabalhou = diasQueTrabalhou;
    }

}
