package gestaorh;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Pedro Grilo
 */
public class EmpresaClass implements Empresa {

    private ArrayList<Empregado> empregado;
    private double salarioPorDia;

    public EmpresaClass(double salarioPorDia) {
        empregado = new ArrayList<>();
        this.salarioPorDia = salarioPorDia;
    }

    public double getSalarioPorDia() {
        return salarioPorDia;
    }

    public double getTotalSalariosPagar() {
        double salario = 0.0;
        for (Empregado e : empregado) {
            salario += (e.getDiasTrabalhoTotal() * salarioPorDia) + (e.getDiasTrabalhoTotal() * e.getSubsidioAlimentacao());
        }   //falta bonus por antiguidade
        return salario;
    }

    public double getSalario(Empregado e, int mes, int ano) {
        return (e.getDiasTrabalho(mes, ano) * salarioPorDia) + (e.getDiasTrabalho(mes, ano) * e.getSubsidioAlimentacao());
    }

    public String getTotalEmpregados() {
        String str = "";
        for (Empregado e : empregado) {
            str += printEmpregado(e);
            str += "\n--------------------------||--------------------------\n";
        }
        return str;
    }

    public String getTotalEmpregadoFiltrados() {
        String str = "Empregados da Categoria Normal : ";

        for (Empregado e : empregado) {
            if (e instanceof Normal) {
                str += printEmpregado(e);
            }
        }

        str += "\nEmpregados da Categoria Comercial : ";

        for (Empregado e : empregado) {
            if (e instanceof Comercial) {
                str += printEmpregado(e);
            }
        }

        str += "\nEmpregados da Categoria Gestor : ";

        for (Empregado e : empregado) {
            if (e instanceof Gestor) {
                str += printEmpregado(e);
            }
        }

        str += "\nEmpregados da Categoria Motorista : ";

        for (Empregado e : empregado) {
            if (e instanceof Motorista) {
                str += printEmpregado(e);
            }
        }

        return str;
    }

    public boolean empregadoIsExists(int codigo) {
        for (Empregado e : empregado) {
            if (e.getCodigo() == codigo) {
                return true;
            }
        }
        return false;
    }

    public void checkCodigo(int codigo) throws GestaoException {
        for (Empregado e : empregado) {
            if (e.getCodigo() == codigo) {
                throw new GestaoException(GestaoErro.CODIGO_EXISTE);
            }
        }
    }

    public int getNumeroEmpregados(String categoria) {
        int counter = 0;

        for (Empregado e : empregado) {
            if (getCategoria(e).toLowerCase() == categoria.toLowerCase()) {
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
        str = "\nFicha do Empregado:\n Categoria: " + getCategoria(empregado) + "\n Código: " + empregado.getCodigo() + "\n Nome: " + empregado.getNome() + "\n Data de Entrada: " + empregado.getDataEntradaEmpresa() + "\n Salário (Ultimo mês): " + getSalario(empregado, mesAnterior.getMonthValue(), mesAnterior.getYear());
        return str;
    }

}
