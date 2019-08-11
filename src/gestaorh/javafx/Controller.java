package gestaorh.javafx;

import com.jfoenix.controls.*;
import gestaorh.*;
import gestaorh.exceptions.GestaoErro;
import gestaorh.exceptions.GestaoException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private Empresa empresa;

    @FXML
    private JFXButton botao_home;

    @FXML
    private JFXButton botao_emp;

    @FXML
    private JFXButton botao_gest;

    @FXML
    private ImageView closeButton;

    @FXML
    private Pane pane_home;

    @FXML
    private Pane pane_gestao;

    @FXML
    private Pane paneApresentarNumeroTotal;

    @FXML
    private BarChart<?, ?> barChart;

    @FXML
    private CategoryAxis barChartCatAxis;

    @FXML
    private NumberAxis barChartNumbAxis;

    @FXML
    private Pane panePesquisarEmp;

    @FXML
    private JFXTextField codigoPesquisarEmp;

    @FXML
    private JFXButton getEmpregadoCodigo;

    @FXML
    private Pane paneInserirVarios;

    @FXML
    private JFXTextField nomeEmpTemp;

    @FXML
    private JFXButton getEmpregadoCodigo1;

    @FXML
    private JFXComboBox<String> tipoEmpregadoComboTemp;

    @FXML
    private JFXDatePicker dataInserirEmpTemp;

    @FXML
    private JFXTextField codigoEmpTemp;

    @FXML
    private JFXListView<Empregado> listViewTemp;

    @FXML
    private JFXButton removerEmpregadoTemp;

    @FXML
    private JFXButton inserirEmpregadoTemp;

    @FXML
    private Pane paneShowAll;

    @FXML
    private JFXButton showFichaEmp;

    @FXML
    private JFXListView<Empregado> listViewAllEmp;

    @FXML
    private JFXTextField searchByName;

    @FXML
    private JFXToggleButton toggleBtnSearch;

    @FXML
    private JFXToggleButton toggleVendasDistancia;

    @FXML
    private Pane paneSalarios;

    @FXML
    private JFXRadioButton radioMensal;

    @FXML
    private ToggleGroup grupoAno;

    @FXML
    private JFXRadioButton radioTrimestral;

    @FXML
    private JFXRadioButton radioSemestral;

    @FXML
    private JFXComboBox comboBoxShowSalarios;

    @FXML
    private JFXComboBox comboBoxEscolherAno;

    @FXML
    private Label labelFixedCustos;

    @FXML
    private Label labelCustos;

    @FXML
    private JFXButton buttonCalcular;

    @FXML
    private Pane paneInserirFicha;

    @FXML
    private JFXComboBox<String> tipoEmpregadoCombo;

    @FXML
    private JFXTextField codigoInserirEmp;

    @FXML
    private JFXTextField nomeInserirEmp;

    @FXML
    private JFXDatePicker dataInserirEmp;

    @FXML
    private Pane paneSalarios1;

    @FXML
    private RadioButton radioInserirFicha;

    @FXML
    private ToggleGroup radioGestao;

    @FXML
    private RadioButton radioPesquisarEmp;

    @FXML
    private RadioButton radioInserirVarios;

    @FXML
    private RadioButton radioApresentarNumeroEmp;

    @FXML
    private RadioButton radioApresentarTodosEmp;

    @FXML
    private RadioButton radioShowSalarios;

    @FXML
    private Pane pane_empregados;

    @FXML
    private RadioButton intDiaTrabalho;

    @FXML
    private ToggleGroup menuEmpregados;

    @FXML
    private RadioButton adicionarBonus;

    @FXML
    private Pane paneBonus;

    @FXML
    private JFXTextField codigoEmpregadoVenda;

    @FXML
    private JFXTextField valorBonus;

    @FXML
    private JFXDatePicker diaBonus;

    @FXML
    private JFXButton botaoSubmeterBonus;

    @FXML
    private JFXTextField codigoEmpregadoKm;

    @FXML
    private JFXTextField valorKm;

    @FXML
    private Pane paneGestaoDiaTrabalho;

    @FXML
    private JFXTextField codigoEmpregado;

    @FXML
    private JFXDatePicker diaTrabalho;

    @FXML
    private JFXButton botaoSubmeter;


    private List<Empregado> empregadosTemp = new ArrayList<>();

    private ObservableList<Empregado> nomesObs;

    private static void verificarDatas(Empresa empresa, int codigo, Date data1, Date data2) {
        Date dataEntradaEmpresa = empresa.getEmpregado(codigo).getDataEntradaEmpresa();
        if ((!empresa.verifyDate(data2, dataEntradaEmpresa)) || (!empresa.verifyDate(data1, data2))) {
            throw new GestaoException(GestaoErro.DATA_INVALIDA);
        }
    }

    public void initialize(URL url, ResourceBundle rb) {
        empresa = new EmpresaClass();
        File f = new File("empresa.dat");
        try {
            if (f.exists()) {
                System.out.println("A ler ficheiro...");
                loadEmpresa();
            }
            loadCategorias();
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    private void loadEmpresa() {
        Empresa temp = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("empresa.dat"));
            temp = (Empresa) ois.readObject();
            System.out.println("Ficheiro lido com sucesso!");
        } catch (Exception e) {
            System.out.println(e);
        }
        empresa = temp;
    }

    private void saveFile() {
        try {
            FileOutputStream out = new FileOutputStream("empresa.dat");
            ObjectOutputStream oout = new ObjectOutputStream(out);
            oout.writeObject(empresa);
            oout.close();
            System.out.println("Ficheiro guardado com sucesso!");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void loadGraficos() {
        try {
            barChart.getData().clear();
            barChartCatAxis.setLabel("Tipo de Empregado");
            barChartNumbAxis.setLabel("Quantidade");
            barChart.setTitle("Numero de Empregados por Categoria");

            XYChart.Series normal = new XYChart.Series();
            normal.setName("Normal");

            XYChart.Series gestor = new XYChart.Series();
            gestor.setName("Gestor");

            XYChart.Series motorista = new XYChart.Series();
            motorista.setName("Motorista");

            XYChart.Series comercial = new XYChart.Series();
            comercial.setName("Comercial");

            normal.getData().add(new XYChart.Data<>("" + empresa.getNumeroEmpregados("Normal"), empresa.getNumeroEmpregados("Normal")));
            gestor.getData().add(new XYChart.Data<>("" + empresa.getNumeroEmpregados("Gestor"), empresa.getNumeroEmpregados("Gestor")));
            motorista.getData().add(new XYChart.Data<>("" + empresa.getNumeroEmpregados("Motorista"), empresa.getNumeroEmpregados("Motorista")));
            comercial.getData().add(new XYChart.Data<>("" + empresa.getNumeroEmpregados("Comercial"), empresa.getNumeroEmpregados("Comercial")));

            barChart.getData().addAll(comercial, gestor, motorista, normal);
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void getCustos() {
        try {
            labelFixedCustos.setVisible(true);
            int index = comboBoxShowSalarios.getSelectionModel().getSelectedIndex();
            int ano = Integer.parseInt(comboBoxEscolherAno.getSelectionModel().getSelectedItem().toString());
            double custos = -1;
            if (radioMensal.isSelected()) {
                custos = empresa.calcularCustos(ano, index, index + 1);
            }
            if (radioTrimestral.isSelected()) {
                switch (index) {
                    case 0:
                        custos = empresa.calcularCustos(ano, 0, 3); //calcular 1º Trimestre
                        break;
                    case 1:
                        custos = empresa.calcularCustos(ano, 3, 6); //calcular 2º Trimestre
                        break;
                    case 2:
                        custos = empresa.calcularCustos(ano, 6, 9); //calcular 3º Trimestre
                        break;
                    case 3:
                        custos = empresa.calcularCustos(ano, 9, 12); //calcular 4º Trimestre
                        break;
                    default:
                        custos = 0.0;

                }

            }
            if (radioSemestral.isSelected()) {
                switch (index) {
                    case 0:
                        custos = empresa.calcularCustos(ano, 0, 6); //calcular 1º Semestre
                        break;
                    case 1:
                        custos = empresa.calcularCustos(ano, 6, 12); //calcular 2º Semestre
                        break;
                    default:
                        custos = 0.0;
                }

            }
            labelCustos.setText(Math.ceil(custos) + "€");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void fillComboBox() {

        ObservableList<Integer> years = FXCollections.observableArrayList();

        int anoMenor = empresa.anoMenorEntradaEmpresa();

        Date dataAtual = new DateClass();

        for (int i = anoMenor; i <= dataAtual.getYear(); i++) {
            years.add(i);
        }

        comboBoxEscolherAno.setItems(years);

    }

    public void acaoRBShowSalarios(ActionEvent event) {
        try {
            comboBoxShowSalarios.setDisable(false);
            comboBoxEscolherAno.setDisable(false);
            buttonCalcular.setDisable(false);

            ObservableList<String> elementsOBL = FXCollections.observableArrayList();

            if (event.getSource() == radioMensal) {
                comboBoxShowSalarios.setPromptText("Introduza um Mês");
                elementsOBL.addAll("Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro");
            }
            if (event.getSource() == radioTrimestral) {
                comboBoxShowSalarios.setPromptText("Introduza um Trimestre");
                elementsOBL.addAll("1º Trimestre", "2º Trimestre", "3º Trimestre", "4º Trimestre");
            }
            if (event.getSource() == radioSemestral) {
                comboBoxShowSalarios.setPromptText("Introduza um Semestre");

                elementsOBL.addAll("1º Semestre", "2º Semestre");
            }

            comboBoxShowSalarios.setItems(elementsOBL);
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void putButtonDisable() {
        showFichaEmp.setDisable(false);
    }

    public void mostrarFicha() {
        Empregado e = listViewAllEmp.getSelectionModel().getSelectedItem();
        criarSceneUser(empresa.getEmpregadoString(e.getCodigo()));
    }

    public void showAllEmpListView() {
        ArrayList<Empregado> empregadosAtuais = empresa.getEmpregado();
        ObservableList<Empregado> observableListEmpregados;
        observableListEmpregados = FXCollections.observableArrayList(empregadosAtuais);
        ObservableList<Empregado> filteredEmpregados = FXCollections.observableArrayList();

        for (Empregado e : empregadosAtuais) {
            if (toggleBtnSearch.isSelected()) {
                if (e.getNome().toLowerCase().contains(searchByName.getText().toLowerCase())) {
                    filteredEmpregados.add(e);
                }
            } else {
                if (String.valueOf(e.getCodigo()).contains(searchByName.getText())) {
                    filteredEmpregados.add(e);
                }
            }

        }
        listViewAllEmp.setItems(filteredEmpregados);

    }

    public void removerEmpregadoTemp() {
        int index = listViewTemp.getSelectionModel().getSelectedIndex();
        if (index != -1) {
            empregadosTemp.remove(index);
        }
        nomesObs = FXCollections.observableArrayList(empregadosTemp);
        listViewTemp.setItems(nomesObs);

    }


    public void inserirEmpregadoTemp() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Gestão de Recursos Humanos");
        alert.setHeaderText("Erro");
        try {
            String nome = nomeEmpTemp.getText();
            String codigoText = codigoEmpTemp.getText();
            LocalDate data = dataInserirEmpTemp.getValue();
            checkNulls(nome);
            checkNulls(codigoText);
            checkDate(data);

            int codigo = Integer.parseInt(codigoText);
            empresa.checkCodigo(codigo);
            checkCodigoListaTemp(codigo);

            int diaEntradaEmpresa = data.getDayOfMonth();
            int mesEntradaEmpresa = data.getMonthValue();
            int anoEntradaEmpresa = data.getYear();

            compareDates(diaEntradaEmpresa, mesEntradaEmpresa, anoEntradaEmpresa);

            switch (tipoEmpregadoComboTemp.getValue()) {
                case "Normal":
                    empregadosTemp.add(new NormalClass(nome, codigo, diaEntradaEmpresa, mesEntradaEmpresa, anoEntradaEmpresa));
                    break;
                case "Comercial":
                    empregadosTemp.add(new ComercialClass(nome, codigo, diaEntradaEmpresa, mesEntradaEmpresa, anoEntradaEmpresa));
                    break;
                case "Gestor":
                    empregadosTemp.add(new GestorClass(nome, codigo, diaEntradaEmpresa, mesEntradaEmpresa, anoEntradaEmpresa));
                    break;
                case "Motorista":
                    empregadosTemp.add(new MotoristaClass(nome, codigo, diaEntradaEmpresa, mesEntradaEmpresa, anoEntradaEmpresa));
                    break;
            }
            LocalDate resetDate = null;
            dataInserirEmpTemp.setValue(resetDate);
            codigoEmpTemp.setText("");
            nomeEmpTemp.setText("");
            nomesObs = FXCollections.observableArrayList(empregadosTemp);
            listViewTemp.setItems(nomesObs);
        } catch (GestaoException e) {
            alert.setContentText(e.getMessage());
            alert.show();
        } catch (NumberFormatException e) {
            alert.setHeaderText("Erro");
            alert.setContentText("Letras não são permitidas.");
            alert.show();
        } catch (Exception e) {
            alert.setContentText("Verificar consola");
            System.out.println(e.getMessage());
            alert.show();
        }
    }

    private void checkCodigoListaTemp(int codigo) throws GestaoException {
        for (Empregado e : empregadosTemp) {
            if (e.getCodigo() == codigo) {
                throw new GestaoException(GestaoErro.CODIGO_EXISTE);
            }
        }
    }

    public void saveListView() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Gestão de Recursos Humanos");
        try {
            if (empregadosTemp.size() == 0) {
                throw new GestaoException(GestaoErro.CAMPOS_VAZIOS);
            }
            ArrayList<Empregado> temp = new ArrayList<>();
            temp.addAll(empregadosTemp);
            empresa.inserirEmpregados(temp);
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Successo");
            alert.setContentText("Empregados adicionados com sucesso!");
            empregadosTemp.clear();
            nomesObs = FXCollections.observableArrayList(empregadosTemp);
            listViewTemp.setItems(nomesObs);
        } catch (GestaoException e) {
            alert.setHeaderText("Erro");
            alert.setContentText(e.getMessage());
        } catch (NumberFormatException e) {
            alert.setHeaderText("Erro");
            alert.setContentText("Letras não são permitidas.");
        } catch (Exception e) {
            alert.setHeaderText("Erro");
            alert.setContentText("Verificar Consola");
            System.out.println(e.getMessage());
        } finally {
            alert.show();
        }
    }

    private void loadCategorias() {
        ArrayList<String> categorias = new ArrayList<>();
        ObservableList<String> observableListCat;
        categorias.add("Gestor");
        categorias.add("Comercial");

        categorias.add("Motorista");
        categorias.add("Normal");

        observableListCat = FXCollections.observableArrayList(categorias);
        tipoEmpregadoCombo.setItems(observableListCat);
        tipoEmpregadoComboTemp.setItems(observableListCat);
    }

    private void checkNulls(String field) {
        if (field == null || field.equals("")) {
            throw new GestaoException(GestaoErro.CAMPOS_VAZIOS);
        }
    }

    private void criarSceneUser(String text) {

        Pane root = new Pane();
        Label texto = new Label();
        texto.setText(text);

        root.getChildren().add(texto);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.DECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setScene(new Scene(root, 270, 165));
        stage.show();

    }

    public void getEmpregadoCodigo() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Gestão de Recursos Humanos");
        alert.setHeaderText("Erro");
        try {
            checkNulls(codigoPesquisarEmp.getText());
            int codigo = Integer.parseInt(codigoPesquisarEmp.getText());
            criarSceneUser(empresa.getEmpregadoString(codigo));
        } catch (GestaoException e) {
            alert.setContentText(e.getMessage());
            alert.show();
        } catch (NumberFormatException e) {
            alert.setHeaderText("Erro");
            alert.setContentText("Letras não são permitidas.");
            alert.show();
        } catch (Exception e) {
            alert.setContentText("Ver consola...");
            System.out.println(e.getMessage());
            alert.show();
        }
    }

    private void compareDates(int diaEntradaEmpresa, int mesEntradaEmpresa, int anoEntradaEmpresa) throws GestaoException {

        Date dataAtual = new DateClass();
        Date dataCompare = new DateClass(diaEntradaEmpresa, mesEntradaEmpresa, anoEntradaEmpresa);

        if (!empresa.verifyDate(dataAtual, dataCompare)) {
            throw new GestaoException(GestaoErro.DATA_INVALIDA);
        }
    }

    public void inserirEmpregado() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Gestão de Recursos Humanos");
        alert.setHeaderText("Erro");
        try {
            checkNulls(codigoInserirEmp.getText());
            checkNulls(nomeInserirEmp.getText());

            int codigo = Integer.parseInt(codigoInserirEmp.getText());
            empresa.checkCodigo(codigo);

            LocalDate data = dataInserirEmp.getValue();

            checkDate(data);

            String nome = nomeInserirEmp.getText();

            int diaEntradaEmpresa = data.getDayOfMonth();
            int mesEntradaEmpresa = data.getMonthValue();
            int anoEntradaEmpresa = data.getYear();

            compareDates(diaEntradaEmpresa, mesEntradaEmpresa, anoEntradaEmpresa);


            switch (tipoEmpregadoCombo.getValue()) {
                case "Normal":
                    empresa.addEmpregadoNormal(nome, codigo, diaEntradaEmpresa, mesEntradaEmpresa, anoEntradaEmpresa);
                    break;
                case "Comercial":
                    empresa.addEmpregadoComercial(nome, codigo, diaEntradaEmpresa, mesEntradaEmpresa, anoEntradaEmpresa);
                    break;
                case "Gestor":
                    empresa.addEmpregadoGestor(nome, codigo, diaEntradaEmpresa, mesEntradaEmpresa, anoEntradaEmpresa);
                    break;
                case "Motorista":
                    empresa.addEmpregadoMotorista(nome, codigo, diaEntradaEmpresa, mesEntradaEmpresa, anoEntradaEmpresa);
                    break;
            }
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Successo");
            alert.setContentText("Empregado adicionado com sucesso");
            codigoInserirEmp.setText("");
            nomeInserirEmp.setText("");


        } catch (GestaoException e) {
            alert.setContentText(e.getMessage());
            alert.show();
        } catch (NumberFormatException e) {
            alert.setHeaderText("Erro");
            alert.setContentText("Letras não são permitidas.");
        } catch (Exception e) {
            alert.setContentText("Verificar consola");
            System.out.println(e.getMessage());
        } finally {
            alert.show();
        }
    }


    private boolean checkDate(LocalDate date) {
        if (date == null) {
            throw new GestaoException(GestaoErro.DATA_INVALIDA);
        }
        return true;
    }

    @FXML
    public void submeterDiaTrabalho() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        try {
            alert.setTitle("Gestão de Recursos Humanos");
            alert.setHeaderText("Erro");
            checkNulls(codigoEmpregado.getText());
            int codigo = Integer.parseInt(codigoEmpregado.getText());
            if (empresa.empregadoIsExists(codigo)) {
                LocalDate dataFX = diaTrabalho.getValue();
                if (checkDate(dataFX)) {
                    int diaDeTrabalho = dataFX.getDayOfMonth();
                    int mesDeTrabalho = dataFX.getMonthValue();
                    int anoDeTrabalho = dataFX.getYear();

                    Date data = new DateClass(diaDeTrabalho, mesDeTrabalho, anoDeTrabalho);
                    Date dataAtual = new DateClass();

                    verificarDatas(empresa, codigo, dataAtual, data);

                    if (empresa.picarDiaTrabalho(codigo, anoDeTrabalho, mesDeTrabalho, diaDeTrabalho)) {
                        alert.setAlertType(Alert.AlertType.INFORMATION);
                        alert.setHeaderText("Successo");
                        alert.setContentText("Dia de Trabalho adicionados com sucesso!");
                    }
                }
            }
        } catch (GestaoException e) {
            alert.setHeaderText("Erro");
            alert.setContentText(e.getMessage());
        } catch (NumberFormatException e) {
            alert.setHeaderText("Erro");
            alert.setContentText("Letras não são permitidas.");
        } catch (Exception e) {
            alert.setHeaderText("Erro");
            alert.setContentText(e.getMessage());
        } finally {
            alert.show();
        }

    }

    public void mudarVendasDistancia() {
        if (!toggleVendasDistancia.isSelected()) {
            valorBonus.setPromptText("Introduzir o Valor da Venda (€)");
            diaBonus.setPromptText("Introduzir Mês da Venda");
        } else {
            valorBonus.setPromptText("Introduzir Distância percorrida (Km)");
            diaBonus.setPromptText("Introduzir Mês da Distância");
        }
    }

    public void adicionarBonus() {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Gestão de Recursos Humanos");

        try {
            checkNulls(codigoEmpregadoVenda.getText());
            checkNulls(valorBonus.getText());

            int codigo = Integer.parseInt(codigoEmpregadoVenda.getText());

            if (!toggleVendasDistancia.isSelected()) {
                empresa.checkCategoriaCodigo(codigo, Categorias.COMERCIAL.toString());
            } else {
                empresa.checkCategoriaCodigo(codigo, Categorias.MOTORISTA.toString());
            }

            double valor = Double.parseDouble(valorBonus.getText());

            LocalDate dataVendaFX = diaBonus.getValue();

            checkDate(dataVendaFX);

            int ano = dataVendaFX.getYear();
            int mes = dataVendaFX.getMonthValue();
            int dia = dataVendaFX.getDayOfMonth();

            Date data = new DateClass(dia, mes, ano);

            Date dataAtual = new DateClass();
            verificarDatas(empresa, codigo, dataAtual, data);

            if (empresa.adicionarBonus(codigo, valor, mes, ano)) {
                alert.setHeaderText("Sucesso");
                alert.setContentText("Bónus adicionado com sucesso!");
                alert.show();
                codigoEmpregadoVenda.setText("");
                valorBonus.setText("");

            }
        } catch (GestaoException e) {
            alert.setHeaderText("Erro");
            alert.setContentText(e.getMessage());
        } catch (NumberFormatException e) {
            alert.setHeaderText("Erro");
            alert.setContentText("Letras não são permitidas.");
        } catch (Exception e) {
            alert.setHeaderText("Erro");
            alert.setContentText(e.getMessage());
        } finally {
            alert.show();
        }

    }


    @FXML
    private void exitBotao() {
        saveFile();
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void acaoBotao(ActionEvent event) {

        if (event.getSource() == botao_home) {
            pane_home.toFront();
        }

        // Menu Gestão //

        if (event.getSource() == botao_gest) {
            pane_gestao.toFront();
        }
        if (event.getSource() == intDiaTrabalho) {
            paneGestaoDiaTrabalho.toFront();
        }
        if (event.getSource() == adicionarBonus) {
            paneBonus.toFront();
        }


        // Menu Empregados //

        {
            if (event.getSource() == botao_emp) {
                pane_empregados.toFront();
            }
        }
        if (event.getSource() == radioInserirFicha) {
            paneInserirFicha.toFront();
        }
        if (event.getSource() == radioPesquisarEmp) {
            panePesquisarEmp.toFront();
        }
        if (event.getSource() == radioInserirVarios) {
            paneInserirVarios.toFront();
        }
        if (event.getSource() == radioApresentarNumeroEmp) {
            loadGraficos();
            paneApresentarNumeroTotal.toFront();
        }
        if (event.getSource() == radioApresentarTodosEmp) {
            paneShowAll.toFront();
        }
        if (event.getSource() == radioShowSalarios) {
            fillComboBox();
            paneSalarios.toFront();
        }

    }


}
