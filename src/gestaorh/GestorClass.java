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
public class GestorClass extends EmpregadoAbsClass implements Gestor {

    public GestorClass(String nome, int codigo, int day, int month, int year) {
        super(nome, codigo, day, month, year);
    }

    @Override
    public double getBonus() {
        return 0.15;
    }

    @Override
    public void setBonus(double valor) {
    }

}
