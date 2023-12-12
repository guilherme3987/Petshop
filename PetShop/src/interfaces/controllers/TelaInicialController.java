package interfaces.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class TelaInicialController {

    @FXML
    void CadastrarClic(ActionEvent event) {
        try {
            // Obtém o botão que acionou o evento
            Button botaoClicado = (Button) event.getSource();

            // Carrega o FXML da próxima tela
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../layout/TelaCadastroTPLayout.fxml"));
            Parent root = loader.load();

            // Cria a cena
            Scene cena = new Scene(root);

            // Obtém a referência ao palco atual (Stage) usando o botão clicado
            Stage palco = (Stage) botaoClicado.getScene().getWindow();

            // Define a nova cena no palco
            palco.setScene(cena);

            palco.setTitle("PetShop - Cadastro");

            // Exibe a nova tela
            palco.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void ServicoClick(ActionEvent event) {
        try {
            Button botaoClicado = (Button) event.getSource();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("../layout/TelaDeServicoLayout.fxml"));
            Parent root = loader.load();

            Scene cena = new Scene(root);

            Stage palco = (Stage) botaoClicado.getScene().getWindow();

            palco.setScene(cena);

            palco.setTitle("PetShop - Serviços");

            palco.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void BucarClick(ActionEvent event) {
       try {
            // Obtém o botão que acionou o evento
            Button botaoClicado = (Button) event.getSource();

            // Carrega o FXML da próxima tela
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../layout/TelaBuscaLayout.fxml"));
            Parent root = loader.load();

            // Cria a cena
            Scene cena = new Scene(root);

            // Obtém a referência ao palco atual (Stage) usando o botão clicado
            Stage palco = (Stage) botaoClicado.getScene().getWindow();

            // Define a nova cena no palco
            palco.setScene(cena);

           palco.setTitle("PetShop - Busca");

            // Exibe a nova tela
            palco.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void EncerrarClick(ActionEvent event) {
        // Lógica para ação do botão Encerrar

        // Fechar a janela atual
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
