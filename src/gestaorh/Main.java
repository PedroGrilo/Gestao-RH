package gestaorh;

import gestaorh.exceptions.GestaoErro;
import gestaorh.exceptions.GestaoException;

import java.io.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * @author creep
 */
public class Main {

    private static final String FILE_NAME = "empresa.dat";
    private static final String ERRO_LER_FICHEIRO = "Erro ao ler ficheiro!";
    private static final String FICHEIRO_VAZIO = "Ficheiro vazio.!";
    private static final String ERRO_SAVE = "Erro ao salvar ficheiro!";
    private static final String LOADING_FILE = "A ler ficheiro...";
    private static final String SUCCESS_SAVE = "Ficheiro salvo com sucesso.";
    private static final String MENU_EMPREGADO_TITULO = "\n\n\n|====== Escolher tipo de empregado a adicionar ======|";
    private static final String ESCOLHA_OPCAO = "Escolha uma opcão";
    private static final String INPUT_KM_PERCORRIDOS = " Introduza os kilometros percorridos";
    private static final String KM_PERCORRIDOS = "Kilometros percorridos adicionada com sucesso!";
    private static final String DATA_KM = "Data dos kilometros:";
    private static final String INPUT_VENDA = " Introduza um valor para a venda";
    private static final String DATA_VENDA = "Data da venda:";
    private static final String VENDA_SUCESSO = "Venda adicionada com sucesso!";
    private static final String ANO = " Ano";
    private static final String MES = " Mes";
    private static final String DIA = " Dia";
    private static final String CODIGO_EMPREGADO = " Introduza o codigo do empregado";
    private static final String CALCULAR_CUSTOS = "\n Custos Trimestrais, semestrais e anuais";
    private static final String INTRODUZIR_NOME = "Introduza o nome";
    private static final String INTRODUZIR_CODIGO = "Introduza um código";
    private static final String DATA_ENTRADA_EMP = "Data de Entrada na Empresa:";
    private static final String INTRODUZIR_CATEGORIA = "Introduza uma categoria";
    private static final String TOTAL_A_PAGAR = "Total de salários a pagar: ";
    private static final String TRIMESTRES_TITULO = "\n" + Periodos.TRIMESTRES.toString() + " : ";
    private static final String SEMESTRES_TITULO = "\n" + Periodos.SEMESTRES.toString() + " : ";
    private static final String ANUAIS_TITULO = "\n" + Periodos.ANUAIS.toString() + " : ";
    private static final String PICAR_DIA_TITULO = "Introduzir dia Trabalhado: ";
    private static final String NR_EMPREGADOS = "\nNumero de Empregados na categoria %s : %d \n";
    private static final String DIA_ADICIONADO_SUCESSO = "Dia adicionado com sucesso";
    private static final String EXISTE_CODIGO = "\nExiste um empregado com o código : %d";
    private static final String NAO_EXISTE_EMP = "Não existe nenhum empregado com o código dado como parâmetro.";

