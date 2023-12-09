package interfaces.controllers;

import core.Pet;
import core.Singleton;
import core.Tutor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TelaDeEdicaoPetController {

    private Pet petParaEdicao;
    private Tutor tutorParaEdicao;

    @FXML
    private TextField EspeciePet;

    @FXML
    private TextField NomePet;

    @FXML
    private TextField PesoPet;

    @FXML
    private TextField RacaPet;


    @FXML
    void ExcluirTodosDados(ActionEvent event) {

        System.out.println("Tutor para Edição: " + tutorParaEdicao);
        System.out.println("Pet para Edição: " + petParaEdicao);

        if (petParaEdicao != null && tutorParaEdicao != null) {
            tutorParaEdicao.removerPet(petParaEdicao);
            Singleton.getInstance().removerPet(petParaEdicao);

            exibirAlerta("Excluindo Dados", "Os dados do core.Pet foram excluídos.", Alert.AlertType.INFORMATION);
            fecharJanela();
        } else {
            exibirAlerta("Nenhum core.Pet ou core.Tutor Selecionado", "Não há um core.Pet ou core.Tutor para excluir.", Alert.AlertType.WARNING);
        }
    }

    @FXML
    void SalvarDados(ActionEvent event) {
        if (validarCampos()) {
            petParaEdicao.setEspecie(EspeciePet.getText());
            petParaEdicao.setNome(NomePet.getText());

            try {
                double peso = Double.parseDouble(PesoPet.getText());
                petParaEdicao.setPeso(peso);
            } catch (NumberFormatException e) {
                exibirAlerta("Erro", "Digite um valor válido para o peso.", Alert.AlertType.ERROR);
                return;
            }

            petParaEdicao.setRaca(RacaPet.getText());

            exibirAlerta("Salvando...", "Dados do core.Pet salvos com sucesso!", Alert.AlertType.INFORMATION);
            fecharJanela();
        }
    }

    public void initData(Pet pet, Tutor tutor) {
        petParaEdicao = pet;
        tutorParaEdicao = tutor;

        EspeciePet.setText(pet.getEspecie());
        NomePet.setText(pet.getNome());
        PesoPet.setText(String.valueOf(pet.getPeso()));
        RacaPet.setText(pet.getRaca());
    }

    private void fecharJanela() {
        Stage palco = (Stage) EspeciePet.getScene().getWindow();

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
        if (EspeciePet.getText().isEmpty() || NomePet.getText().isEmpty() || PesoPet.getText().isEmpty() || RacaPet.getText().isEmpty()) {
            exibirAlerta("Campos Vazios", "Preencha todos os campos para salvar.", Alert.AlertType.WARNING);
            return false;
        }
        return true;
    }
}
