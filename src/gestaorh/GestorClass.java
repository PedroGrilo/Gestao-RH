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
public class GestorClass extends EmpregadoAbsClass implements Gestor  {

    private double premio;

    public GestorClass(String nome, int codigo, int day, int month, int year) {
        super(nome, codigo, day, month, year);
        premio = 0.15;
    }

    public double getPremio() {
        return premio;
    }

    public void setPremio(double premio) {
        this.premio = premio;
    }
     @Override
    public double getSalario(){
        return 12;
    }
}
