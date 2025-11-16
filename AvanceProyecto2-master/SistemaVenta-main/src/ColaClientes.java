public class ColaClientes {

    private NodoCliente frente;
    private NodoCliente fin;

    private static class NodoCliente {
        Cliente cliente;
        NodoCliente siguiente;

        NodoCliente(Cliente c) {
            cliente = c;
            siguiente = null;
        }
    }

    public boolean estaVacia() { return frente == null; }

    public void encolar(Cliente c) {
        NodoCliente nuevo = new NodoCliente(c);

        if (estaVacia()) {
            frente = fin = nuevo;
            return;
        }

        if (c.getPrioridad() > frente.cliente.getPrioridad()) {
            nuevo.siguiente = frente;
            frente = nuevo;
            return;
        }

        NodoCliente aux = frente;
        while (aux.siguiente != null &&
                aux.siguiente.cliente.getPrioridad() >= c.getPrioridad()) {
            aux = aux.siguiente;
        }

        nuevo.siguiente = aux.siguiente;
        aux.siguiente = nuevo;

        if (nuevo.siguiente == null) fin = nuevo;
    }

    public Cliente atender() {
        if (estaVacia()) return null;

        Cliente c = frente.cliente;
        frente = frente.siguiente;
        if (frente == null) fin = null;
        return c;
    }

    public Cliente verSiguiente() {
        return estaVacia() ? null : frente.cliente;
    }
}
