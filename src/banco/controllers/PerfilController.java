package banco.controllers;

import banco.dao.UserDAO;
import banco.models.User;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class PerfilController {

    @FXML private TextField txtNombre;
    @FXML private TextField txtApellido;
    @FXML private TextField txtCedula;
    @FXML private TextField txtCuenta;
    @FXML private PasswordField txtPin;
    @FXML private Label lblEstado;

    private final UserDAO usuarioDAO = new UserDAO();

    @FXML
    public void initialize() {
        User usuario = LoginController.usuarioActual;

        txtNombre.setText(usuario.obtenerNombre());
        txtApellido.setText(usuario.obtenerApellido());
        txtCedula.setText(usuario.obtenerCedula());
        txtCuenta.setText(usuario.obtenerCuenta());

        if (usuario.obtenerPin().length() <= 6) {
            txtPin.setText(usuario.obtenerPin());
        } else {
            txtPin.setText("");
        }
    }

    @FXML
    private void guardarCambios() {
        try {
            User usuario = LoginController.usuarioActual;

            usuario.establecerNombre(txtNombre.getText());
            usuario.establecerApellido(txtApellido.getText());
            usuario.establecerCedula(txtCedula.getText());
            usuario.establecerCuenta(txtCuenta.getText());
            usuario.establecerPin(txtPin.getText());

            boolean correcto = usuarioDAO.actualizarUsuario(usuario);

            if (correcto) {
                lblEstado.setStyle("-fx-text-fill: green;");
                lblEstado.setText("Cambios guardados correctamente ✔");
            } else {
                lblEstado.setStyle("-fx-text-fill: red;");
                lblEstado.setText("No se pudieron guardar los cambios");
            }

            lblEstado.setVisible(true);

        } catch (Exception e) {
            lblEstado.setStyle("-fx-text-fill: red;");
            lblEstado.setText("Error al actualizar");
            lblEstado.setVisible(true);
        }
    }
}
