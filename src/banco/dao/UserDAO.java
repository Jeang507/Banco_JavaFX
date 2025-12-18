package banco.dao;

import banco.models.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public User buscarPorUsuario(String nombreUsuario) throws SQLException {
        if (nombreUsuario == null || nombreUsuario.trim().isEmpty()) {
            throw new IllegalArgumentException("El username no puede ser nulo o vacío");
        }
        
        String consulta = "SELECT * FROM users WHERE username = ?";
        try (Connection conexion = DBConnection.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(consulta)) {

            sentencia.setString(1, nombreUsuario);
            try (ResultSet resultado = sentencia.executeQuery()) {
                if (resultado.next()) {
                    return mapearUsuario(resultado);
                }
            }
        }
        return null;
    }

    public boolean insertar(User usuario) throws SQLException {
        if (usuario == null) {
            throw new NullPointerException("El objeto User no puede ser null");
        }
        
        String consulta = """
                INSERT INTO users (username, nombre, apellido, cedula, cuenta, pin, saldo, rol)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection conexion = DBConnection.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(consulta)) {

            sentencia.setString(1, usuario.obtenerNombreUsuario());
            sentencia.setString(2, usuario.obtenerNombre());
            sentencia.setString(3, usuario.obtenerApellido());
            sentencia.setString(4, usuario.obtenerCedula());
            sentencia.setString(5, usuario.obtenerCuenta());
            sentencia.setString(6, usuario.obtenerPin());
            sentencia.setDouble(7, usuario.obtenerSaldo());
            sentencia.setString(8, usuario.obtenerRol());

            return sentencia.executeUpdate() > 0;
        }
    }

    public boolean actualizarSaldo(String nombreUsuario, double nuevoSaldo) throws SQLException {
        if (nombreUsuario == null || nombreUsuario.trim().isEmpty()) {
            throw new IllegalArgumentException("El username no puede ser nulo o vacío");
        }
        if (nuevoSaldo < 0) {
            throw new IllegalArgumentException("El saldo no puede ser negativo");
        }
        
        String consulta = "UPDATE users SET saldo = ? WHERE username = ?";

        try (Connection conexion = DBConnection.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(consulta)) {

            sentencia.setDouble(1, nuevoSaldo);
            sentencia.setString(2, nombreUsuario);

            return sentencia.executeUpdate() > 0;
        }
    }

    public List<User> buscarTodos() throws SQLException {
        List<User> listaUsuarios = new ArrayList<>();
        String consulta = "SELECT * FROM users";

        try (Connection conexion = DBConnection.obtenerConexion();
             Statement sentencia = conexion.createStatement();
             ResultSet resultado = sentencia.executeQuery(consulta)) {

            while (resultado.next()) {
                listaUsuarios.add(mapearUsuario(resultado));
            }
        }
        return listaUsuarios;
    }

    public User iniciarSesion(String nombreUsuario, String pin) throws SQLException {
        if (nombreUsuario == null || nombreUsuario.trim().isEmpty()) {
            throw new IllegalArgumentException("El username no puede ser nulo o vacío");
        }
        if (pin == null || pin.trim().isEmpty()) {
            throw new IllegalArgumentException("El PIN no puede ser nulo o vacío");
        }
        
        String consulta = "SELECT * FROM users WHERE username = ? AND pin = ?";

        try (Connection conexion = DBConnection.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(consulta)) {

            sentencia.setString(1, nombreUsuario);
            sentencia.setString(2, pin);

            try (ResultSet resultado = sentencia.executeQuery()) {
                if (resultado.next()) {
                    return mapearUsuario(resultado);
                }
            }
        }

        return null;
    }

    private User mapearUsuario(ResultSet resultado) throws SQLException {
        return new User(
                resultado.getInt("id"),
                resultado.getString("username"),
                resultado.getString("nombre"),
                resultado.getString("apellido"),
                resultado.getString("cedula"),
                resultado.getString("cuenta"),
                resultado.getString("pin"),
                resultado.getDouble("saldo"),
                resultado.getString("rol"),
                resultado.getInt("intentos"),
                resultado.getDate("fecha_expiracion").toLocalDate()
        );
    }

    public boolean actualizarUsuario(User usuario) throws SQLException {
        if (usuario == null) {
            throw new IllegalArgumentException("El usuario no puede ser null");
        }

        String consulta = """
                UPDATE users
                SET nombre = ?, apellido = ?, cedula = ?, cuenta = ?, pin = ?
                WHERE username = ?
                """;

        try (Connection conexion = DBConnection.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(consulta)) {

            sentencia.setString(1, usuario.obtenerNombre());
            sentencia.setString(2, usuario.obtenerApellido());
            sentencia.setString(3, usuario.obtenerCedula());
            sentencia.setString(4, usuario.obtenerCuenta());
            sentencia.setString(5, usuario.obtenerPin());
            sentencia.setString(6, usuario.obtenerNombreUsuario());

            return sentencia.executeUpdate() > 0;
        }
    }
}
