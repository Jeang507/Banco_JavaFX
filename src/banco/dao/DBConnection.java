package banco.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL_CONEXION = "jdbc:postgresql://localhost:5432/banco";
    private static final String USUARIO = "postgres";
    private static final String CLAVE = "1234";

    private DBConnection() {
        throw new UnsupportedOperationException("Esta es una clase de utilidad y no puede ser instanciada");
    }

    public static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(URL_CONEXION, USUARIO, CLAVE);
    }
}
