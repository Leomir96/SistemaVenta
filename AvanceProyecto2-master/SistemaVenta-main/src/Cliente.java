public class Cliente {

    private String nombre;
    private int prioridad;
    private ListaProductos carrito;

    public Cliente(String nombre, int prioridad) {
        this.nombre = nombre;
        this.prioridad = Math.max(1, Math.min(prioridad, 3));
        this.carrito = new ListaProductos();
    }

    public String getNombre() { return nombre; }
    public int getPrioridad() { return prioridad; }
    public ListaProductos getCarrito() { return carrito; }

    public void agregarAlCarrito(Producto p) {
        carrito.insertarFinal(
                p.getNombre(),
                p.getPrecio(),
                p.getCategoria(),
                p.getFechaVencimiento(),
                p.getCantidad()
        );
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nombre='" + nombre + '\'' +
                ", prioridad=" + prioridad +
                '}';
    }
}
