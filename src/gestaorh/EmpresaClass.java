package gestaorh;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */

import gestaorh.exceptions.GestaoErro;
import gestaorh.exceptions.GestaoException;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * @author Pedro Grilo
 */
public class EmpresaClass implements Empresa, Serializable {

    private static double salarioPorDia = 10.0;
    private static double bonusPorAntiguidade = 0.05;//5% de bonus do salario por ano
    private static double subsidioAlimentacao = 4.79; //4.79€ por dia de trabalho
    private static double valorKm = 0.50; //50centimos por kilometro
    private static double percentagemVendas = 0.20;// percentagem
    private static double premioGestor = 1.15;  //15% premio gestor
    private static DecimalFormat decimal2casas = new DecimalFormat("#.##");
    private ArrayList<Empregado> empregado;

    /**
     *
     */
    public EmpresaClass() {
        empregado = new ArrayList<>();
    }

    /**
     * @return
     */
    public ArrayList<Empregado> getEmpregado() {
        return empregado;
    }

    /**
     * @param empregado
     */
    public void setEmpregado(ArrayList<Empregado> empregado) {
        this.empregado = empregado;
    }

    /**
     * @param empregados
     * @throws GestaoException
     */
    public void inserirEmpregados(ArrayList<Empregado> empregados) throws GestaoException {

        int counter = 0;

        for (Empregado eToAdd : empregados) {
            if (!empregadoIsExists(eToAdd.getCodigo())) {
                empregado.add(eToAdd);
            } else {
                counter++;
            }
        }

        if (counter > 0) {
            throw new GestaoException(GestaoErro.ERRO_ADICIONAR_EMP);
        }
    }

    /**
     * @param codigo
     * @param categoria
     * @return
     * @throws GestaoException
     */
    public boolean checkCategoriaCodigo(int codigo, String categoria) throws GestaoException {
        for (Empregado e : empregado) {
            if (e.getCodigo() == codigo && getCategoria(e).toLowerCase().equals(categoria.toLowerCase())) {
                return true;
            }
        }
        throw new GestaoException(GestaoErro.CATEGORIA_INVALIDA);
    }

    /**
     * @param codigo
     * @param valor
     * @param mes
     * @param ano
     * @return
     * @throws GestaoException
     */
    @Override
    public boolean adicionarBonus(int codigo, double valor, int mes, int ano) throws GestaoException {

        if (empregadoIsExists(codigo)) {
            for (Empregado e : empregado) {
                if (e.getCodigo() == codigo) {
                    e.setBonus(valor, mes, ano);
                    return true;
                }
            }
        } else {
            throw new GestaoException(GestaoErro.EMPREGADO_NOTFOUND);
        }
        return false;
    }

    @Override
    public int anoMenorEntradaEmpresa() {
        Date dataAtual = new DateClass();
        int anoMenor = dataAtual.getYear();
        for (int i = 0; i < empregado.size(); i++) {
            if (empregado.get(i).getDataEntradaEmpresa().getYear() < anoMenor) {
                anoMenor = empregado.get(i).getDataEntradaEmpresa().getYear();
            }
        }
        return anoMenor;
    }

    public double calcularCustos(int ano, int posInicial, int posFinal) {
        double custos = 0.0;
        for (Empregado e : empregado) {
            for (int i = posInicial; i < posFinal; i++) {
                custos += salarioPorCategoria(e, i + 1, ano);
            }
        }
        return custos;
    }

    /**
     * @param ano
     * @return
     */
    public String calcularTrimestreAno(int ano) {
        double prTrim = calcularCustos(ano, 0, 3); //calcular 1º Trimestre
        double segTrim = calcularCustos(ano, 3, 6); //calcular 2º Trimestre
        double terTrim = calcularCustos(ano, 6, 9); //calcular 3º Trimestre
        double quarTrim = calcularCustos(ano, 9, 12); //calcular 4º Trimestre

        return "\n\n  Ano : " + ano
                + "\n\n   Primeiro Trimestre: " + decimal2casas.format(prTrim) + "€"
                + "\n   Segundo Trimestre: " + decimal2casas.format(segTrim) + "€"
                + "\n   Terceiro Trimestre: " + decimal2casas.format(terTrim) + "€"
                + "\n   Quarto Trimestre: " + decimal2casas.format(quarTrim) + "€";
    }

    /**
     * @param ano
     * @return
     */
    public String calcularSemestreAno(int ano) {
        double prSem = calcularCustos(ano, 0, 3) + calcularCustos(ano, 3, 6);
        double segSem = calcularCustos(ano, 6, 9) + calcularCustos(ano, 9, 12);

        return "\n\n  Ano : " + ano
                + "\n\n    Primeiro Semestre: " + decimal2casas.format(prSem) + "€"
                + "\n    Segundo Semestre: " + decimal2casas.format(segSem) + "€";
    }

