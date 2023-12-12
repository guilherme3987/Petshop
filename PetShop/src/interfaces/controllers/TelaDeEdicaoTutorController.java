package interfaces.controllers;

import core.Menu;
import core.Pet;
import core.Tutor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.input.MouseButton;

import java.io.IOException;
import java.util.Arrays;

public class TelaDeEdicaoTutorController {

    private Tutor tutorParaEdicao;

    Menu menu = new Menu();

    @FXML
    private TextField CPFTutor;

    @FXML
    private TableColumn<Pet, String> NomePetColumn;

    @FXML
    private TextField NomeTutor;

    @FXML
    private TableColumn<Pet, String> PesoPetColumn;

    @FXML
    private TableColumn<Pet, String> RacaPetColumn;

    @FXML
    private TextField TelefoneTutor;

    @FXML
    private TableView<Pet> tabelaDados;

    private ObservableList<Pet> PetsEncontradoList;

    @FXML
    void AdicionarPets(ActionEvent event) {
        try {
            // Obtém o controlador da janela de adição de pet
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../layout/TelaAdicaoPet.fxml"));
            Parent root = loader.load();
            TelaDeAdicaoPetController telaDeAdicaoPetController = loader.getController();

            // Chama o método initData passando o tutorParaEdicao
            telaDeAdicaoPetController.initData(tutorParaEdicao);

            // Cria a cena
            Scene cena = new Scene(root);

            // Cria um novo palco (Stage) para a janela de adição de pet
            Stage palcoAdicaoPet = new Stage();
            palcoAdicaoPet.initModality(Modality.APPLICATION_MODAL);
            palcoAdicaoPet.initStyle(StageStyle.UNDECORATED);
            palcoAdicaoPet.setTitle("Adicionar core.Pet");
            palcoAdicaoPet.setScene(cena);

            // Exibe a janela de adição de pet
            palcoAdicaoPet.showAndWait();
        } catch (IOException e) {
            exibirAlerta("Erro", "Erro ao abrir a janela de adição do Pet.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void BotaoMostrarTodosPet(ActionEvent event) {

        PetsEncontradoList.clear();
        PetsEncontradoList.addAll(tutorParaEdicao.getListaDePets());
        tabelaDados.setItems(PetsEncontradoList);
    }

    @FXML
    void VoltarTela(ActionEvent event) {
        fecharJanela();
    }

    @FXML
    void SalvarDadosTutor(ActionEvent event) {

        if (NomeTutor.getText().trim().isEmpty() || TelefoneTutor.getText().trim().isEmpty()) {
            exibirAlerta("Erro", "Todos os campos são obrigatórios", Alert.AlertType.ERROR);
        }

        if (!CadastrarTPController.validadorTelefone.validarTelefone(TelefoneTutor.getText())) {
            exibirAlerta("Erro", "Número de telefone inválido!", Alert.AlertType.ERROR);
            return;
        }

        exibirAlerta("Salvando...", "Dados Salvo com sucesso!", Alert.AlertType.INFORMATION);

        tutorParaEdicao.setNome(NomeTutor.getText());
        tutorParaEdicao.setTelefone(TelefoneTutor.getText());
    }

    public void initData(Tutor tutor) {
        tutorParaEdicao = tutor;

        // Preenche os campos com os dados do core.Tutor
        CPFTutor.setText(tutor.getCpf());
        NomeTutor.setText(tutor.getNome());
        TelefoneTutor.setText(tutor.getTelefone());
    }

    @FXML
    void initialize() {

        PetsEncontradoList = FXCollections.observableArrayList();
        // Configuração das colunas da tabela
        NomePetColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        PesoPetColumn.setCellValueFactory(new PropertyValueFactory<>("peso"));
        RacaPetColumn.setCellValueFactory(new PropertyValueFactory<>("raca"));

        tabelaDados.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                Pet petSelecionado = tabelaDados.getSelectionModel().getSelectedItem();

                abrirJanelaEdicaoPet(petSelecionado);
            }
        });
    }

    private void abrirJanelaEdicaoPet(Pet pet) {
        try {
            // Obtém o controlador da janela de edição de pet
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../layout/TelaEdicaoPet.fxml"));
            Parent root = loader.load();
            TelaDeEdicaoPetController telaDeEdicaoPetController = loader.getController();
            telaDeEdicaoPetController.initData(pet, tutorParaEdicao);

            // Cria a cena
            Scene cena = new Scene(root);

            // Cria um novo palco (Stage) para a janela de edição de pet
            Stage palcoEdicaoPet = new Stage();
            palcoEdicaoPet.initModality(Modality.APPLICATION_MODAL);
            palcoEdicaoPet.initStyle(StageStyle.UNDECORATED);
            palcoEdicaoPet.setTitle("Editar Pet");
            palcoEdicaoPet.setScene(cena);

            // Exibe a janela de edição de pet
            palcoEdicaoPet.showAndWait();
        } catch (IOException e) {
            exibirAlerta("Erro", "Erro ao abrir a janela de edição do Pet.", Alert.AlertType.ERROR);
        }
    }


    public class validadorTelefone {

        public static boolean validarTelefone(String telefone) {
            // Remove caracteres n numericos
            String numeroLimpo = telefone.replaceAll("[^0-9]", "");

            // Verifica se o num de telefone tem o formato BR
            if (!numeroLimpo.matches("\\d{10,11}")) {
                return false;
            }

            // Extrai o DDD
            String ddd = numeroLimpo.substring(0, 2);

            // DDDs BR VALIDOS
            String[] dddsValidos = {"11", "12", "13", "14", "15", "16", "17", "18", "19", "21", "22", "24", "27", "28", "31", "32", "33", "34", "35", "37", "38", "41", "42", "43", "44", "45", "46", "47", "48", "49", "51", "53", "54", "55", "61", "62", "63", "64", "65", "66", "67", "68", "69", "71", "73", "74", "75", "77", "79", "81", "82", "83", "84", "85", "86", "87", "88", "89", "91", "92", "93", "94", "95", "96", "97", "98", "99"};

            //  DDD é válido
            if (!Arrays.asList(dddsValidos).contains(ddd)) {
                return false;
            }

            return true;
        }
    }

    private void fecharJanela() {
        Stage palco = (Stage) CPFTutor.getScene().getWindow();

        // Fecha a janela de edição
        palco.close();
    }

    private void exibirAlerta(String titulo, String mensagem, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }

}
