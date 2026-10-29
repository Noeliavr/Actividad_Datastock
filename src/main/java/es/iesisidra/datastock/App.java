package es.iesisidra.datastock;


import es.iesisidra.datastock.servicio.Inventario;
import es.iesisidra.datastock.ui.MenuPrincipal;


public final class App {


    private App() {
    }

    public static void main(String[] args) {

        // Creamos el inventario, el menú y le pasamos el inventario y lo iniciamos.
        Inventario inventario = new Inventario();

        
        MenuPrincipal menu = new MenuPrincipal(inventario);

        menu.iniciar();
    }
}