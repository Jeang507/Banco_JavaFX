package banco.controllers;

import banco.dao.UserDAO;
import banco.models.User;
import banco.views.SaldoView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DashboardController {

    @FXML private StackPane areaContenido;

    private final UserDAO usuarioDAO = new UserDAO();

    @FXML
    private void initialize() {
        cargarInicio();
    }

    private void cargarVista(String fxml, java.util.function.Consumer<Object> manejador) {
        try {
            FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/banco/views/" + fxml)
            );

            Node vista = loader.load();

            if (manejador != null) {
                Object controlador = loader.getController();
                manejador.accept(controlador);
            }

            areaContenido.getChildren().setAll(vista);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cargarVista(String fxml) {
        cargarVista(fxml, null);
    }

    @FXML
    private void cargarInicio() {
        cargarVista("inicio.fxml");
    }

    @FXML
    private void cargarPerfil() {
        cargarVista("perfil.fxml");
    }

    @FXML
    private void cargarSaldo() {
        SaldoView vista = new SaldoView();
        vista.establecerSaldo(LoginController.usuarioActual.obtenerSaldo());
        areaContenido.getChildren().setAll(vista);
    }

    @FXML
    private void cargarDeposito() {
        cargarVista("deposito.fxml", controlador -> {

            DepositoController dep = (DepositoController) controlador;

            dep.setAlDepositar(monto -> {

                try {
                    User usuario = LoginController.usuarioActual;
                    double nuevoSaldo = usuario.obtenerSaldo() + monto;

                    if (usuarioDAO.actualizarSaldo(usuario.obtenerNombreUsuario(), nuevoSaldo)) {

                        usuario.establecerSaldo(nuevoSaldo);
                        dep.mostrarNuevoSaldo(nuevoSaldo);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            });

        });
    }

    @FXML
    private void cargarRetiro() {
        cargarVista("retiro.fxml", controlador -> {

            RetiroController ret = (RetiroController) controlador;

            ret.setAlRetirar(monto -> {

                try {
                    User usuario = LoginController.usuarioActual;
                    double saldoActual = usuario.obtenerSaldo();

                    if (monto > saldoActual) {
                        ret.mostrarResultado("Fondos insuficientes");
                        return;
                    }

                    double nuevoSaldo = saldoActual - monto;

                    if (usuarioDAO.actualizarSaldo(usuario.obtenerNombreUsuario(), nuevoSaldo)) {

                        usuario.establecerSaldo(nuevoSaldo);

                        ret.mostrarResultado(
                            "Retiraste: $" + String.format("%.2f", monto) +
                            "\nSaldo restante: $" + String.format("%.2f", nuevoSaldo)
                        );
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            });

        });
    }

    @FXML
    private void cerrarSesion() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/banco/views/login.fxml"));
            Scene escena = new Scene(loader.load());

            Stage ventana = (Stage) areaContenido.getScene().getWindow();

            ventana.setWidth(1200);
            ventana.setHeight(900);

            ventana.setScene(escena);
            ventana.show();

            LoginController.usuarioActual = null;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
