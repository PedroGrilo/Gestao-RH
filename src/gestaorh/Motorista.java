package gestaorh;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 

/**
 *
 * @author Pedro Grilo
 */
public interface Motorista extends Empregado {

    double getKmPercorridos();

    void setKmPercorridos(double kmPercorridos);
    
    double getSalario();
}
