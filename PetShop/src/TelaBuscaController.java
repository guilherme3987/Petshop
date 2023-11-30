import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

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

        Tutor tutorEncontrado = menu.BuscaTutor(cpfTutor);

        tabelaDados.getItems().clear();

        if (tutorEncontrado != null) {
            ObservableList<Tutor> tutorEncontradoList = FXCollections.observableArrayList();
            tutorEncontradoList.add(tutorEncontrado);

            tabelaDados.setItems(tutorEncontradoList);

            TutorNomeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNome()));
            QuantidadePetColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getListaDePets().size())));
            TelefoneColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTelefone())); // Corrigido para obter o telefone do tutor
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

        tabelaDados.setItems(tutorEncontradoList);

        TutorNomeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNome()));

        // Configuração da coluna QuantidadePetColumn
        QuantidadePetColumn.setCellValueFactory(cellData -> {
            int quantidadePets = cellData.getValue().getListaDePets().size();
            return new SimpleStringProperty(Integer.toString(quantidadePets));
        });

        TelefoneColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTelefone()));
    }

    @FXML
    void BotaoVoltar(ActionEvent event) {
        try {
            // Obtém o botão que acionou o evento
            Button botaoClicado = (Button) event.getSource();

            // Carrega o FXML da próxima tela
            FXMLLoader loader = new FXMLLoader(getClass().getResource("TelaInicialLayout.fxml"));
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("TelaDeAlteracaoLayout.fxml"));
            Parent root = loader.load();

            // Obtém o controlador da janela de edição
            TelaDeEdicaoController telaDeEdicaoController = loader.getController();

            // Passa o objeto Tutor para o controlador da janela de edição
            telaDeEdicaoController.initData(tutor);

            // Cria a cena
            Scene cena = new Scene(root);

            // Cria um novo palco (Stage) para a janela de edição
            Stage palcoEdicao = new Stage();
            palcoEdicao.setScene(cena);

            // Define o título da janela de edição
            palcoEdicao.setTitle("Editar Tutor");

            // Exibe a janela de edição
            palcoEdicao.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
