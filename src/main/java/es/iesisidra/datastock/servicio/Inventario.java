package es.iesisidra.datastock.servicio;

import java.util.ArrayList;
import java.util.List;

import es.iesisidra.datastock.modelo.Producto;

/**
 * Servicio central para gestionar productos en memoria e interactuar con
 * persistencia.
 */
public class Inventario {

    private List<Producto> listaProductos;

    public Inventario() {
        this.listaProductos = new ArrayList<>();
    }

    public boolean agregarProducto(Producto p) {
        if (buscarPorId(p.getId()) == null) {
            listaProductos.add(p);
            return true;
        }
        return false;
    }

    public Producto buscarPorId(String id) {
        for (Producto p : listaProductos) {
            if (p.getId().equalsIgnoreCase(id)) {
                return p;
            }
        }
        return null;
    }

    public List<Producto> getListaProductos() {
        return listaProductos;
    }

    public void cargarDesdeXml(String ruta) {
        // XML persistence is unavailable without ProductoXmlRepository.
    }

    public boolean guardarEnXml(String ruta) {
        // XML persistence is unavailable without ProductoXmlRepository.
        return false;
    }
}
