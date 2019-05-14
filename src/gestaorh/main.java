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

    private static void menuPrincipal() {
        System.out.println("\n\n|========== Menu Principal ==========|");
        System.out.println("1. Inserir uma nova ficha de empregado; \n" //done
                + "2. Verificar se existe algum empregado com um código dado como parâmetro; \n" // perguntar
                + "3. Obter a ficha de empregado, dado um código como parâmetro, se existir; \n" //done
                + "4. Dada uma lista de empregados contratados, inseri‐la no registo atual; \n" // perguntar / ficheiros
                + "5. Determinar o número atual de empregados de uma dada categoria; \n"
                + "6. Devolver o conjunto atual de fichas de empregados; \n"
                + "7. Devolver o conjunto atual de fichas de empregados, filtrados por categoria; \n"
                + "8. Calcular, a qualquer momento, o total de salários a pagar; \n"
                + "9. Calcular e apresentar os custos trimestrais, semestrais e anuais com salários, devendo ter em conta os subsídios de natal e de férias; \n"
                + "10. Guardar a lista atual de empregados num ficheiro de texto; \n"
                + "0. Sair");
    }

    private static void nrEmpregadosPorCategoria(UserInput ui, Empresa empresa) {
        String categoria = ui.lerFrase("Introduza uma categoria");
        System.out.println("Numero de Empregados na Categoria ->" + categoria);
        empresa.getNumeroEmpregados(categoria);
    }

    private static void obterEmpregadoByCodigo(UserInput ui, Empresa empresa) {
        int codigo = ui.lerInteiro("Introduza o código do empregado");
        System.out.println(empresa.getEmpregado(codigo));

    }

    private static int escolherOpcao(UserInput ui, Empresa empresa) {
        int lerOpcao = ui.lerInteiro("Escolha uma opção");
        escolherMenu(empresa, ui, lerOpcao);
        return lerOpcao;
    }

    private static void checkEmpregado(UserInput ui, Empresa empresa) {
        int codigo = ui.lerInteiro("Introduza um código");
        if (empresa.empregadoIsExists(codigo)) {
            System.out.println("Existe um empregado com o código :" + codigo);
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
        System.out.println(empresa.getTotalSalariosPagar());
    }

    private static void escolherMenu(Empresa empresa, UserInput ui, int opcao) {

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
                //to do demo!!
                break;
            case 10:
            //ficheiros to do!

        }

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
