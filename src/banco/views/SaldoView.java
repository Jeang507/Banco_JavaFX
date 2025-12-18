package banco.views;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class SaldoView extends VBox {

    private Label lblSaldo;

    public SaldoView() {
        setSpacing(20);
        setAlignment(Pos.CENTER);
        getStyleClass().add("card");

        Label titulo = new Label("Saldo Disponible");
        titulo.getStyleClass().add("title");

        lblSaldo = new Label("$0.00");
        lblSaldo.getStyleClass().add("subtitle");

        getChildren().addAll(titulo, lblSaldo);
    }

    public void establecerSaldo(double monto) {
        lblSaldo.setText("$" + String.format("%.2f", monto));
    }
}