    /**
     * @param empresa
     * @param sourceFile
     * @throws FileNotFoundException
     */
    public static void saveEmpregadosFile(Empresa empresa, String sourceFile) throws FileNotFoundException {
        try {
            FileOutputStream out = new FileOutputStream(FILE_NAME);
            ObjectOutputStream oout = new ObjectOutputStream(out);
            oout.writeObject(empresa);
            oout.close();
            System.out.println(SUCCESS_SAVE);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @param empresa
     * @param file
     * @throws FileNotFoundException
     * @throws NoSuchElementException
     */
    public static Empresa lerEmpregadosFile() {
        Empresa temp = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME));
            temp = (Empresa) ois.readObject();
            System.out.println(SUCCESS_SAVE);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            return temp;
        }

    }

    public static void empregadosToBeAdd(Empresa empresa) {

        Random rand = new Random(System.currentTimeMillis());
        int mes = rand.nextInt(11) + 1;
        int dia = rand.nextInt(29) + 1;
        ArrayList<Empregado> empregadosTemp = new ArrayList<>();

        empregadosTemp.add(new NormalClass("Pedro", 1, dia, mes, 2014));
        empregadosTemp.add(new NormalClass("Márcia", 2, dia, mes, 2015));
        empregadosTemp.add(new NormalClass("José", 3, dia, mes, 2018));
        empregadosTemp.add(new ComercialClass("Patricia", 4, dia, mes, 2018));
        empregadosTemp.add(new ComercialClass("Diogo", 5, dia, mes, 2012));
        empregadosTemp.add(new ComercialClass("Alfredo", 6, dia, mes, 2010));
        empregadosTemp.add(new GestorClass("António", 7, dia, mes, 2015));
        empregadosTemp.add(new GestorClass("Beatriz", 8, dia, mes, 2016));
        empregadosTemp.add(new GestorClass("Ana", 9, dia, mes, 2017));
        empregadosTemp.add(new MotoristaClass("Andre", 10, dia, mes, 2010));
        empregadosTemp.add(new MotoristaClass("Manel", 11, dia, mes, 2017));
        empregadosTemp.add(new MotoristaClass("Josefino", 12, 5, 5, 2019));

        empresa.inserirEmpregados(empregadosTemp);
    }

    private static String firstLetterUP(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    private static void menuTipoEmpregado(UserInput ui, Empresa empresa) {
        System.out.println(MENU_EMPREGADO_TITULO);

        for (int i = 1; i <= Categorias.values().length; i++) {
            System.out.println(i + ". " + firstLetterUP(Categorias.values()[i - 1].toString()));
        }

        escolherOpcaoTipoEmpregado(ui, empresa);
    }

    private static int escolherOpcaoTipoEmpregado(UserInput ui, Empresa empresa) {
        int lerOpcao = ui.lerInteiro(ESCOLHA_OPCAO);
        escolherMenuTipoEmpregado(empresa, ui, lerOpcao);
        return lerOpcao;
    }

    private static void escolherMenuTipoEmpregado(Empresa empresa, UserInput ui, int opcao) {
        System.out.println(MENU_EMPREGADO_TITULO);

        int codigo = ui.lerInteiro(INTRODUZIR_CODIGO);
        empresa.checkCodigo(codigo);

        String nome = ui.lerFrase(INTRODUZIR_NOME);
        System.out.println(DATA_ENTRADA_EMP);

        int diaEntradaEmpresa = ui.lerInteiro(DIA);
        int mesEntradaEmpresa = ui.lerInteiro(MES);
        int anoEntradaEmpresa = ui.lerInteiro(ANO);

        Date dataAtual = new DateClass();
        Date dataCompare = new DateClass(diaEntradaEmpresa, mesEntradaEmpresa, anoEntradaEmpresa);

        if (!empresa.verifyDate(dataAtual, dataCompare)) {
            throw new GestaoException(GestaoErro.DATA_INVALIDA);
        }

        switch (opcao) {
            case 1:
                empresa.addEmpregadoNormal(nome, codigo, diaEntradaEmpresa, mesEntradaEmpresa, anoEntradaEmpresa);
                break;
            case 2:
                empresa.addEmpregadoComercial(nome, codigo, diaEntradaEmpresa, mesEntradaEmpresa, anoEntradaEmpresa);
                break;
            case 4:
                empresa.addEmpregadoGestor(nome, codigo, diaEntradaEmpresa, mesEntradaEmpresa, anoEntradaEmpresa);
                break;
            case 3:
                empresa.addEmpregadoMotorista(nome, codigo, diaEntradaEmpresa, mesEntradaEmpresa, anoEntradaEmpresa);
                break;

        }

    }

    private static void nrEmpregadosPorCategoria(UserInput ui, Empresa empresa) {
        String categoria = ui.lerFrase(INTRODUZIR_CATEGORIA);
        System.out.printf(NR_EMPREGADOS, categoria, empresa.getNumeroEmpregados(categoria));
    }

    private static void obterEmpregadoByCodigo(UserInput ui, Empresa empresa) {
        int codigo = ui.lerInteiro(CODIGO_EMPREGADO);
        System.out.println(empresa.getEmpregadoString(codigo));

    }

    private static void checkEmpregado(UserInput ui, Empresa empresa) {
        int codigo = ui.lerInteiro(INTRODUZIR_CODIGO);
        if (empresa.empregadoIsExists(codigo)) {
            System.out.printf(EXISTE_CODIGO, codigo);
        } else {
            System.out.println(NAO_EXISTE_EMP);
        }
    }

    private static void mostrarTodosEmpregados(Empresa empresa) {
        System.out.println(empresa.getTotalEmpregados());
    }

    private static void mostrarTodosEmpregadosFiltrados(Empresa empresa) {
        System.out.println(empresa.getTotalEmpregadoFiltrados());
    }

    private static void calcularSalariosTotais(Empresa empresa) {
        System.out.println(TOTAL_A_PAGAR);
        System.out.println(empresa.getTotalSalariosPagar() + "€");
    }

    private static void verificarDatas(Empresa empresa, int codigo, Date data1, Date data2) {
        Date dataEntradaEmpresa = empresa.getEmpregado(codigo).getDataEntradaEmpresa();
        if ((!empresa.verifyDate(data2, dataEntradaEmpresa)) || (!empresa.verifyDate(data1, data2))) {
            throw new GestaoException(GestaoErro.DATA_INVALIDA);
        }
    }

    private static void picarDia(UserInput ui, Empresa empresa) {
        System.out.println(PICAR_DIA_TITULO);
        int codigo = ui.lerInteiro(CODIGO_EMPREGADO);

        if (empresa.empregadoIsExists(codigo)) {
            int dia = ui.lerInteiro(DIA);
            int mes = ui.lerInteiro(MES);
            int ano = ui.lerInteiro(ANO);

            Date data = new DateClass(dia, mes, ano);
            Date dataAtual = new DateClass();

            verificarDatas(empresa, codigo, dataAtual, data);

            if (empresa.picarDiaTrabalho(codigo, ano, mes, dia)) {
                System.out.println(DIA_ADICIONADO_SUCESSO);
            }
        }
    }

    private static void apresentarCustos(Empresa empresa) {
        System.out.println(CALCULAR_CUSTOS);

        System.out.println(TRIMESTRES_TITULO);
        System.out.println(empresa.calcularCustos(Periodos.TRIMESTRES));

        System.out.println(SEMESTRES_TITULO);
        System.out.println(empresa.calcularCustos(Periodos.SEMESTRES));

        System.out.println(ANUAIS_TITULO);
        System.out.println(empresa.calcularCustos(Periodos.ANUAIS));
    }

    private static void adicionarVendasComercial(UserInput ui, Empresa empresa) {
        int codigo = ui.lerInteiro(CODIGO_EMPREGADO);
        empresa.checkCategoriaCodigo(codigo, Categorias.COMERCIAL.toString());

        double valor = ui.lerDouble(INPUT_VENDA);

        System.out.println(DATA_VENDA);
        int ano = ui.lerInteiro(ANO);
        int mes = ui.lerInteiro(MES);

        Date data = new DateClass(1, mes, ano);
        Date dataAtual = new DateClass();
        verificarDatas(empresa, codigo, dataAtual, data);

        if (empresa.adicionarBonus(codigo, valor, mes, ano)) {
            System.out.println(VENDA_SUCESSO);
        }
    }

    private static void adicionarKmPercorricos(UserInput ui, Empresa empresa) {
        int codigo = ui.lerInteiro(CODIGO_EMPREGADO);
        empresa.checkCategoriaCodigo(codigo, Categorias.MOTORISTA.toString());

        double valor = ui.lerDouble(INPUT_KM_PERCORRIDOS);

        System.out.println(DATA_KM);
        int ano = ui.lerInteiro(ANO);
        int mes = ui.lerInteiro(MES);

        Date data = new DateClass(1, mes, ano);
        Date dataAtual = new DateClass();
        verificarDatas(empresa, codigo, dataAtual, data);

        if (empresa.adicionarBonus(codigo, valor, mes, ano)) {
            System.out.println(KM_PERCORRIDOS);
        }
    }

    private static void menuPrincipal() {
        System.out.println("\n\n|========== Menu Principal ==========|\n"
                + "1. Menu Empregado\n"
                + "2. Menu Gestão\n"
                + "0. Sair");
    }

    private static void escolherMenuPrincipal(Empresa empresa, UserInput ui, int opcao) {
        switch (opcao) {
            case 1:
                menuEmpregado();
                escolherMenuEmpregado(empresa, ui);
                break;
            case 2:
                menuGestao();
                escolherMenu(empresa, ui);
                break;
        }
    }

    private static void menuEmpregado() {
        System.out.println("\n\n|========== Menu Empregado ==========|\n"
                + "1. Introduzir dia de trabalho;\n"
                + "2. Adicionar vendas (Exclusivo para Comerciais);\n"
                + "3. Adicionar kilometros percorridos (Exclusivo para Motoristas);\n"
                + "0. Sair");
    }

    private static void escolherMenuEmpregado(Empresa empresa, UserInput ui) {
        int opcao = ui.lerInteiro(ESCOLHA_OPCAO);
        switch (opcao) {
            case 1:
                picarDia(ui, empresa);
                break;
            case 2:
                adicionarVendasComercial(ui, empresa);
                break;
            case 3:
                adicionarKmPercorricos(ui, empresa);
                break;
        }
    }

    private static void menuGestao() {
        System.out.println("\n\n|========== Menu Gestão ==========|\n"
                + "1. Inserir um novo empregado; \n"
                + "2. Verificar se existe algum empregado com um código dado como parâmetro; \n"
                + "3. Obter a ficha de empregado, dado um código; \n"
                + "4. Dada uma lista de empregados contratados, inseri-la no registo atual; \n"
                + "5. Obter o número atual de empregados de uma dada categoria; \n"
                + "6. Obter o conjunto de fichas de empregados; \n"
                + "7. Obter o conjunto de fichas de empregados, filtrados por categoria; \n"
                + "8. Calcular o total de salários a pagar; \n"
                + "9. Calcular os custos trimestrais, semestrais e anuais com salários\n"
                + "10. Guardar a lista atual de empregados no ficheiro de texto; \n"
                + "0. Sair");
    }

    private static void escolherMenu(Empresa empresa, UserInput ui) {

        int opcao = ui.lerInteiro(ESCOLHA_OPCAO);

        switch (opcao) {
            case 1:
                menuTipoEmpregado(ui, empresa);
                break;
            case 2:
                checkEmpregado(ui, empresa);
                break;
            case 3:
                obterEmpregadoByCodigo(ui, empresa);
                break;
            case 4:
                empregadosToBeAdd(empresa);
                break;
            case 5:
                nrEmpregadosPorCategoria(ui, empresa);
                break;
            case 6:
                mostrarTodosEmpregados(empresa);
                break;
            case 7:
                mostrarTodosEmpregadosFiltrados(empresa);
                break;
            case 8:
                calcularSalariosTotais(empresa);
                break;
            case 9:
                apresentarCustos(empresa);
                break;
            case 10:
                try {
                    saveEmpregadosFile(empresa, FILE_NAME);
                    System.out.println(SUCCESS_SAVE);
                } catch (IOException e) {
                    System.out.println(ERRO_SAVE);
                }
                break;

        }
    }

    private static int escolherOpcao(UserInput ui, Empresa empresa) {
        int lerOpcao = ui.lerInteiro(ESCOLHA_OPCAO);
        escolherMenuPrincipal(empresa, ui, lerOpcao);
        return lerOpcao;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        int opcao = -1;
        UserInput ui = new UserInput();
        Empresa empresa = new EmpresaClass();
        try {
            empresa = lerEmpregadosFile();
        } catch (NoSuchElementException nF) {
            System.out.println(FICHEIRO_VAZIO);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        do {
            try {
                menuPrincipal();
                opcao = escolherOpcao(ui, empresa);
            } catch (GestaoException e) {
                System.out.println(e.getMessage());
            }
        } while (opcao != 0);

        ui.closeScanner();

    }

}
