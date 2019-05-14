package gestaorh;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Pedro Grilo
 */
public class EmpresaClass implements Empresa {

    private ArrayList<Empregado> empregado;
    private static double salarioPorDia = 10.0;
    private static double bonusPorAntiguidade = 1.05;//5% de bonus do salario por ano
    private static double subsidioAlimentacao = 4.79; //4.79€ por dia de trabalho
    private static double valorKm = 0.50; //50centimos por kilometro
    private static double percentagemVendas = 0.20;// percentagem
    private static double premioGestor = 1.15;  //15% premio gestor
    private static DecimalFormat decimal2casas = new DecimalFormat("#.##");

    public EmpresaClass() {
        empregado = new ArrayList<>();
    }

    public boolean checkCategoriaCodigo(int codigo, String categoria) throws GestaoException {
        for (Empregado e : empregado) {
            if (e.getCodigo() == codigo && getCategoria(e).toLowerCase().equals(categoria.toLowerCase())) {
                return true;
            }
        }
        throw new GestaoException(GestaoErro.CATEGORIA_INVALIDA);
    }

    public boolean adicionarBonus(int codigo, double valor) throws GestaoException {
        if (empregadoIsExists(codigo)) {
            for (Empregado e : empregado) {
                if (e.getCodigo() == codigo) {
                    e.setBonus(valor);
                    return true;
                }
            }
        } else {
            throw new GestaoException(GestaoErro.EMPREGADO_NOTFOUND);
        }
        return false;
    }

    private double calcularPrimeiroTrimestre(int ano) {
        double custos = 0.0;
        for (Empregado e : empregado) {
            for (int i = 1; i <= 3; i++) {
                custos += getSalarioEmp(e, i, ano);
            }
        }
        return custos;
    }

    private double calcularSegundoTrimestre(int ano) {
        double custos = 0.0;
        for (Empregado e : empregado) {
            for (int i = 4; i <= 6; i++) {
                custos += getSalarioEmp(e, i, ano);
            }
        }
        return custos;
    }

    private double calcularTerceiroTrimestre(int ano) {
        double custos = 0.0;
        for (Empregado e : empregado) {
            for (int i = 7; i <= 9; i++) {
                custos += getSalarioEmp(e, i, ano);
            }
        }
        return custos;
    }

    private double calcularQuartoTrimestre(int ano) {
        double custos = 0.0;
        for (Empregado e : empregado) {
            for (int i = 10; i <= 12; i++) {
                custos += getSalarioEmp(e, i, ano);
            }
        }
        return custos;
    }

    public String calcularTrimestreAno(int ano) {
        double prTrim = calcularPrimeiroTrimestre(ano);
        double segTrim = calcularSegundoTrimestre(ano);
        double terTrim = calcularTerceiroTrimestre(ano);
        double quarTrim = calcularQuartoTrimestre(ano);

        return "\n\n  Ano : " + ano
                + "\n\n   Primeiro Trimestre: " + decimal2casas.format(prTrim) + "€"
                + "\n   Segundo Trimestre: " + decimal2casas.format(segTrim) + "€"
                + "\n   Terceiro Trimestre: " + decimal2casas.format(terTrim) + "€"
                + "\n   Quarto Trimestre: " + decimal2casas.format(quarTrim) + "€";
    }

    private int anoMenorEntradaEmpresa() {
        Date dataAtual = new DateClass();
        int anoMenor = dataAtual.getYear();
        for (int i = 0; i < empregado.size(); i++) {
            if (empregado.get(i).getDataEntradaEmpresa().getYear() < anoMenor) {
                anoMenor = empregado.get(i).getDataEntradaEmpresa().getYear();
            }
        }
        return anoMenor;
    }

    public String calcularTrimestres() {

        Date dataAtual = new DateClass();
        int anoMenor = anoMenorEntradaEmpresa();

        String str = "";
        for (int i = anoMenor; i <= dataAtual.getYear(); i++) {
            str += calcularTrimestreAno(i);
        }
        return str;
    }

    public String calcularSemestreAno(int ano) {
        double prSem = calcularPrimeiroTrimestre(ano) + calcularSegundoTrimestre(ano);
        double segSem = calcularTerceiroTrimestre(ano) + calcularQuartoTrimestre(ano);

        return "\n\n  Ano : " + ano
                + "\n\n    Primeiro Semestre: " + decimal2casas.format(prSem) + "€"
                + "\n    Segundo Semestre: " + decimal2casas.format(segSem) + "€";
    }

    public String calcularSemestres() {

        Date dataAtual = new DateClass();
        int anoMenor = anoMenorEntradaEmpresa();
        String str = "";
        for (int i = anoMenor; i <= dataAtual.getYear(); i++) {
            str += calcularSemestreAno(i);
        }
        return str;
    }

    public String calcularCustosAnual(int ano) {
        double custos = calcularPrimeiroTrimestre(ano) + calcularSegundoTrimestre(ano) + calcularTerceiroTrimestre(ano) + calcularQuartoTrimestre(ano);

        return "\n\n  Total no ano(" + ano + ") :" + decimal2casas.format(custos) + "€";
    }

    public String calcularCustosAnuais() {

        Date dataAtual = new DateClass();
        int anoMenor = anoMenorEntradaEmpresa();
        String str = "";
        for (int i = anoMenor; i <= dataAtual.getYear(); i++) {
            str += calcularCustosAnual(i);
        }
        return str;
    }

    public boolean picarDiaTrabalho(int codigo, int ano, int mes, int dia) throws GestaoException {
        for (Empregado e : empregado) {
            if (e.getCodigo() == codigo) {
                e.setDiaTrabalho(dia, mes, ano);
                return true;
            }
        }
        throw new GestaoException(GestaoErro.EMPREGADO_NOTFOUND);

    }

