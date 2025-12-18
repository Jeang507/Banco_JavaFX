package banco.models;

import java.time.LocalDate;

public class User {

    private int id;
    private String username;
    private String nombre;
    private String apellido;
    private String cedula;
    private String cuenta;
    private String pin;
    private double saldo;
    private String rol;
    private int intentos;
    private LocalDate fechaExpiracion;

    public User(int id, String username, String nombre, String apellido,
                String cedula, String cuenta, String pin,
                double saldo, String rol, int intentos, LocalDate fechaExpiracion) {
        establecerId(id);
        establecerNombreUsuario(username);
        establecerNombre(nombre);
        establecerApellido(apellido);
        establecerCedula(cedula);
        establecerCuenta(cuenta);
        establecerPin(pin);
        establecerSaldo(saldo);
        establecerRol(rol);
        establecerIntentos(intentos);
        establecerFechaExpiracion(fechaExpiracion);
    }

    public User() {
    }

    public int obtenerId() {
        return id;
    }

    public void establecerId(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("El ID no puede ser negativo");
        }
        this.id = id;
    }

    public String obtenerNombreUsuario() {
        return username;
    }

    public void establecerNombreUsuario(String username) {
        if (username == null) {
            throw new NullPointerException("El username no puede ser null");
        }
        if (username.trim().isEmpty()) {
            throw new IllegalArgumentException("El username no puede estar vacío");
        }
        this.username = username;
    }

    public String obtenerNombre() {
        return nombre;
    }

    public void establecerNombre(String nombre) {
        if (nombre == null) {
            throw new NullPointerException("El nombre no puede ser null");
        }
        if (nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        this.nombre = nombre;
    }

    public String obtenerApellido() {
        return apellido;
    }

    public void establecerApellido(String apellido) {
        if (apellido == null) {
            throw new NullPointerException("El apellido no puede ser null");
        }
        if (apellido.trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido no puede estar vacío");
        }
        this.apellido = apellido;
    }

    public String obtenerCedula() {
        return cedula;
    }

    public void establecerCedula(String cedula) {
        if (cedula == null) {
            throw new NullPointerException("La cédula no puede ser null");
        }
        if (cedula.trim().isEmpty()) {
            throw new IllegalArgumentException("La cédula no puede estar vacía");
        }
        this.cedula = cedula;
    }

    public String obtenerCuenta() {
        return cuenta;
    }

    public void establecerCuenta(String cuenta) {
        if (cuenta == null) {
            throw new NullPointerException("La cuenta no puede ser null");
        }
        if (cuenta.trim().isEmpty()) {
            throw new IllegalArgumentException("La cuenta no puede estar vacía");
        }
        this.cuenta = cuenta;
    }

    public String obtenerPin() {
        return pin;
    }

    public void establecerPin(String pin) {
        if (pin == null) {
            throw new NullPointerException("El PIN no puede ser null");
        }
        if (!pin.matches("\\d{4}")) {
            throw new IllegalArgumentException("El PIN debe ser exactamente 4 dígitos numéricos");
        }
        this.pin = pin;
    }

    public double obtenerSaldo() {
        return saldo;
    }

    public void establecerSaldo(double saldo) {
        if (saldo < 0) {
            throw new IllegalArgumentException("El saldo no puede ser negativo");
        }
        this.saldo = saldo;
    }

    public String obtenerRol() {
        return rol;
    }

    public void establecerRol(String rol) {
        if (rol == null) {
            throw new NullPointerException("El rol no puede ser null");
        }
        if (rol.trim().isEmpty()) {
            throw new IllegalArgumentException("El rol no puede estar vacío");
        }
        this.rol = rol;
    }

    public int obtenerIntentos() {
        return intentos;
    }

    public void establecerIntentos(int intentos) {
        if (intentos < 0) {
            throw new IllegalArgumentException("Los intentos no pueden ser negativos");
        }
        this.intentos = intentos;
    }

    public LocalDate obtenerFechaExpiracion() {
        return fechaExpiracion;
    }

    public void establecerFechaExpiracion(LocalDate fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public String obtenerNombreCompleto() {
        return String.format("%s %s", nombre, apellido).trim();
    }

    public boolean esCuentaExpirada() {
        if (fechaExpiracion == null) {
            return false;
        }
        return LocalDate.now().isAfter(fechaExpiracion);
    }

    public boolean estaBloqueada() {
        return intentos >= 3;
    }

    public void incrementarIntentos() {
        this.intentos++;
    }

    public void reiniciarIntentos() {
        this.intentos = 0;
    }

    public boolean esAdministrador() {
        return "ADMINISTRADOR".equalsIgnoreCase(rol);
    }

    public boolean esCliente() {
        return "CLIENTE".equalsIgnoreCase(rol);
    }

    public String obtenerSaldoFormateado() {
        return String.format("$%,.2f", saldo);
    }

    @Override
    public String toString() {
        return String.format(
            "Usuario[id=%d, username=%s, nombre=%s %s, saldo=%.2f, rol=%s, intentos=%d]",
            id, username, nombre, apellido, saldo, rol, intentos
        );
    }

    public boolean tieneSaldoSuficiente(double monto) {
        if (monto < 0) {
            throw new IllegalArgumentException("El monto no puede ser negativo");
        }
        return saldo >= monto;
    }

    public boolean esCuentaActiva() {
        return !estaBloqueada() && 
               !esCuentaExpirada() && 
               saldo >= 0;
    }
}
