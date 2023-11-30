import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class CadastrarTPController {

    Menu menu = new Menu();

    @FXML
    private TextField CPFTutor;

    @FXML
    private TextField EspeciePet;

    @FXML
    private TextField NomePet;

    @FXML
    private TextField NomeTutor;

    @FXML
    private TextField PesoPet;

    @FXML
    private TextField RacaPet;

    @FXML
    private TextField TelefoneTutor;

    @FXML
    void CadastrarDados(ActionEvent event) {

        String cpftutor;
        String nomeTutor;
        String telefone;

        String nomePet;
        String racaPet;
        String pesoPet;
        String especiePet;
        
        if(CPFTutor.getText().trim().isEmpty() || NomeTutor.getText().trim().isEmpty() || TelefoneTutor.getText().trim().isEmpty()
        || NomePet.getText().trim().isEmpty() || RacaPet.getText().trim().isEmpty() || PesoPet.getText().trim().isEmpty() || RacaPet.getText().trim().isEmpty()){
            exibirAlerta("Erro", "Todos os campos são obrigatórios", AlertType.ERROR);
        }else{
            cpftutor = CPFTutor.getText();
            nomeTutor = NomeTutor.getText();
            telefone = TelefoneTutor.getText();

            nomePet = NomePet.getText();
            racaPet = RacaPet.getText();
            pesoPet = PesoPet.getText();
            especiePet = EspeciePet.getText();
            
            

            double pesoDouble = 0.0; // Valor padrão se a conversão falhar
            try {
                pesoDouble = Double.parseDouble(pesoPet);
            } catch (NumberFormatException e) {
                exibirAlerta("Erro", "O peso deve ser um valor numérico válido", AlertType.ERROR);
                return; // Se houver um erro na conversão, pare a execução do método
            }

            menu.cadastrarTutor(nomeTutor, cpftutor, telefone);
            menu.cadastrarPet(cpftutor, nomePet, racaPet, pesoDouble, especiePet);


            exibirAlerta("Sucesso", "Dados cadastrados com sucesso", AlertType.INFORMATION);
            limparCampos();
        }
    }

    @FXML
    void VoltarTelaAnterior(ActionEvent event) {
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

    private void limparCampos(){
        CPFTutor.clear();
        NomeTutor.clear();
        TelefoneTutor.clear();

        NomePet.clear();
        RacaPet.clear();
        PesoPet.clear();
        EspeciePet.clear();
    }

    private void exibirAlerta(String titulo, String mensagem, AlertType tipo){
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }
}

