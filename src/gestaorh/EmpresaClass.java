package gestaorh;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
import java.util.ArrayList;

/**
 *
 * @author Pedro Grilo
 */
public class EmpresaClass implements Empresa {

    private ArrayList<Empregado> empregrado;

    public EmpresaClass() {
        empregrado = new ArrayList<>();
    }

    public void addEmpregadoNormal(String nome, int codigo, int day, int month, int year) {
        Empregado e = new NormalClass(nome, codigo, day, month, year);
        empregrado.add(e);
    }

    public void addEmpregadoComercial(String nome, int codigo, int day, int month, int year) {
        Empregado e = new ComercialClass(nome, codigo, day, month, year);
        empregrado.add(e);
    }

    public void addEmpregadoMotorista(String nome, int codigo, int day, int month, int year) {
        Empregado e = new MotoristaClass(nome, codigo, day, month, year);
        empregrado.add(e);
    }

    public void addEmpregadoGestor(String nome, int codigo, int day, int month, int year) {
        Empregado e = new GestorClass(nome, codigo, day, month, year);
        empregrado.add(e);
    }

}
