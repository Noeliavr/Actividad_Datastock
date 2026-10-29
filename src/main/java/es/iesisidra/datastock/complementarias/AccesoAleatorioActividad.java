package es.iesisidra.datastock.complementarias;

import java.io.File;
import java.io.RandomAccessFile;

/**
 * Requisito RF-FIC-08: Gestión de productos en ficheros de Acceso Aleatorio (RAF).
 * Permite leer y escribir registros de tamaño fijo directamente en disco.
 */
public class AccesoAleatorioActividad {

    // Tamaño de registro fijo: ID (2 chars = 4 bytes) + Stock (int = 4 bytes) + Precio (double = 8 bytes) = 16 bytes
    private static final int LONGITUD_REGISTRO = 16;

    public static void guardarProductoAleatorio(String rutaFichero, int posicion, String id, int stock, double precio) {
        try {
            File f = new File(rutaFichero);
            if (f.getParentFile() != null) f.getParentFile().mkdirs();

            try (RandomAccessFile raf = new RandomAccessFile(f, "rw")) {
                // Posicionar el puntero en la posición solicitada (1-based index)
                long posicionByte = (posicion - 1) * LONGITUD_REGISTRO;
                raf.seek(posicionByte);

                // Escribir ID ajustado a 2 caracteres
                String idPadded = String.format("%-2.2s", id);
                raf.writeChars(idPadded); // 2 caracteres x 2 bytes = 4 bytes
                raf.writeInt(stock);      // 4 bytes
                raf.writeDouble(precio);  // 8 bytes

                System.out.println("✔ Registro guardado en la posición " + posicion + " del fichero binario.");
            }
        } catch (Exception e) {
            System.out.println("❌ Error en Acceso Aleatorio: " + e.getMessage());
        }
    }

    public static void leerProductoAleatorio(String rutaFichero, int posicion) {
        try (RandomAccessFile raf = new RandomAccessFile(rutaFichero, "r")) {
            long posicionByte = (posicion - 1) * LONGITUD_REGISTRO;

            if (posicionByte >= raf.length()) {
                System.out.println("⚠ La posición " + posicion + " no existe en el archivo.");
                return;
            }

            raf.seek(posicionByte);

            StringBuilder idBuffer = new StringBuilder();
            for (int i = 0; i < 2; i++) {
                idBuffer.append(raf.readChar());
            }

            int stock = raf.readInt();
            double precio = raf.readDouble();

            System.out.println("📖 [Lectura RAF Posición " + posicion + "] ID: " + idBuffer.toString().trim() + " | Stock: " + stock + " | Precio: " + precio + "€");

        } catch (Exception e) {
            System.out.println("❌ Error leyendo el registro aleatorio: " + e.getMessage());
        }
    }
}
