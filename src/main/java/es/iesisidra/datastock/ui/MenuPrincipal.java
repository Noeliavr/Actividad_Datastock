package es.iesisidra.datastock.ui;

import es.iesisidra.datastock.complementarias.AccesoAleatorioActividad;
import es.iesisidra.datastock.complementarias.XPathActividad;
import es.iesisidra.datastock.modelo.Producto;
import es.iesisidra.datastock.servicio.Inventario;

import java.util.Scanner;

/**
 * Consola interactiva que integra la gestión del inventario y ejercicios de la UT1.
 */
public class MenuPrincipal {

    private Inventario inventario;
    private Scanner scanner;

    public MenuPrincipal() {
        this.inventario = new Inventario();
        this.scanner = new Scanner(System.in);
        // Cargar algunos datos iniciales de prueba
        inventario.agregarProducto(new Producto("P01", "Teclado Mecánico", 45.99, 15, "Periféricos"));
        inventario.agregarProducto(new Producto("P02", "Ratón Óptico", 19.50, 30, "Periféricos"));
        inventario.agregarProducto(new Producto("P03", "Monitor 24 pulgadas", 149.00, 8, "Pantallas"));
    }

    public void mostrarMenu() {
        int opcion = -1;
        do {
            System.out.println("\n===========================================");
            System.out.println("   DATASTOCK - GESTIÓN DE INVENTARIO (UT1)");
            System.out.println("===========================================");
            System.out.println("1. Listar Productos en Memoria");
            System.out.println("2. Registrar Nuevo Producto");
            System.out.println("3. Exportar Catálogo a XML (DOM)");
            System.out.println("4. Importar Catálogo desde XML (DOM)");
            System.out.println("5. Probar Consulta XPath sobre XML");
            System.out.println("6. Probar Acceso Aleatorio (Fichero Binario)");
            System.out.println("0. Salir");
            System.out.print("Selecciona una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("⚠ Por favor, introduce un número válido.");
                continue;
            }

            switch (opcion) {
                case 1 -> listarProductos();
                case 2 -> registrarProducto();
                case 3 -> inventario.guardarEnXml("datos/salidas/catalogo_salida.xml");
                case 4 -> inventario.cargarDesdeXml("datos/ejemplos/catalogo_valido.xml");
                case 5 -> probarXPath();
                case 6 -> probarRAF();
                case 0 -> System.out.println("👋 ¡Gracias por utilizar DataStock!");
                default -> System.out.println("⚠ Opción no válida.");
            }
        } while (opcion != 0);
    }

    private void listarProductos() {
        System.out.println("\n--- LISTA DE PRODUCTOS ---");
        if (inventario.getListaProductos().isEmpty()) {
            System.out.println("El inventario está vacío.");
        } else {
            for (Producto p : inventario.getListaProductos()) {
                System.out.println(p);
            }
        }
    }

    private void registrarProducto() {
        System.out.print("ID del Producto (ej. P04): ");
        String id = scanner.nextLine();
        System.out.print("Nombre del Producto: ");
        String nombre = scanner.nextLine();
        System.out.print("Categoría: ");
        String cat = scanner.nextLine();

        double precio = 0.0;
        try {
            System.out.print("Precio (€): ");
            precio = Double.parseDouble(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Valor de precio no válido, se asignará 0.");
        }

        int stock = 0;
        try {
            System.out.print("Stock Inicial: ");
            stock = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Valor de stock no válido, se asignará 0.");
        }

        Producto p = new Producto(id, nombre, precio, stock, cat);
        if (inventario.agregarProducto(p)) {
            System.out.println("✔ Producto registrado con éxito.");
        } else {
            System.out.println("❌ Error: Ya existe un producto con el ID " + id);
        }
    }

    private void probarXPath() {
        System.out.println("\n--- EJECUCIÓN DE PRUEBA XPATH ---");
        // Aseguramos que existe el fichero guardando antes
        inventario.guardarEnXml("datos/salidas/catalogo_salida.xml");
        XPathActividad.ejecutarConsultaXPath("datos/salidas/catalogo_salida.xml", "//producto[precio > 20]/nombre");
    }

    private void probarRAF() {
        System.out.println("\n--- EJECUCIÓN PRUEBA ACCESO ALEATORIO ---");
        String rutaBin = "datos/salidas/productos.dat";
        AccesoAleatorioActividad.guardarProductoAleatorio(rutaBin, 1, "P1", 20, 15.50);
        AccesoAleatorioActividad.guardarProductoAleatorio(rutaBin, 2, "P2", 50, 99.99);

        AccesoAleatorioActividad.leerProductoAleatorio(rutaBin, 1);
        AccesoAleatorioActividad.leerProductoAleatorio(rutaBin, 2);
    }
}
