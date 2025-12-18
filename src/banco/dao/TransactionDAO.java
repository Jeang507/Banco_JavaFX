package banco.dao;

import banco.models.Transaction;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO {

    public boolean insertar(Transaction transaccion) throws SQLException {
        if (transaccion == null) {
            throw new NullPointerException("El objeto Transaction no puede ser null");
        }
        
        String consulta = """
                INSERT INTO transacciones (usuario_id, tipo, monto)
                VALUES (?, ?, ?)
                """;

        try (Connection conexion = DBConnection.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(consulta)) {

            sentencia.setInt(1, transaccion.obtenerUsuarioId());

            sentencia.setString(2, transaccion.obtenerTipo());
            sentencia.setDouble(3, transaccion.obtenerMonto());

            return sentencia.executeUpdate() > 0;
        }
    }

    public List<Transaction> buscarPorUsuario(int idUsuario) throws SQLException {
        String consulta = "SELECT * FROM transacciones WHERE usuario_id = ? ORDER BY fecha DESC";
        List<Transaction> listaTransacciones = new ArrayList<>();

        try (Connection conexion = DBConnection.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(consulta)) {

            sentencia.setInt(1, idUsuario);
            try (ResultSet resultado = sentencia.executeQuery()) {
                while (resultado.next()) {
                    listaTransacciones.add(mapearTransaccion(resultado));
                }
            }
        }
        return listaTransacciones;
    }

    private Transaction mapearTransaccion(ResultSet resultado) throws SQLException {
        return new Transaction(
                resultado.getInt("id"),
                resultado.getInt("usuario_id"),
                resultado.getString("tipo"),
                resultado.getDouble("monto"),
                resultado.getTimestamp("fecha").toLocalDateTime()
        );
    }
}
