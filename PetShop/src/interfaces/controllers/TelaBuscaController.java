package interfaces.controllers;

import java.util.List;

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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.stage.StageStyle;

public class TelaBuscaController {

    Menu menu = new Menu();

    Singleton singletonIntance = Singleton.getInstance();

    List<Tutor> listaDeTutores = singletonIntance.getListaDeTuTores();
    List<Pet> listaPets = singletonIntance.getListaDePet();

    @FXML
    private TextField CampoDeBusca;

    @FXML
    private TableColumn<Tutor, String> QuantidadePetColumn;

    @FXML
    private TableColumn<Tutor, String> TelefoneColumn;

    @FXML
    private TableColumn<Tutor, String> TutorNomeColumn;

    @FXML
    private TableView<Tutor> tabelaDados;

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
                    ObservableList<Tutor> tutorEncontradoList = FXCollections.observableArrayList();
                    tutorEncontradoList.add(tutorEncontrado);

                    tabelaDados.setItems(tutorEncontradoList);

                    TutorNomeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNome()));
                    QuantidadePetColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getListaDePets().size())));
                    TelefoneColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTelefone()));
                }
            }
        }
    }

    @FXML
    void BotaoMostrarTodos(ActionEvent event) {
        tabelaDados.getItems().clear();

        System.out.println("tutor" + listaDeTutores);
        System.out.println("pet" + singletonIntance.getListaDePet());

        ObservableList<Tutor> tutorEncontradoList = FXCollections.observableArrayList();

        for (Tutor busca : listaDeTutores) {

            busca.getNome();
            busca.getListaDePets();
            System.out.println("Tutot : " + tutorEncontradoList.add(busca));
        }

        if (tutorEncontradoList.isEmpty()) {
            exibirAlerta("Atenção", "Não há Items Cadastrados Para Exibir.", Alert.AlertType.INFORMATION);
        } else {
            tabelaDados.setItems(tutorEncontradoList);

            TutorNomeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNome()));

            // Configuração da coluna QuantidadePetColumn
            QuantidadePetColumn.setCellValueFactory(cellData -> {
                int quantidadePets = cellData.getValue().getListaDePets().size();
                return new SimpleStringProperty(Integer.toString(quantidadePets));
            });

            TelefoneColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTelefone()));
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
        TutorNomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        QuantidadePetColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getListaDePets().size())));
        TelefoneColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTelefone()));

        // Adiciona ouvinte de evento para clique duplo na tabela
        tabelaDados.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                // Obtém o item selecionado na tabela
                Tutor tutorSelecionado = tabelaDados.getSelectionModel().getSelectedItem();

                // Abre a janela de edição (método a ser implementado)
                abrirJanelaEdicao(tutorSelecionado);
            }
        });
    }

    // Método para abrir a janela de edição
    private void abrirJanelaEdicao(Tutor tutor) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../layout/TelaDeEdicaoTutorLayout.fxml"));
            Parent root = loader.load();

            // Obtém o controlador da janela de edição
            TelaDeEdicaoTutorController telaDeEdicaoTutorController = loader.getController();

            // Passa o objeto core.Tutor para o controlador da janela de edição
            telaDeEdicaoTutorController.initData(tutor);

            // Cria a cena
            Scene cena = new Scene(root);

            // Cria um novo palco (Stage) para a janela de edição
            Stage palcoEdicao = new Stage();

            // Configura o palco como uma janela modal (bloqueia interação com a janela principal)
            palcoEdicao.initModality(Modality.APPLICATION_MODAL);

            // Configura o estilo da janela (sem decorações padrão)
            palcoEdicao.initStyle(StageStyle.UNDECORATED);

            // Define o título da janela de edição
            palcoEdicao.setTitle("Editar core.Tutor");

            // Define a cena no palco
            palcoEdicao.setScene(cena);

            // Exibe a janela de edição
            palcoEdicao.showAndWait();
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
