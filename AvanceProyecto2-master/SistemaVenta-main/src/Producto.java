import java.util.ArrayList;
import java.util.List;

public class Producto {

    private String nombre;
    private double precio;
    private String categoria;
    private String fechaVencimiento;
    private int cantidad;
    private final List<String> listaImagenes = new ArrayList<>();
    private Producto siguiente;

    public Producto(String nombre, double precio, String categoria,
                    String fechaVencimiento, int cantidad) {
        this.nombre = nombre;
        this.precio = Math.max(precio, 0);
        this.categoria = categoria;
        this.fechaVencimiento = fechaVencimiento;
        this.cantidad = Math.max(cantidad, 0);
        this.siguiente = null;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = Math.max(precio, 0); }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public String getFechaVencimiento() { return fechaVencimiento; }
    public void setFechaVencimiento(String fechaVencimiento) { this.fechaVencimiento = fechaVencimiento; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = Math.max(cantidad, 0); }

    public List<String> getListaImagenes() { return listaImagenes; }
    public void agregarImagen(String ruta) { listaImagenes.add(ruta); }

    public Producto getSiguiente() { return siguiente; }
    public void setSiguiente(Producto sig) { this.siguiente = sig; }

    @Override
    public String toString() {
        return "Producto{" +
                "nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", categoria='" + categoria + '\'' +
                ", fechaVencimiento='" + fechaVencimiento + '\'' +
                ", cantidad=" + cantidad +
                ", imagenes=" + listaImagenes +
                '}';
    }
}
