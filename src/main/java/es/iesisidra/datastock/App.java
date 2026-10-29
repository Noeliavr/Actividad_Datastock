package es.iesisidra.datastock;

import es.iesisidra.datastock.ui.MenuPrincipal;

/**
 * Clase principal de inicio de la aplicación DataStock.
 */
public class App {
    public static void main(String[] args) {
        MenuPrincipal menu = new MenuPrincipal();
        menu.mostrarMenu();
    }
}
