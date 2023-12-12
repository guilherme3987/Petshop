package interfaces.controllers;

import core.Pet;
import core.Tutor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TelaDeAdicaoPetController {

    private Tutor tutorParaEdicao;

    @FXML
    private TextField EspeciePet;

    @FXML
    private TextField NomePet;

    @FXML
    private TextField PesoPet;

    @FXML
    private TextField RacaPet;

    public void initData(Tutor tutor) {
        tutorParaEdicao = tutor;
    }

    @FXML
    void AdicionarNovoPet(ActionEvent event) {

        // Verifica se todos os campos foram preenchidos
        if (validarCampos()) {
            // Cria um novo pet com os dados fornecidos

            String nomePet = NomePet.getText();
            String racaPet = RacaPet.getText();
            String pesoPet = PesoPet.getText();
            String especiePet = EspeciePet.getText();

            double pesoDouble = 0.0;

            try {
                pesoDouble = Double.parseDouble(pesoPet);
                if (pesoDouble <= 0) {
                    exibirAlerta("Erro", "O peso deve ser um valor numérico positivo", Alert.AlertType.ERROR);
                    return;
                }
            } catch (NumberFormatException e) {
                exibirAlerta("Erro", "O peso deve ser um valor numérico válido", Alert.AlertType.ERROR);
                return;
            }

            Pet novoPet = new Pet(nomePet, racaPet, pesoDouble, especiePet);

            // Adiciona o novo pet ao tutor
            tutorParaEdicao.adicionarPet(novoPet);

            // Exibe uma mensagem de sucesso
            exibirAlerta("Adicionando Novo core.Pet", "Novo core.Pet adicionado com sucesso!", Alert.AlertType.INFORMATION);

            // Limpa os campos após a adição
            limparCampos();
        } else {
            exibirAlerta("Campos Incompletos", "Preencha todos os campos para adicionar um novo core.Pet.", Alert.AlertType.WARNING);
        }
    }

    @FXML
    void VoltarTela(ActionEvent event) {
        Stage palco = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Fecha a janela
        palco.close();
    }

    private void exibirAlerta(String titulo, String mensagem, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }

    private boolean validarCampos() {
        return !NomePet.getText().isEmpty() &&
                !EspeciePet.getText().isEmpty() &&
                !RacaPet.getText().isEmpty() &&
                !PesoPet.getText().isEmpty();
    }

    private void limparCampos() {
        NomePet.clear();
        EspeciePet.clear();
        RacaPet.clear();
        PesoPet.clear();
    }
}
