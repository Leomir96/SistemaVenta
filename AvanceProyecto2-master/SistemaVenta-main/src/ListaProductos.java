public class ListaProductos {

    private Producto primero;

    public ListaProductos() {
        primero = null;
    }

    public boolean estaVacia() { return primero == null; }

    private Producto crearProducto(String nombre, double precio, String categoria,
                                   String fecha, int cantidad) {
        return new Producto(nombre, precio, categoria, fecha, cantidad);
    }

    public void insertarInicio(String nombre, double precio, String categoria,
                               String fecha, int cantidad) {
        Producto nuevo = crearProducto(nombre, precio, categoria, fecha, cantidad);
        nuevo.setSiguiente(primero);
        primero = nuevo;
    }

    public void insertarFinal(String nombre, double precio, String categoria,
                              String fecha, int cantidad) {

        Producto nuevo = crearProducto(nombre, precio, categoria, fecha, cantidad);

        if (estaVacia()) {
            primero = nuevo;
            return;
        }

        Producto aux = primero;
        while (aux.getSiguiente() != null) {
            aux = aux.getSiguiente();
        }
        aux.setSiguiente(nuevo);
    }

    public Producto buscar(String nombre) {
        Producto aux = primero;
        while (aux != null && !aux.getNombre().equalsIgnoreCase(nombre)) {
            aux = aux.getSiguiente();
        }
        return aux;
    }

    public void imprimirResultadoBusqueda(String nombre) {
        Producto p = buscar(nombre);
        if (p == null) {
            System.out.println("No encontrado.");
        } else {
            System.out.println("Resultado:");
            System.out.println(p);
        }
    }

    public Producto eliminar(String nombre) {
        if (estaVacia()) return null;

        Producto actual = primero;
        Producto anterior = null;

        while (actual != null && !actual.getNombre().equalsIgnoreCase(nombre)) {
            anterior = actual;
            actual = actual.getSiguiente();
        }

        if (actual == null) return null;

        if (anterior == null) {
            primero = actual.getSiguiente();
        } else {
            anterior.setSiguiente(actual.getSiguiente());
        }

        actual.setSiguiente(null);
        return actual;
    }

    public void mostrarLista() {
        if (estaVacia()) {
            System.out.println("La lista esta vacia");
            return;
        }

        Producto aux = primero;
        while (aux != null) {
            System.out.println(aux);
            aux = aux.getSiguiente();
        }
    }

    public void reporteCostos() {
        if (estaVacia()) {
            System.out.println("La lista esta vacia");
            return;
        }

        double total = 0;
        Producto aux = primero;

        while (aux != null) {
            double subtotal = aux.getPrecio() * aux.getCantidad();
            System.out.println(aux.getNombre() + ": " + subtotal);
            total += subtotal;
            aux = aux.getSiguiente();
        }

        System.out.println("Costo total acumulado: " + total);
    }
}
