package es.iesisidra.datastock.modelo;

import java.time.LocalDateTime;

/**
 * Registro de entradas y salidas de stock para auditoría.
 */
public class MovimientoStock {

    private String idProducto;
    private int cantidad;
    private String tipo; // "ENTRADA" o "SALIDA"
    private LocalDateTime fecha;

    public MovimientoStock(String idProducto, int cantidad, String tipo) {
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.tipo = tipo;
        this.fecha = LocalDateTime.now();
    }

    public String getIdProducto() { return idProducto; }
    public int getCantidad() { return cantidad; }
    public String getTipo() { return tipo; }
    public LocalDateTime getFecha() { return fecha; }

    @Override
    public String toString() {
        return "[" + fecha + "] " + tipo + " de " + cantidad + " unidades para producto ID: " + idProducto;
    }
}
