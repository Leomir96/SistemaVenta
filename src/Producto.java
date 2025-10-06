import java.util.ArrayList;
import java.util.List;

public class Producto {
    // Atributos
    private String nombre;
    private double precio;
    private String categoria;
    private String fechaVencimiento; // corregido el nombre
    private int cantidad;
    private final List<String> listaImagenes; // inmutable en la referencia

    // Constructor
    public Producto(String nombre, double precio, String categoria, String fechaVencimiento, int cantidad) {
        this.nombre = nombre;
        setPrecio(precio);                 // valida
        this.categoria = categoria;
        this.fechaVencimiento = fechaVencimiento;
        setCantidad(cantidad);             // valida
        this.listaImagenes = new ArrayList<>();
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) {
        if (precio < 0) {
            System.out.println("⚠ Precio negativo no permitido. Se mantiene el valor actual: " + this.precio);
            return;
        }
        this.precio = precio;
    }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public String getFechaVencimiento() { return fechaVencimiento; }
    public void setFechaVencimiento(String fechaVencimiento) { this.fechaVencimiento = fechaVencimiento; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) {
        if (cantidad < 0) {
            System.out.println("⚠ Cantidad negativa no permitida. Se mantiene el valor actual: " + this.cantidad);
            return;
        }
        this.cantidad = cantidad;
    }

    public List<String> getListaImagenes() { return listaImagenes; }
    public void setListaImagenes(List<String> listaImagenes) {
        this.listaImagenes.clear();
        if (listaImagenes != null) this.listaImagenes.addAll(listaImagenes);
    }

    // Metodo para agregar imagen
    public void agregarImagen(String rutaImagen) {
        if (rutaImagen == null || rutaImagen.isBlank()) {
            System.out.println("⚠ Ruta de imagen vacía. No se agregó.");
            return;
        }
        this.listaImagenes.add(rutaImagen.trim());
    }

    // Muestra todas las imágenes registradas para el producto
    public void mostrarImagenes() {
        if (listaImagenes.isEmpty()) {
            System.out.println("No hay imágenes registradas para este producto.");
        } else {
            System.out.println("Imágenes del producto " + nombre + ":");
            for (String ruta : listaImagenes) {
                System.out.println(" - " + ruta);
            }
        }
    }

    // Actualiza campos; si el parámetro viene null o vacío (en Strings), mantiene el valor actual.
    public void actualizarCampos(String nuevoNombre, Double nuevoPrecio,
                                 String nuevaCategoria, String nuevaFechaVencimiento,
                                 Integer nuevaCantidad) {
        if (nuevoNombre != null && !nuevoNombre.trim().isEmpty()) this.nombre = nuevoNombre.trim();
        if (nuevoPrecio != null && nuevoPrecio >= 0) this.precio = nuevoPrecio;
        if (nuevaCategoria != null && !nuevaCategoria.trim().isEmpty()) this.categoria = nuevaCategoria.trim();
        if (nuevaFechaVencimiento != null && !nuevaFechaVencimiento.trim().isEmpty()) this.fechaVencimiento = nuevaFechaVencimiento.trim();
        if (nuevaCantidad != null && nuevaCantidad >= 0) this.cantidad = nuevaCantidad;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", categoria='" + categoria + '\'' +
                ", fechaVencimiento='" + fechaVencimiento + '\'' +
                ", cantidad=" + cantidad +
                ", listaImagenes=" + listaImagenes +
                '}';
    }
}
