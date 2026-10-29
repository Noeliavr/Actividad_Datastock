package es.iesisidra.datastock.modelo;

/**
 * Clase Proveedor: Información del suministrador de productos.
 */
public class Proveedor {

    private String cif;
    private String nombreEmpresa;
    private String telefono;

    public Proveedor() {}

    public Proveedor(String cif, String nombreEmpresa, String telefono) {
        this.cif = cif;
        this.nombreEmpresa = nombreEmpresa;
        this.telefono = telefono;
    }

    public String getCif() { return cif; }
    public void setCif(String cif) { this.cif = cif; }

    public String getNombreEmpresa() { return nombreEmpresa; }
    public void setNombreEmpresa(String nombreEmpresa) { this.nombreEmpresa = nombreEmpresa; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    @Override
    public String toString() {
        return "Proveedor [CIF=" + cif + ", Empresa=" + nombreEmpresa + ", Tel=" + telefono + "]";
    }
}
