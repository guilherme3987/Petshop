package interfaces.controllers;

import core.Pet;
import core.Singleton;
import core.Tutor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Optional;

public class TelaDeEdicaoController {

    private Tutor tutorParaEdicao;
    private int indicePetAtual;

    @FXML
    private TextField CPFTutor;

    @FXML
    private TextField NomeTutor;

    @FXML
    private TextField TelefoneTutor;

    @FXML
    private TextField NomePet;

    @FXML
    private TextField EspeciePet;

    @FXML
    private TextField PesoPet;

    @FXML
    private TextField RacaPet;


    @FXML
    void SalvarDados(ActionEvent event) {

        tutorParaEdicao.setCpf(CPFTutor.getText());
        tutorParaEdicao.setNome(NomeTutor.getText());
        tutorParaEdicao.setTelefone(TelefoneTutor.getText());

        // Atualiza os dados do core.Pet associado ao core.Tutor
        if (!tutorParaEdicao.getListaDePets().isEmpty()) {
            Pet petAtual = tutorParaEdicao.getListaDePets().get(indicePetAtual);
            petAtual.setNome(NomePet.getText());
            petAtual.setEspecie(EspeciePet.getText());
            petAtual.setRaca(RacaPet.getText());
            try {
                double peso = Double.parseDouble(PesoPet.getText());
                petAtual.setPeso(peso);
            } catch (NumberFormatException e) {
                // Tratar caso a conversão não seja bem-sucedida
                System.err.println("Erro ao converter o peso para um número decimal: " + e.getMessage());
            }
        }

        // Salva as alterações no core.Singleton
        Singleton.getInstance().salvarAlteracoesTutor(tutorParaEdicao);

        fecharJanela();

    }

    @FXML
    void ExcluirTodosDados(ActionEvent event) {
        // Verifica se o tutor para edição não é nulo
        if (tutorParaEdicao != null) {
            // Exibe um alerta de confirmação
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmação");
            alert.setHeaderText(null);
            alert.setContentText("Tem certeza que deseja excluir os dados do tutor?");

            // Obtém a resposta do usuário
            Optional<ButtonType> result = alert.showAndWait();

            // Se o usuário confirmar, exclua os dados do tutor
            if (result.isPresent() && result.get() == ButtonType.OK) {
                // Remove o tutor da lista de tutores
                Singleton.getInstance().getListaDeTuTores().remove(tutorParaEdicao);

                fecharJanela();
            }
        } else {
            // Se o tutor para edição for nulo, exibe um alerta
            exibirAlerta("Aviso", "Não há tutor para excluir.", Alert.AlertType.WARNING);
        }
    }

    // Método para inicializar os dados do core.Tutor na tela de edição
    public void initData(Tutor tutor) {

        tutorParaEdicao = tutor;

        CPFTutor.setText(tutor.getCpf());
        NomeTutor.setText(tutor.getNome());
        TelefoneTutor.setText(tutor.getTelefone());

        if (!tutor.getListaDePets().isEmpty()) {
            Pet primeiroPet = tutor.getListaDePets().get(0);
            NomePet.setText(primeiroPet.getNome());
            EspeciePet.setText(primeiroPet.getEspecie());
            RacaPet.setText(primeiroPet.getRaca());
            PesoPet.setText(String.valueOf(primeiroPet.getPeso()));
            indicePetAtual = 0;
        }
    }

    private void exibirAlerta(String titulo, String mensagem, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }

    // Método para fechar a janela de edição
    private void fecharJanela() {
        Stage palco = (Stage) CPFTutor.getScene().getWindow();

        // Fecha a janela de edição
        palco.close();
    }
}