    /**
     * @param ano
     * @return
     */
    public String calcularCustosAnual(int ano) {
        double custos = calcularCustos(ano, 0, 3) + calcularCustos(ano, 3, 6) + calcularCustos(ano, 6, 9) + calcularCustos(ano, 9, 12);

        return "\n\n  Total no ano(" + ano + ") :" + decimal2casas.format(custos) + "€";
    }

    /**
     * @param periodo
     * @return
     */
    public String calcularCustos(Periodos periodo) {
        Date dataAtual = new DateClass();
        int anoMenor = anoMenorEntradaEmpresa();

        String str = "";
        for (int i = anoMenor; i <= dataAtual.getYear(); i++) {
            if (periodo.equals(Periodos.TRIMESTRES)) {
                str += calcularTrimestreAno(i);
            }
            if (periodo.equals(Periodos.SEMESTRES)) {
                str += calcularSemestreAno(i);
            }
            if (periodo.equals(Periodos.ANUAIS)) {
                str += calcularCustosAnual(i);
            }
        }
        return str;
    }

    /**
     * @param codigo
     * @param ano
     * @param mes
     * @param dia
     * @return
     * @throws GestaoException
     */
    public boolean picarDiaTrabalho(int codigo, int ano, int mes, int dia) throws GestaoException {
        for (Empregado e : empregado) {
            if (e.getCodigo() == codigo) {
                e.setDiaTrabalho(dia, mes, ano);
                return true;
            }
        }
        throw new GestaoException(GestaoErro.EMPREGADO_NOTFOUND);

    }

    /**
     * @param e
     * @param mes
     * @param ano
     * @return
     */
    public double getSalarioBase(Empregado e, int mes, int ano) {
        return ((e.getDiasTrabalho(mes, ano) * salarioPorDia) + (e.getDiasTrabalho(mes, ano) * getSubsidioAlimentacao())) * ((e.getAnosTrabalho() * bonusPorAntiguidade) + 1);
    }

    private double salarioPorCategoria(Empregado e, int mes, int ano) throws GestaoException {

        if (!empregadoIsExists(e)) {
            throw new GestaoException(GestaoErro.EMPREGADO_NOTFOUND);
        }
        if (e instanceof Gestor) {
            return getSalarioBase(e, mes, ano) * premioGestor; // salario base + (15% do salario base)
        }
        if (e instanceof Motorista) {
            return getSalarioBase(e, mes, ano) + (e.getBonus(mes, ano) * valorKm); // salario base + (kmPercorridos * valor por Kilometro
        }
        if (e instanceof Comercial) {
            return getSalarioBase(e, mes, ano) + (e.getBonus(mes, ano) * percentagemVendas); //salario base + (valor das Vendas * percentagem Vendas)
        }
        if (e instanceof Normal) {
            return getSalarioBase(e, mes, ano);
        }

        throw new GestaoException(GestaoErro.DATA_INVALIDA);
    }

    /**
     * @return
     */
    public String getTotalSalariosPagar() {
        double salario = 0.0;
        Date dataAtual = new DateClass();
        for (Empregado e : empregado) {
            salario += salarioPorCategoria(e, dataAtual.getMonth(), dataAtual.getYear());
        }

        return decimal2casas.format(salario);
    }

    /**
     * @return
     */
    public String getTotalEmpregados() {
        String str = "";
        for (Empregado e : empregado) {
            str += printEmpregado(e);
        }
        return str;
    }

    /**
     * @return
     */
    public String getTotalEmpregadoFiltrados() {
        String normal = "\nEmpregados da Categoria Normal : \n";
        String comercial = "\nEmpregados da Categoria Comercial : \n";
        String gestor = "\nEmpregados da Categoria Gestor : \n";
        String motorista = "\nEmpregados da Categoria Motorista : \n";

        for (Empregado e : empregado) {
            if (e instanceof Normal) {
                normal += printEmpregado(e);
            }
            if (e instanceof Comercial) {
                comercial += printEmpregado(e);
            }
            if (e instanceof Gestor) {
                gestor += printEmpregado(e);
            }
            if (e instanceof Motorista) {
                motorista += printEmpregado(e);
            }
        }

        return normal + comercial + gestor + motorista;
    }

