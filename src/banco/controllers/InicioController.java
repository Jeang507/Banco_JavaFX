package banco.controllers;

import banco.models.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class InicioController {

    @FXML private Label lblBienvenida;
    @FXML private Label lblNombre;
    @FXML private Label lblCuenta;
    @FXML private Label lblSaldo;

    @FXML private Label lblTransHoy;
    @FXML private Label lblDepositosMes;
    @FXML private Label lblRetirosMes;

    @FXML
    public void initialize() {
        User usuario = LoginController.usuarioActual;

        lblBienvenida.setText("Bienvenido a CHIPIBANK");
        lblNombre.setText("Cliente: " + usuario.obtenerNombre() + " " + usuario.obtenerApellido());
        lblCuenta.setText("Cuenta: " + usuario.obtenerCuenta());
        lblSaldo.setText("Saldo disponible: $" + String.format("%.2f", usuario.obtenerSaldo()));

        lblTransHoy.setText("0");
        lblDepositosMes.setText("$0.00");
        lblRetirosMes.setText("$0.00");
    }
}
