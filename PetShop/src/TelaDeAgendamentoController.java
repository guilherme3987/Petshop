import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class TelaDeAgendamentoController {

    private Tutor tutorParaAgendamento;
    private Pet petParaAgendamento;

    @FXML
    private DatePicker CampoData;

    @FXML
    private TextField CampoNomePet;

    @FXML
    private TextField CampoNomeTutor;

    @FXML
    private TextField CampoPesoPet;

    @FXML
    private TextField CampoTotal;

    @FXML
    private CheckBox CheckBoxBanho;

    @FXML
    private CheckBox CheckBoxTosa;

    @FXML
    void initialize() {
        // Método chamado ao inicializar o controlador
        // Configura listeners para as CheckBoxes
        CheckBoxBanho.setOnAction(e -> atualizarValorTotal());
        CheckBoxTosa.setOnAction(e -> atualizarValorTotal());

        // Calcula e exibe o valor total quando a tela é carregada
        atualizarValorTotal();
    }

    private void atualizarValorTotal() {
        double precoServico = 0;

        // Verifica se Banho está selecionado
        if (CheckBoxBanho.isSelected()) {
            Servico banho = new Banho("Banho", 100, petParaAgendamento);
            precoServico += banho.calcServico(petParaAgendamento);
        }

        // Verifica se Tosa está selecionada
        if (CheckBoxTosa.isSelected()) {
            Servico tosa = new Tosa("Tosa", 100, petParaAgendamento);
            precoServico += tosa.calcServico(petParaAgendamento);
        }

        // Verifica se Tosa e Banho estão marcados juntos
        if (CheckBoxTosa.isSelected() && CheckBoxBanho.isSelected()) {
            Servico tosaEBanho = new BanhoETosa("Tosa e Banho", 150, petParaAgendamento);
            precoServico += tosaEBanho.calcServico(petParaAgendamento);
        }

        // Atualiza o campo total com o preço do serviço
        CampoTotal.setText(String.valueOf(precoServico));
    }

    @FXML
    void Confirmar(ActionEvent event) {
        // Adicione aqui a lógica para confirmar

        // Exibe uma notificação de sucesso
        exibirNotificacao("Agendamento realizado com sucesso!");

        // Obtém a referência ao palco atual (Stage) usando o botão clicado
        Stage palco = (Stage) ((Button) event.getSource()).getScene().getWindow();

        // Fecha a janela atual
        palco.close();
    }

    @FXML
    void Voltar(ActionEvent event) {
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

    private void exibirNotificacao(String mensagem) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Notificação");
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);

        // Adiciona um botão "OK" à notificação
        alerta.getButtonTypes().setAll(ButtonType.OK);

        // Exibe a notificação e aguarda até que o usuário a feche
        alerta.showAndWait();
    }

    public void initData(Tutor tutor, Pet pet) {
        tutorParaAgendamento = tutor;
        petParaAgendamento = pet;

        // Preenche os campos com os dados do Tutor e Pet
        CampoNomeTutor.setText(tutor.getNome());
        CampoNomePet.setText(pet.getNome());
        CampoPesoPet.setText(String.valueOf(pet.getPeso()));
    }
}
