package banco.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.util.function.DoubleConsumer;

public class RetiroController {

    @FXML private TextField txtRetiro;
    @FXML private Label lblResultado;

    private DoubleConsumer alRetirar;

    public void setAlRetirar(DoubleConsumer accion) {
        this.alRetirar = accion;
    }

    @FXML
    private void procesarRetiro() {
        try {
            double monto = Double.parseDouble(txtRetiro.getText());
            if (alRetirar != null) alRetirar.accept(monto);
        } catch (Exception e) {
            lblResultado.setText("Monto inválido");
            lblResultado.setVisible(true);
        }
    }

    public void mostrarResultado(String texto) {
        lblResultado.setVisible(true);
        lblResultado.setText(texto);
    }
}
