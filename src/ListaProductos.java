public class ListaProductos {

    private NodoProducto primero;

    public ListaProductos() {
        this.primero = null;
    }

    public NodoProducto getPrimero() { return primero; }
    public void setPrimero(NodoProducto primero) { this.primero = primero; }

    /* =======================
       Métodos públicos (API)
       ======================= */

    public void insertarInicio(String nombre, double precio, String categoria,
                               String fechaVencimiento, int cantidad) {
        NodoProducto nuevo = crearNodo(nombre, precio, categoria, fechaVencimiento, cantidad);
        nuevo.setSiguiente(primero);
        primero = nuevo;
    }

    public void insertarFinal(String nombre, double precio, String categoria,
                              String fechaVencimiento, int cantidad) {
        NodoProducto nuevo = crearNodo(nombre, precio, categoria, fechaVencimiento, cantidad);

        if (estaVacia()) {
            primero = nuevo;
            return;
        }
        NodoProducto ultimo = obtenerUltimo();
        ultimo.setSiguiente(nuevo);
    }

    public NodoProducto buscarPorNombre(String nombreBuscado) {
        if (estaVacia()) return null;

        NodoProducto actual = primero;
        while (actual != null && !nombresIguales(actual.getProducto().getNombre(), nombreBuscado)) {
            actual = actual.getSiguiente();
        }
        return actual; // null si no se encontró
    }

    // Metodo de conveniencia para el menú (UI)
    public void imprimirResultadoBusqueda(String nombreBuscado) {
        NodoProducto encontrado = buscarPorNombre(nombreBuscado);
        if (encontrado != null) {
            System.out.println("Encontrado: " + encontrado.getProducto().getNombre());
        } else {
            System.out.println("No encontrado: " + nombreBuscado);
        }
    }

    public NodoProducto eliminar(String nombreEliminar) {
        if (estaVacia()) {
            System.out.println("La lista está vacía");
            return null;
        }

        NodoProducto anterior = null;
        NodoProducto actual = primero;

        while (actual != null && !nombresIguales(actual.getProducto().getNombre(), nombreEliminar)) {
            anterior = actual;
            actual = actual.getSiguiente();
        }

        if (actual == null) {
            System.out.println("El producto no fue encontrado");
            return null;
        }

        if (anterior == null) {
            primero = actual.getSiguiente();
        } else {
            anterior.setSiguiente(actual.getSiguiente());
        }

        System.out.println("Producto eliminado: " + actual.getProducto().getNombre());
        actual.setSiguiente(null);
        return actual;
    }

    public void mostrarLista() {
        if (estaVacia()) {
            System.out.println("La lista está vacía");
            return;
        }
        NodoProducto actual = primero;
        while (actual != null) {
            System.out.println(actual.getProducto());
            actual = actual.getSiguiente();
        }
    }

    public void reporteCostos() {
        if (estaVacia()) {
            System.out.println("La lista está vacía");
            return;
        }
        double total = 0.0;
        NodoProducto actual = primero;
        while (actual != null) {
            Producto p = actual.getProducto();
            double subtotal = p.getPrecio() * p.getCantidad();
            System.out.println(
                    p.getNombre() + " -> " + p.getCantidad() + " unidades, Costo total: " +
                            String.format("%.2f", subtotal)
            );
            total += subtotal;
            actual = actual.getSiguiente();
        }
        System.out.println("Costo acumulado de la lista: " + String.format("%.2f", total));
    }

    /* =======================
       Helpers privados (DRY)
       ======================= */

    private boolean estaVacia() { return primero == null; }

    private NodoProducto crearNodo(String nombre, double precio, String categoria,
                                   String fechaVencimiento, int cantidad) {
        Producto producto = new Producto(nombre, precio, categoria, fechaVencimiento, cantidad);
        return new NodoProducto(producto);
    }

    private NodoProducto obtenerUltimo() {
        NodoProducto temp = primero;
        while (temp.getSiguiente() != null) {
            temp = temp.getSiguiente();
        }
        return temp;
    }

    private boolean nombresIguales(String a, String b) {
        return a != null && b != null && a.equalsIgnoreCase(b.trim());
    }
}
