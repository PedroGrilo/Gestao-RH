package gestaorh;

import gestaorh.exceptions.GestaoException;

import java.io.Serializable;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */

/**
 * @author Pedro Grilo
 */
public class MotoristaClass extends EmpregadoAbsClass implements Motorista, Serializable {

    private ArrayList<Bonus> kmPercorridos;

    /**
     * @param nome
     * @param codigo
     * @param day
     * @param month
     * @param year
     */
    public MotoristaClass(String nome, int codigo, int day, int month, int year) {
        super(nome, codigo, day, month, year);
        kmPercorridos = new ArrayList<>();
    }

    public double getBonus(int mes, int ano) throws GestaoException {
        for (Bonus b : kmPercorridos) {
            if (b.getAno() == ano && b.getMes() == mes) {
                return b.getValor();
            }
        }
        return 0.0;
    }

    @Override
    public void setBonus(double valor, int mes, int ano) {
        boolean notFounded = true;

        for (Bonus v : kmPercorridos) {
            if (v.getAno() == ano && v.getMes() == mes) {
                v.setValor(valor);

                notFounded = false;
                break;
            }
        }

        if (notFounded) {
            kmPercorridos.add(new Bonus(ano, mes, valor));
        }
    }
}
