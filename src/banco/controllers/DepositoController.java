package banco.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.util.function.DoubleConsumer;

public class DepositoController {

    @FXML private TextField txtDeposito;
    @FXML private Label lblResultado;

    private DoubleConsumer alDepositar;

    public void setAlDepositar(DoubleConsumer accion) {
        this.alDepositar = accion;
    }

    @FXML
    private void procesarDeposito() {
        try {
            double monto = Double.parseDouble(txtDeposito.getText());

            if (monto <= 0) {
                lblResultado.setVisible(true);
                lblResultado.setText("Ingrese un monto válido mayor a 0");
                return;
            }

            if (alDepositar != null) alDepositar.accept(monto);

        } catch (Exception e) {
            lblResultado.setVisible(true);
            lblResultado.setText("Ingrese un número válido");
        }
    }

    public void mostrarNuevoSaldo(double saldo) {
        lblResultado.setVisible(true);
        lblResultado.setText("Nuevo saldo: $" + String.format("%.2f", saldo));
    }
}
