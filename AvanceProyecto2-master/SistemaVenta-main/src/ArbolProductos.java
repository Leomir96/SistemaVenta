public class ArbolProductos {
    private NodoArbol raiz;

    private static class NodoArbol {
        Producto producto;
        NodoArbol izq, der;

        NodoArbol(Producto p) {
            producto = p;
        }
    }

    public void insertar(Producto p) {
        raiz = insertarRec(raiz, p);
    }

    private NodoArbol insertarRec(NodoArbol nodo, Producto p) {
        if (nodo == null) return new NodoArbol(p);

        if (p.getNombre().compareToIgnoreCase(nodo.producto.getNombre()) < 0)
            nodo.izq = insertarRec(nodo.izq, p);
        else
            nodo.der = insertarRec(nodo.der, p);

        return nodo;
    }

    public Producto buscar(String nombre) {
        NodoArbol aux = raiz;
        while (aux != null) {
            int cmp = nombre.compareToIgnoreCase(aux.producto.getNombre());
            if (cmp == 0) return aux.producto;
            aux = (cmp < 0) ? aux.izq : aux.der;
        }
        return null;
    }

    public void imprimirInOrden() {
        imprimirInOrdenRec(raiz);
    }

    private void imprimirInOrdenRec(NodoArbol nodo) {
        if (nodo != null) {
            imprimirInOrdenRec(nodo.izq);
            System.out.println(nodo.producto);
            imprimirInOrdenRec(nodo.der);
        }
    }
}
