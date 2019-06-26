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

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private static Empresa empresa;


    @FXML
    private JFXButton botao_home;

    @FXML
    private JFXButton botao_emp;

    @FXML
    private JFXButton botao_gest;

    @FXML
    private ImageView closeButton;

    @FXML
    private Pane pane_empregados;

    @FXML
    private RadioButton intDiaTrabalho;

    @FXML
    private ToggleGroup menuEmpregados;

    @FXML
    private RadioButton adicionarVendas;

    @FXML
    private RadioButton adicionarKilometros;

    @FXML
    private Pane pane_gestao1;

    @FXML
    private JFXTextField codigoEmpregado;

    @FXML
    private JFXDatePicker diaTrabalho;

    @FXML
    private JFXButton botaoSubmeter;

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
    private JFXComboBox comboBoxShowSalarios;

    @FXML
    private JFXComboBox comboBoxEscolherAno;

    @FXML
    private Label labelCustos;

    private List<Empregado> empregadosTemp = new ArrayList<>();

    private ObservableList<Empregado> nomesObs;

    public void initialize(URL url, ResourceBundle rb) {
        empresa = new EmpresaClass();
        empresa.addEmpregadoComercial("Pedro", 1, 29, 8, 2000);
        empresa.addEmpregadoNormal("Amor", 2, 8, 1, 2000);
        empresa.picarDiaTrabalho(2, 2019, 6, 26);
        empresa.picarDiaTrabalho(2, 2019, 5, 26);
        empresa.picarDiaTrabalho(2, 2019, 7, 26);
        loadCategorias();
    }

    public void loadGraficos() {
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

    }


    public void getCustos() {

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
        comboBoxShowSalarios.setDisable(false);
        comboBoxEscolherAno.setDisable(false);
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
    }

    public void showAllEmpListView() {
        ArrayList<Empregado> empregadosAtuais = empresa.getEmpregado();
        ObservableList<Empregado> observableListEmpregados;
        observableListEmpregados = FXCollections.observableArrayList(empregadosAtuais);
        ObservableList<Empregado> filteredEmpregados = FXCollections.observableArrayList();


        for (Empregado e : empregadosAtuais) {
            if (e.getNome().contains(searchByName.getText())) {
                filteredEmpregados.add(e);
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
            ArrayList<Empregado> temp = new ArrayList<>();
            temp.addAll(empregadosTemp);
            empresa.inserirEmpregados(temp);
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Successo");
            alert.setContentText("Empregados adicionados com sucesso!");
            empregadosTemp.clear();
            nomesObs = FXCollections.observableArrayList(empregadosTemp);
            listViewTemp.setItems(nomesObs);
        } catch (Exception e) {
            alert.setHeaderText("Erro");
            alert.setContentText("Verificar Consola");
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
        checkNulls(codigoPesquisarEmp.getText());

        int codigo = Integer.parseInt(codigoPesquisarEmp.getText());
        criarSceneUser(empresa.getEmpregadoString(codigo));

    }

    private void compareDates(int diaEntradaEmpresa, int mesEntradaEmpresa, int anoEntradaEmpresa) {
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
        } catch (Exception e) {
            alert.setContentText("Verificar consola");
            System.out.println(e.getMessage());
        } finally {
            alert.show();
        }
    }

    @FXML
    public void submeterDiaTrabalho() {


        try {
            System.out.println("Dia adicionado com sucesso");

            checkNulls(codigoEmpregado.getText());

            int codigo = Integer.parseInt(codigoEmpregado.getText());


            LocalDate data = diaTrabalho.getValue();

            int diaDeTrabalho = data.getDayOfMonth();
            int mesDeTrabalho = data.getMonthValue();
            int anoDeTrabalho = data.getYear();

            empresa.picarDiaTrabalho(codigo, anoDeTrabalho, mesDeTrabalho, diaDeTrabalho);
            empresa.calcularCustos(Periodos.ANUAIS);
        } catch (GestaoException e) {
            //TODO: colocar alertBox
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

    @FXML
    private void exitBotao() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void acaoBotao(ActionEvent event) {
        if (event.getSource() == botao_home) {
            pane_home.toFront();
            //System.out.println("Página Home");
        }
        if (event.getSource() == botao_emp) {
            pane_empregados.toFront();
            //System.out.println("Página Empregados");
        }
        if (event.getSource() == botao_gest) {
            pane_gestao.toFront();
            //System.out.println("Página Gestão");
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
