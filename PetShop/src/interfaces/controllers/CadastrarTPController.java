package interfaces.controllers;

import core.Menu;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.util.Arrays;

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

        if (CPFTutor.getText().trim().isEmpty() || NomeTutor.getText().trim().isEmpty() || TelefoneTutor.getText().trim().isEmpty()
                || NomePet.getText().trim().isEmpty() || RacaPet.getText().trim().isEmpty() || PesoPet.getText().trim().isEmpty() || EspeciePet.getText().trim().isEmpty()) {
            exibirAlerta("Erro", "Todos os campos são obrigatórios", AlertType.ERROR);
        } else {
            cpftutor = CPFTutor.getText();
            nomeTutor = NomeTutor.getText();
            telefone = TelefoneTutor.getText();

            if (!validadorTelefone.validarTelefone(telefone)) {
                exibirAlerta("Erro", "Número de telefone inválido!", AlertType.ERROR);
                return;
            }

            nomePet = NomePet.getText();
            racaPet = RacaPet.getText();
            pesoPet = PesoPet.getText();
            especiePet = EspeciePet.getText();

            // Validar se os campos contêm apenas letras
            if (!apenasLetras(nomePet) || !apenasLetras(racaPet) || !apenasLetras(especiePet) || !apenasLetras(nomeTutor)) {
                exibirAlerta("Erro", "Os campos: Nome do core.Tutor, Nome do core.Pet, Raça e Espécie aceitam apenas letras.", AlertType.ERROR);
                return;
            }

            double pesoDouble = 0.0;

            try {
                pesoDouble = Double.parseDouble(pesoPet);
                if (pesoDouble <= 0) {
                    exibirAlerta("Erro", "O peso deve ser um valor numérico positivo", AlertType.ERROR);
                    return;
                }
            } catch (NumberFormatException e) {
                exibirAlerta("Erro", "O peso deve ser um valor numérico válido", AlertType.ERROR);
                return;
            }

            // Validar CPF
            if (!validadorCPF.validarCPF(cpftutor)) {
                exibirAlerta("Erro", "CPF inválido", AlertType.ERROR);
                return;
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
            Node source = (Node) event.getSource();
            Stage palco = (Stage) source.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("../layout/TelaInicialLayout.fxml"));
            Parent root = loader.load();
            Scene cena = new Scene(root);

            palco.setScene(cena);
            palco.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public class validadorCPF {

        public static boolean validarCPF(String cpf) {
            // Remove caracteres que n sao numeros
            cpf = cpf.replaceAll("[^0-9]", "");

            // Verifica se o cpf tem 11 digitos
            if (cpf.length() != 11) {
                return false;
            }

            // Calcula primeiro dígito verificador
            int soma = 0;
            for (int i = 0; i < 9; i++) {
                soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
            }
            int primeiroDigito = 11 - (soma % 11);
            if (primeiroDigito > 9) {
                primeiroDigito = 0;
            }

            // Verif primeiro digito
            if (Character.getNumericValue(cpf.charAt(9)) != primeiroDigito) {
                return false;
            }

            // Calcula segundo dígito verificador
            soma = 0;
            for (int i = 0; i < 10; i++) {
                soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
            }
            int segundoDigito = 11 - (soma % 11);
            if (segundoDigito > 9) {
                segundoDigito = 0;
            }

            // Verif segundo dígito
            return Character.getNumericValue(cpf.charAt(10)) == segundoDigito;
        }
    }

    public class validadorTelefone {

        public static boolean validarTelefone(String telefone) {
            // Remove caracteres n numericos
            String numeroLimpo = telefone.replaceAll("[^0-9]", "");

            // Verifica se o num de telefone tem o formato BR
            if (!numeroLimpo.matches("\\d{10,11}")) {
                return false;
            }

            // Extrai o DDD
            String ddd = numeroLimpo.substring(0, 2);

            // DDDs BR VALIDOS
            String[] dddsValidos = {"11", "12", "13", "14", "15", "16", "17", "18", "19", "21", "22", "24", "27", "28", "31", "32", "33", "34", "35", "37", "38", "41", "42", "43", "44", "45", "46", "47", "48", "49", "51", "53", "54", "55", "61", "62", "63", "64", "65", "66", "67", "68", "69", "71", "73", "74", "75", "77", "79", "81", "82", "83", "84", "85", "86", "87", "88", "89", "91", "92", "93", "94", "95", "96", "97", "98", "99"};

            //  DDD é válido
            if (!Arrays.asList(dddsValidos).contains(ddd)) {
                return false;
            }

            return true;
        }
    }

    public static boolean apenasLetras(String texto) {
        // Remove caracteres que n sao letras, espaços ou num romanos
        String textoLimpo = texto.replaceAll("[^\\p{L} IVXLCDM]+", "");

        // Verif se tem apenas letras, espaços ou num romanos
        return texto.equals(textoLimpo);
    }

    private void exibirAlerta(String titulo, String mensagem, AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }

    private void limparCampos() {
        CPFTutor.clear();
        NomeTutor.clear();
        TelefoneTutor.clear();
        NomePet.clear();
        RacaPet.clear();
        PesoPet.clear();
        EspeciePet.clear();
    }

}
