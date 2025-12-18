package banco.controllers;

import banco.dao.UserDAO;
import banco.models.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class LoginController {
    
    public static User usuarioActual;
    @FXML private TextField txtUsuario;
    @FXML private PasswordField txtPin;
    @FXML private Label lblError;

    private final UserDAO usuarioDAO = new UserDAO();

    @FXML
    private void iniciarSesion() {
        try {
            String nombreUsuario = txtUsuario.getText();
            String pin = txtPin.getText();

            User usuario = usuarioDAO.iniciarSesion(nombreUsuario, pin);

            if (usuario == null) {
                lblError.setText("Credenciales incorrectas");
                lblError.setVisible(true);
                return;
            }

            LoginController.usuarioActual = usuario;

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/banco/views/dashboard.fxml"));
            Scene escena = new Scene(loader.load());

            Stage ventana = (Stage) txtUsuario.getScene().getWindow();
            ventana.setScene(escena);
            ventana.show();

        } catch (Exception e) {
            e.printStackTrace();
            lblError.setText("Error interno");
            lblError.setVisible(true);
        }
    }

    @FXML
    private void irAlRegistro() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/banco/views/register.fxml"));
            Scene escena = new Scene(loader.load());

            Stage ventana = (Stage) txtUsuario.getScene().getWindow();
            ventana.setWidth(1200);
            ventana.setHeight(900);
            ventana.setScene(escena);
            ventana.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
