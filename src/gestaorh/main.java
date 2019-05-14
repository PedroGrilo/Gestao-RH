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
public class main {

    private static void menuTipoEmpregado(UserInput ui, Empresa empresa) {
        System.out.println("\n\n\n======Escolher tipo de empregado a adiconar ======");
        System.out.println("1. Normal");
        System.out.println("2. Comercial");
        System.out.println("3. Motorista");
        System.out.println("4. Gestor");
        escolherOpcaoTipoEmpregado(ui, empresa);
    }

    private static int escolherOpcaoTipoEmpregado(UserInput ui, Empresa empresa) {
        int lerOpcao = ui.lerInteiro("Escolha uma opção");
        escolherMenuTipoEmpregado(empresa, ui, lerOpcao);
        return lerOpcao;
    }

    private static void escolherMenuTipoEmpregado(Empresa empresa, UserInput ui, int opcao) {
        System.out.print("\n");
        String nome = ui.lerFrase("Introduza o nome");
        int codigo = ui.lerInteiro("Introduza um código");
        System.out.println("Data de Entrada na Empresa:");
        int diaDeNascimento = ui.lerInteiro("Dia");
        int mesDeNascimento = ui.lerInteiro("Mês");
        int anoDeNascimento = ui.lerInteiro("Ano");

        switch (opcao) {
            case 1:
                empresa.addEmpregadoNormal(nome, codigo, diaDeNascimento, mesDeNascimento, anoDeNascimento);
                break;
            case 2:
                empresa.addEmpregadoComercial(nome, codigo, diaDeNascimento, mesDeNascimento, anoDeNascimento);
                break;
            case 3:
                empresa.addEmpregadoMotorista(nome, codigo, diaDeNascimento, mesDeNascimento, anoDeNascimento);
                break;
            case 4:
                empresa.addEmpregadoGestor(nome, codigo, diaDeNascimento, mesDeNascimento, anoDeNascimento);
                break;
        }

    }

    private static void nrEmpregadosPorCategoria(UserInput ui, Empresa empresa) {
        String categoria = ui.lerFrase("Introduza uma categoria");
        System.out.println("Numero de Empregados na Categoria -> " + categoria + " : " + empresa.getNumeroEmpregados(categoria));
    }

    private static void obterEmpregadoByCodigo(UserInput ui, Empresa empresa) {
        int codigo = ui.lerInteiro("Introduza o código do empregado");
        System.out.println(empresa.getEmpregado(codigo));

    }

    private static void checkEmpregado(UserInput ui, Empresa empresa) {
        int codigo = ui.lerInteiro("Introduza um código");
        if (empresa.empregadoIsExists(codigo)) {
            System.out.println("Existe um empregado com o código : " + codigo);
        } else {
            System.out.println("Não existe nenhum empregado com o código dado como parâmetro.");
        }
    }

    private static void mostrarTodosEmpregados(Empresa empresa) {
        System.out.println(empresa.getTotalEmpregados());
    }

    private static void mostrarTodosEmpregadosFiltrados(Empresa empresa) {
        System.out.println(empresa.getTotalEmpregadoFiltrados());
    }

    private static void calcularSalariosTotais(Empresa empresa) {
        System.out.println("Total de salários a pagar: ");
        System.out.println(empresa.getTotalSalariosPagar() + "€");
    }

    private static void picarDia(UserInput ui, Empresa empresa) {
        System.out.println("Introduzir dia Trabalhado: ");
        int codigo = ui.lerInteiro(" Introduza o codigo do empregado");
        int dia = ui.lerInteiro(" Introduza o dia");
        int mes = ui.lerInteiro(" Introduza o mes");
        int ano = ui.lerInteiro(" Introduza o ano");

        if (empresa.picarDiaTrabalho(codigo, ano, mes, dia)) {
            System.out.println("Dia adicionado com sucesso!");
        }
    }

    private static void apresentarCustos(Empresa empresa) {
        System.out.println("\n Custos Trimestrais, semestrais e anuais");
        System.out.println("\n TRIMESTRES:");
        System.out.println(empresa.calcularTrimestres());
        System.out.println("\n SEMESTRES:");
        System.out.println(empresa.calcularSemestres());
        System.out.println("\n ANUAL:");
        System.out.println(empresa.calcularCustosAnuais());
    }

    private static void adicionarVendasComercial(UserInput ui, Empresa empresa) {
        int codigo = ui.lerInteiro(" Introduza o codigo do empregado");
        double valor = ui.lerDouble("Introduza um valor para a venda");

        empresa.checkCategoriaCodigo(codigo, "Comercial");

        if (empresa.adicionarBonus(codigo, valor)) {
            System.out.println("Venda adicionada com sucesso!");
        };
    }

    private static void adicionarKmPercorricos(UserInput ui, Empresa empresa) {
        int codigo = ui.lerInteiro(" Introduza o codigo do empregado");
        double valor = ui.lerDouble("Introduza os kilometros percorridos");

        empresa.checkCategoriaCodigo(codigo, "Motorista");

        if (empresa.adicionarBonus(codigo, valor)) {
            System.out.println("Kilometros percorridos adicionada com sucesso!");
        };
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
        int opcao = ui.lerInteiro("Escolha uma opção");
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
                + "4. Inserir no ficheiro de texto empregados; \n"
                + "5. Obter o número atual de empregados de uma dada categoria; \n"
                + "6. Obter o conjunto de fichas de empregados; \n"
                + "7. Obter o conjunto de fichas de empregados, filtrados por categoria; \n"
                + "8. Calcular o total de salários a pagar; \n"
                + "9. Calcular os custos trimestrais, semestrais e anuais com salários\n"
                + "10. Guardar a lista atual de empregados no ficheiro de texto; \n"
                + "0. Sair");
    }

    private static void escolherMenu(Empresa empresa, UserInput ui) {

        int opcao = ui.lerInteiro("Escolha uma opção");

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
                //to do
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
                //ficheiros to do!
                break;
        }

    }

    private static int escolherOpcao(UserInput ui, Empresa empresa) {
        int lerOpcao = ui.lerInteiro("Escolha uma opção");
        escolherMenuPrincipal(empresa, ui, lerOpcao);
        return lerOpcao;
    }

    public static void main(String[] args) {
        int opcao = -1;
        UserInput ui = new UserInput();
        Empresa empresa = new EmpresaClass();
        do {
            try {
                menuPrincipal();
                opcao = escolherOpcao(ui, empresa);
            } catch (GestaoException e) {
                System.out.println(e.getMessage());
            }
        } while (opcao != 0);
    }

}
