package banco.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SaldoController {

    @FXML
    private Label lblSaldo;

    public void establecerSaldo(double monto) {
        lblSaldo.setText("$" + String.format("%.2f", monto));
    }
}
