package es.iesisidra.datastock.servicio;

import es.iesisidra.datastock.modelo.Producto;
import es.iesisidra.datastock.repository.ProductoXmlRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Servicio central para gestionar productos en memoria e interactuar con persistencia.
 */
public class Inventario {

    private List<Producto> listaProductos;
    private ProductoXmlRepository xmlRepo;

    public Inventario() {
        this.listaProductos = new ArrayList<>();
        this.xmlRepo = new ProductoXmlRepository();
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
        List<Producto> cargados = xmlRepo.cargarDesdeXml(ruta);
        if (!cargados.isEmpty()) {
            this.listaProductos = cargados;
        }
    }

    public boolean guardarEnXml(String ruta) {
        return xmlRepo.guardarEnXml(listaProductos, ruta);
    }
}
