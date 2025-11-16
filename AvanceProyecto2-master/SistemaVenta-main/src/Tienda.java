public class Tienda {
    
    private ArbolProductos inventario;
    private ColaClientes colaClientes;
    
    public Tienda() {
        this.inventario = new ArbolProductos();
        this.colaClientes = new ColaClientes();
    }
    
    public void agregarProductoAlInventario(Producto p) {
        inventario.insertar(p);
        System.out.println("Producto agregado al inventario: " + p.getNombre());
    }
    
    public Producto buscarProductoEnInventario(String nombre) {
        return inventario.buscar(nombre);
    }
    
    public void mostrarInventario() {
        System.out.println("\n===== INVENTARIO DE LA TIENDA =====");
        inventario.imprimirInOrden();
        System.out.println("====================================\n");
    }
    
    public void agregarClienteACola(Cliente cliente) {
        colaClientes.encolar(cliente);
        System.out.println("Cliente agregado a la cola: " + cliente.getNombre() + 
                         " (Prioridad: " + cliente.getPrioridad() + ")");
    }
    
    public Cliente atenderSiguienteCliente() {
        if (colaClientes.estaVacia()) {
            System.out.println("No hay clientes en la cola para atender.");
            return null;
        }
        
        Cliente clienteAtendido = colaClientes.atender();
        generarFactura(clienteAtendido);
        return clienteAtendido;
    }
    
    public Cliente verSiguienteCliente() {
        if (colaClientes.estaVacia()) {
            System.out.println("No hay clientes en la cola.");
            return null;
        }
        
        Cliente siguiente = colaClientes.verSiguiente();
        System.out.println("Siguiente cliente: " + siguiente.getNombre() + 
                         " (Prioridad: " + siguiente.getPrioridad() + ")");
        return siguiente;
    }
    
    private void generarFactura(Cliente cliente) {
        System.out.println("\n========================================");
        System.out.println("           FACTURA DE VENTA            ");
        System.out.println("========================================");
        System.out.println("Cliente: " + cliente.getNombre());
        
        String tipoCliente = "";
        switch(cliente.getPrioridad()) {
            case 1: tipoCliente = "Basico"; break;
            case 2: tipoCliente = "Afiliado"; break;
            case 3: tipoCliente = "Premium"; break;
        }
        System.out.println("Tipo: " + tipoCliente);
        System.out.println("========================================");
        System.out.println("Productos comprados:");
        System.out.println("----------------------------------------");
        
        cliente.getCarrito().reporteCostos();
        
        System.out.println("========================================");
        System.out.println("       Gracias por su compra!          ");
        System.out.println("========================================\n");
    }
    
    public boolean colaVacia() {
        return colaClientes.estaVacia();
    }
    
    public ArbolProductos getInventario() {
        return inventario;
    }
    
    public ColaClientes getColaClientes() {
        return colaClientes;
    }
}
