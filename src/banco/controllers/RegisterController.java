package banco.controllers;

import banco.dao.UserDAO;
import banco.models.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class RegisterController {

    @FXML private TextField txtUsuario;
    @FXML private TextField txtNombre;
    @FXML private TextField txtApellido;
    @FXML private TextField txtCedula;
    @FXML private TextField txtCuenta;
    @FXML private PasswordField txtPin;
    @FXML private Label lblError;

    private final UserDAO usuarioDAO = new UserDAO();

    @FXML
    private void registrarse() {
        try {
            User usuario = new User();

            usuario.establecerNombreUsuario(txtUsuario.getText());
            usuario.establecerNombre(txtNombre.getText());
            usuario.establecerApellido(txtApellido.getText());
            usuario.establecerCedula(txtCedula.getText());
            usuario.establecerCuenta(txtCuenta.getText());
            usuario.establecerPin(txtPin.getText());
            usuario.establecerSaldo(0);
            usuario.establecerRol("CLIENTE");
            usuario.establecerIntentos(0);

            boolean correcto = usuarioDAO.insertar(usuario);

            if (!correcto) {
                lblError.setText("No se pudo registrar");
                lblError.setVisible(true);
                return;
            }

            irAlLogin();

        } catch (Exception e) {
            lblError.setText("Datos inválidos o usuario duplicado");
            lblError.setVisible(true);
        }
    }

    @FXML
    private void irAlLogin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/banco/views/login.fxml"));
            Scene escena = new Scene(loader.load());

            Stage ventana = (Stage) txtUsuario.getScene().getWindow();
            ventana.setScene(escena);
            ventana.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
