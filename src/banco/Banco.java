package banco;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Banco extends Application {

    @Override
    public void start(Stage ventana) throws Exception {

        FXMLLoader cargador = new FXMLLoader(getClass().getResource("views/login.fxml"));
        Scene escena = new Scene(cargador.load(), 1200, 900);

        ventana.setTitle("Banco Virtual");
        ventana.setScene(escena);
        ventana.setResizable(false);
        ventana.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
