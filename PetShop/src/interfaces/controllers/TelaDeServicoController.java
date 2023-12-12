package interfaces.controllers;

import core.Menu;
import core.Pet;
import core.Singleton;
import core.Tutor;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

import java.util.List;

public class TelaDeServicoController {

    Menu menu = new Menu();
    Singleton singletonIntance = Singleton.getInstance();

    List<Tutor> listaDeTutores = singletonIntance.getListaDeTuTores();
    List<Pet> listaPets = singletonIntance.getListaDePet();

    @FXML
    private TextField CampoDeBusca;

    @FXML
    private TableColumn<Pet, String> PesoPetColumn;

    @FXML
    private TableColumn<Pet, String> PetNomeColumn;

    @FXML
    private TableColumn<Pet, String> RacaPetColumn;

    @FXML
    private TableView<Pet> tabelaDados;

    @FXML
    void BotaoDeBusca(ActionEvent event) {
        String cpfTutor = CampoDeBusca.getText();

        if (cpfTutor.isEmpty()) {
            exibirAlerta("Atenção", "Campo de Busca Vazio.", Alert.AlertType.INFORMATION);
        } else {
            Tutor tutorEncontrado = menu.BuscaTutor(cpfTutor);

            if (tutorEncontrado == null) {
                exibirAlerta("Atenção", "CPF Não Foi Encontrado.", Alert.AlertType.INFORMATION);
            } else {
                tabelaDados.getItems().clear();

                if (tutorEncontrado != null) {
                    List<Pet> petsDoTutor = tutorEncontrado.getListaDePets();

                    ObservableList<Pet> petObservableList = FXCollections.observableArrayList(petsDoTutor);

                    tabelaDados.setItems(petObservableList);

                    PetNomeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNome()));
                    RacaPetColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRaca()));
                    PesoPetColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPeso())));
                }
            }
        }
    }

    @FXML
    void BotaoVoltar(ActionEvent event) {
        try {
            // Obtém o botão que acionou o evento
            Button botaoClicado = (Button) event.getSource();

            // Carrega o FXML da próxima tela
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../layout/TelaInicialLayout.fxml"));
            Parent root = loader.load();

            // Cria a cena
            Scene cena = new Scene(root);

            // Obtém a referência ao palco atual (Stage) usando o botão clicado
            Stage palco = (Stage) botaoClicado.getScene().getWindow();

            // Define a nova cena no palco
            palco.setScene(cena);

            // Exibe a nova tela
            palco.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        // Configuração das colunas da tabela
        PetNomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        RacaPetColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRaca()));
        PesoPetColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPeso())));

        // Adiciona ouvinte de evento para clique duplo na tabela
        tabelaDados.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                // Obtém o item selecionado na tabela
                Pet PetSelecionado = tabelaDados.getSelectionModel().getSelectedItem();

                // Abre a janela de edição (método a ser implementado)
                abrirJanelaAgendamento(PetSelecionado);
            }
        });
    }

    private Tutor obterTutorAssociadoAoPet(Pet pet) {
        for (Tutor tutor : listaDeTutores) {
            if (tutor.getListaDePets().contains(pet)) {
                return tutor;
            }
        }
        return null; // ou lance uma exceção se necessário
    }

    private void abrirJanelaAgendamento(Pet pet) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../layout/TelaDeAgendamentoLayout.fxml"));
            Parent root = loader.load();

            // Obtém o controlador da janela de agendamento
            TelaDeAgendamentoController telaDeAgendamentoController = loader.getController();

            // Obtém o tutor associado ao pet usando o core.Singleton
            Tutor tutorAssociadoAoPet = obterTutorAssociadoAoPet(pet);

            // Passa o objeto core.Tutor e core.Pet para o controlador da janela de agendamento
            telaDeAgendamentoController.initData(tutorAssociadoAoPet, pet);

            // Cria a cena
            Scene cena = new Scene(root);

            // Cria um novo palco (Stage) para a janela de agendamento
            Stage palcoAgendamento = new Stage();
            palcoAgendamento.setScene(cena);

            // Define o título da janela de agendamento
            palcoAgendamento.setTitle("Agendar Serviço");

            // Exibe a janela de agendamento
            palcoAgendamento.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void exibirAlerta(String titulo, String mensgem, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensgem);
        alerta.showAndWait();
    }

}