    /**
     * @param codigo
     * @return
     * @throws GestaoException
     */
    public boolean empregadoIsExists(int codigo) throws GestaoException {
        for (Empregado e : empregado) {
            if (e.getCodigo() == codigo) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param emp
     * @return
     * @throws GestaoException
     */
    public boolean empregadoIsExists(Empregado emp) throws GestaoException {
        for (Empregado e : empregado) {
            if (e.getCodigo() == emp.getCodigo()) {
                return true;
            }
        }
        throw new GestaoException(GestaoErro.EMPREGADO_NOTFOUND);

    }

    /**
     * @param codigo
     * @throws GestaoException
     */
    public void checkCodigo(int codigo) throws GestaoException {
        for (Empregado e : empregado) {
            if (e.getCodigo() == codigo) {
                throw new GestaoException(GestaoErro.CODIGO_EXISTE);
            }
        }
    }

    private void checkCategoria(String categoria) throws GestaoException {
        if (!categoria.equals(Categorias.NORMAL.toString()) && !categoria.equals(Categorias.COMERCIAL.toString()) && !categoria.equals(Categorias.MOTORISTA.toString()) && !categoria.equals(Categorias.GESTOR.toString())) {
            throw new GestaoException(GestaoErro.CATEGORIA_NOTFOUND);
        }
    }

    /**
     * @param categoria
     * @return
     */
    public int getNumeroEmpregados(String categoria) {
        int counter = 0;
        checkCategoria(categoria.toLowerCase());
        for (Empregado e : empregado) {
            if (getCategoria(e).toLowerCase().equals(categoria.toLowerCase())) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * @param e
     * @return
     * @throws GestaoException
     */
    public String getCategoria(Empregado e) throws GestaoException {
        if (e instanceof Normal) {
            return "Normal";
        } else if (e instanceof Comercial) {
            return "Comercial";
        } else if (e instanceof Motorista) {
            return "Motorista";
        } else if (e instanceof Gestor) {
            return "Gestor";
        } else {
            throw new GestaoException(GestaoErro.ERRO_CATEGORIA);
        }
    }

    /**
     * @param codigo
     * @return
     * @throws GestaoException
     */
    public String getEmpregadoString(int codigo) throws GestaoException {
        for (Empregado e : empregado) {
            if (e.getCodigo() == codigo) {
                return printEmpregado(e);
            }
        }
        throw new GestaoException(GestaoErro.EMPREGADO_NOTFOUND);
    }

    /**
     * @param codigo
     * @return
     */
    public Empregado getEmpregado(int codigo) {
        for (Empregado e : empregado) {
            if (e.getCodigo() == codigo) {
                return e;
            }
        }
        throw new GestaoException(GestaoErro.EMPREGADO_NOTFOUND);
    }

    /**
     * @param nome
     * @param codigo
     * @param day
     * @param month
     * @param year
     */
    public void addEmpregadoNormal(String nome, int codigo, int day, int month, int year) {
        Empregado e = new NormalClass(nome, codigo, day, month, year);
        empregado.add(e);
    }

    /**
     * @param nome
     * @param codigo
     * @param day
     * @param month
     * @param year
     */
    public void addEmpregadoComercial(String nome, int codigo, int day, int month, int year) {
        Empregado e = new ComercialClass(nome, codigo, day, month, year);
        empregado.add(e);
    }

    /**
     * @param nome
     * @param codigo
     * @param day
     * @param month
     * @param year
     */
    public void addEmpregadoMotorista(String nome, int codigo, int day, int month, int year) {
        Empregado e = new MotoristaClass(nome, codigo, day, month, year);
        empregado.add(e);
    }

    /**
     * @param nome
     * @param codigo
     * @param day
     * @param month
     * @param year
     */
    public void addEmpregadoGestor(String nome, int codigo, int day, int month, int year) {
        Empregado e = new GestorClass(nome, codigo, day, month, year);
        empregado.add(e);
    }

    /**
     * @param data1
     * @param data2
     * @return
     */
    public boolean verifyDate(Date data1, Date data2) {
        if (data1.getYear() < data2.getYear()) {
            return false;
        }
        if (data1.getMonth() < data2.getMonth() && data1.getYear() <= data2.getYear()) {
            return false;
        }

        if (data1.getDay() < data2.getDay() && data1.getMonth() <= data2.getMonth() && data1.getYear() <= data2.getYear()) {
            return false;
        }
        return true;
    }

    /**
     * @return
     */
    public double getSubsidioAlimentacao() {
        return subsidioAlimentacao;
    }

    /**
     * @return
     */
    public double getSalarioPorDia() {
        return salarioPorDia;
    }

    /**
     * @param empregado
     * @return
     */
    public String printEmpregado(Empregado empregado) {

        LocalDate today = LocalDate.now();
        LocalDate mesAnterior = today.minusMonths(1);
        String str;
        str = "\nFicha do Empregado:\n Categoria: " + getCategoria(empregado)
                + "\n Código: " + empregado.getCodigo()
                + "\n Nome: " + empregado.getNome()
                + "\n Data de Entrada: " + empregado.getDataEntradaEmpresa()
                + "\n Salário (Último mes): " + decimal2casas.format(salarioPorCategoria(empregado, mesAnterior.getMonthValue(), mesAnterior.getYear())) + "€"
                + "\n\n|------------------------------------------------------|\n";
        return str;
    }

}
