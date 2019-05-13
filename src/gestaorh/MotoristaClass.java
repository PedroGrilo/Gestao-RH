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
public class MotoristaClass extends EmpregadoAbsClass implements Motorista {

    private double kmPercorridos;

    public MotoristaClass(String nome, int codigo, int day, int month, int year) {
        super(nome, codigo, day, month, year);
        kmPercorridos = 0;
    }

    public double getKmPercorridos() {
        return kmPercorridos;
    }

    public void setKmPercorridos(double kmPercorridos) {
        this.kmPercorridos = kmPercorridos;
    }

}
