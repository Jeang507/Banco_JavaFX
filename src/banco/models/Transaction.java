package banco.models;

import java.time.LocalDateTime;

public class Transaction {

    private int id;
    private int usuarioId;
    private String tipo;
    private double monto;
    private LocalDateTime fecha;

    public Transaction(int id, int usuarioId, String tipo, double monto, LocalDateTime fecha) {
        establecerId(id);
        establecerUsuarioId(usuarioId);
        establecerTipo(tipo);
        establecerMonto(monto);
        establecerFecha(fecha);
    }

    public Transaction(int usuarioId, String tipo, double monto) {
        this(0, usuarioId, tipo, monto, LocalDateTime.now());
    }

    public int obtenerId() {
        return id;
    }

    public void establecerId(int id) {
        if (id < 0) throw new IllegalArgumentException("El ID no puede ser negativo");
        this.id = id;
    }

    public int obtenerUsuarioId() {
        return usuarioId;
    }

    public void establecerUsuarioId(int usuarioId) {
        if (usuarioId <= 0) throw new IllegalArgumentException("El ID de usuario debe ser positivo");
        this.usuarioId = usuarioId;
    }

    public String obtenerTipo() {
        return tipo;
    }

    public void establecerTipo(String tipo) {
        if (tipo == null || tipo.trim().isEmpty())
            throw new IllegalArgumentException("El tipo no puede ser vacío");
        this.tipo = tipo;
    }

    public double obtenerMonto() {
        return monto;
    }

    public void establecerMonto(double monto) {
        if (monto <= 0) throw new IllegalArgumentException("El monto debe ser mayor a cero");
        this.monto = monto;
    }

    public LocalDateTime obtenerFecha() {
        return fecha;
    }

    public void establecerFecha(LocalDateTime fecha) {
        if (fecha == null) throw new IllegalArgumentException("La fecha no puede ser null");
        this.fecha = fecha;
    }

    public boolean esDeposito() {
        return "DEPOSITO".equalsIgnoreCase(tipo);
    }

    public boolean esRetiro() {
        return "RETIRO".equalsIgnoreCase(tipo);
    }

    public boolean esTransferencia() {
        return "TRANSFERENCIA".equalsIgnoreCase(tipo);
    }

    @Override
    public String toString() {
        return String.format("Transaccion[id=%d, usuarioId=%d, tipo=%s, monto=%.2f, fecha=%s]",
                id, usuarioId, tipo, monto, fecha);
    }
}
