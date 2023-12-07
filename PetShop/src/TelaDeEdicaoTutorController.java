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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("TelaAdicaoPet.fxml"));
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
            palcoAdicaoPet.setTitle("Adicionar Pet");
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

        exibirAlerta("Salvando...", "Dados Salvo com sucesso!", Alert.AlertType.INFORMATION);

        tutorParaEdicao.setNome(NomeTutor.getText());
        tutorParaEdicao.setTelefone(TelefoneTutor.getText());
    }

    public void initData(Tutor tutor) {
        tutorParaEdicao = tutor;

        // Preenche os campos com os dados do Tutor
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("TelaEdicaoPet.fxml"));
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
