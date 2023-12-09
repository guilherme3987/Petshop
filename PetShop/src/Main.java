import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("interfaces/layout/TelaInicialLayout.fxml"));
        Parent root = fxmlLoader.load();
        Scene telaInicial = new Scene(root);

        primaryStage.setResizable(false);
        primaryStage.setScene(telaInicial);
        primaryStage.setTitle("Pet Shop - Inicio");
        primaryStage.show();
    }
}