    public double getSubsidioAlimentacao() {
        return subsidioAlimentacao;
    }

    public double getSalarioPorDia() {
        return salarioPorDia;
    }

    public double getSalarioBase(Empregado e, int mes, int ano) {
        return (e.getDiasTrabalho(mes, ano) * salarioPorDia) + (e.getDiasTrabalho(mes, ano) * getSubsidioAlimentacao()) + (e.getAnosTrabalho() * bonusPorAntiguidade);
    }

    public double getTotalSalariosPagar() {
        double salario = 0.0;

        for (Empregado e : empregado) {
            salario += salarioTotalEmp(e);
        }
        return salario;
    }

    private double salarioTotalEmp(Empregado e) {

        double salarioEmp = (e.getDiasTrabalhoTotal() * getSalarioPorDia()) + (e.getDiasTrabalhoTotal() * getSubsidioAlimentacao()) + (e.getAnosTrabalho() * bonusPorAntiguidade);

        if (e instanceof Gestor) {
            return salarioEmp * premioGestor; // salario base + (15% do salario base)
        }
        if (e instanceof Motorista) {
            return salarioEmp + (e.getBonus() * valorKm); // salario base + (kmPercorridos * valor por Kilometro
        }
        if (e instanceof Comercial) {
            return salarioEmp + (e.getBonus() * percentagemVendas); //sario base + (valor das Vendas * percentagem Vendas)
        }
        if (e instanceof Normal) {
            return salarioEmp;
        }
        throw new GestaoException(GestaoErro.EMPREGADO_NOTFOUND);
    }

    public double getSalarioEmp(Empregado e, int mes, int ano) {
        return salarioPorCategoria(e, mes, ano);
    }

    private double salarioPorCategoria(Empregado e, int mes, int ano) throws GestaoException {

        if (!empregadoIsExists(e)) {
            throw new GestaoException(GestaoErro.EMPREGADO_NOTFOUND);
        }
        if (e instanceof Gestor) {
            return getSalarioBase(e, mes, ano) * premioGestor; // salario base + (15% do salario base)
        }
        if (e instanceof Motorista) {
            return getSalarioBase(e, mes, ano) + (e.getBonus() * valorKm); // salario base + (kmPercorridos * valor por Kilometro
        }
        if (e instanceof Comercial) {
            return getSalarioBase(e, mes, ano) + (e.getBonus() * percentagemVendas); //sario base + (valor das Vendas * percentagem Vendas)
        }
        if (e instanceof Normal) {
            return getSalarioBase(e, mes, ano);
        }
        throw new GestaoException(GestaoErro.DATA_INVALIDA);
    }

    public String getTotalEmpregados() {
        String str = "";
        for (Empregado e : empregado) {
            str += printEmpregado(e);
        }
        return str;
    }

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

    public boolean empregadoIsExists(int codigo) {
        for (Empregado e : empregado) {
            if (e.getCodigo() == codigo) {
                return true;
            }
        }
        return false;
    }

    public boolean empregadoIsExists(Empregado emp) {
        for (Empregado e : empregado) {
            if (e.getCodigo() == emp.getCodigo()) {
                return true;
            }
        }
        return false;
    }

    private void checkCodigo(int codigo) throws GestaoException {
        for (Empregado e : empregado) {
            if (e.getCodigo() == codigo) {
                throw new GestaoException(GestaoErro.CODIGO_EXISTE);
            }
        }
    }

    private void checkCategoria(String categoria) throws GestaoException {
        System.out.println(categoria + " - testes");
        if (!categoria.equals("normal") && !categoria.equals("comercial") && !categoria.equals("motorista") && !categoria.equals("gestor")) {
            throw new GestaoException(GestaoErro.CATEGORIA_NOTFOUND);
        }
    }

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

    public String getEmpregado(int codigo) throws GestaoException {
        for (Empregado e : empregado) {
            if (e.getCodigo() == codigo) {
                return printEmpregado(e);
            }
        }
        throw new GestaoException(GestaoErro.EMPREGADO_NOTFOUND);

    }

    public void addEmpregadoNormal(String nome, int codigo, int day, int month, int year) {
        checkCodigo(codigo);
        Empregado e = new NormalClass(nome, codigo, day, month, year);
        empregado.add(e);

    }

    public void addEmpregadoComercial(String nome, int codigo, int day, int month, int year) {
        checkCodigo(codigo);
        Empregado e = new ComercialClass(nome, codigo, day, month, year);
        empregado.add(e);
    }

    public void addEmpregadoMotorista(String nome, int codigo, int day, int month, int year) {
        checkCodigo(codigo);
        Empregado e = new MotoristaClass(nome, codigo, day, month, year);
        empregado.add(e);
    }

    public void addEmpregadoGestor(String nome, int codigo, int day, int month, int year) {
        checkCodigo(codigo);
        Empregado e = new GestorClass(nome, codigo, day, month, year);
        empregado.add(e);
    }

    public String printEmpregado(Empregado empregado) {

        LocalDate today = LocalDate.now();
        LocalDate mesAnterior = today.minusMonths(1);
        String str;
        str = "\nFicha do Empregado:\n Categoria: " + getCategoria(empregado)
                + "\n Código: " + empregado.getCodigo()
                + "\n Nome: " + empregado.getNome()
                + "\n Data de Entrada: " + empregado.getDataEntradaEmpresa()
                + "\n Salário (Ultimo mês): " + decimal2casas.format(getSalarioBase(empregado, mesAnterior.getMonthValue(), mesAnterior.getYear())) + "€"
                + "\n Salario Total: " + decimal2casas.format((salarioTotalEmp(empregado))) + "€"
                + "\n\n|------------------------------------------------------|\n";
        return str;
    }

}
