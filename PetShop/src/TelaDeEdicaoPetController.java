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

    public void setTutorParaEdicao(Tutor tutor) {
        this.tutorParaEdicao = tutor;
    }

    @FXML
    void ExcluirTodosDados(ActionEvent event) {
        // Verifica se petParaEdicao e tutorParaEdicao não são nulos

        System.out.println("Tutor para Edição: " + tutorParaEdicao);
        System.out.println("Pet para Edição: " + petParaEdicao);

        if (petParaEdicao != null && tutorParaEdicao != null) {
            // Implemente a lógica para excluir petParaEdicao
            tutorParaEdicao.removerPet(petParaEdicao);  // Assumindo que há um método para remover o Pet do Tutor
            Singleton.getInstance().removerPet(petParaEdicao);  // Remove o Pet da lista geral no Singleton

            exibirAlerta("Excluindo Dados", "Os dados do Pet foram excluídos.", Alert.AlertType.INFORMATION);
            fecharJanela();
        } else {
            exibirAlerta("Nenhum Pet ou Tutor Selecionado", "Não há um Pet ou Tutor para excluir.", Alert.AlertType.WARNING);
        }
    }

    @FXML
    void SalvarDados(ActionEvent event) {
        // Implemente a lógica para salvar os dados do pet
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

            exibirAlerta("Salvando...", "Dados do Pet salvos com sucesso!", Alert.AlertType.INFORMATION);
            fecharJanela();
        }
    }

    public void initData(Pet pet, Tutor tutor) {
        petParaEdicao = pet;
        tutorParaEdicao = tutor;

        // Preenche os campos com os dados do Pet
        EspeciePet.setText(pet.getEspecie());
        NomePet.setText(pet.getNome());
        PesoPet.setText(String.valueOf(pet.getPeso()));
        RacaPet.setText(pet.getRaca());
    }

    private void fecharJanela() {
        Stage palco = (Stage) EspeciePet.getScene().getWindow();

        // Fecha a janela de edição de pet
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
