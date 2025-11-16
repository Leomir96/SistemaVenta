import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in);
    private static Tienda tienda;

    public static void main(String[] args) {
        tienda = new Tienda();
        menu();
        sc.close();
    }

    public static void menu() {
        int opcion;

        do {
            System.out.println("\n===============================================");
            System.out.println("    SISTEMA DE GESTION DE VENTAS EN LINEA     ");
            System.out.println("===============================================");
            System.out.println("1. Agregar producto al inventario");
            System.out.println("2. Mostrar inventario completo");
            System.out.println("3. Buscar producto en inventario");
            System.out.println("4. Agregar cliente a la cola");
            System.out.println("5. Ver siguiente cliente en la cola");
            System.out.println("6. Atender siguiente cliente");
            System.out.println("7. Agregar producto al carrito de un cliente");
            System.out.println("0. Salir");
            System.out.println("===============================================");
            
            opcion = leerEntero("Seleccione una opcion: ");

            switch (opcion) {
                case 1 -> agregarProductoInventario();
                case 2 -> tienda.mostrarInventario();
                case 3 -> buscarProducto();
                case 4 -> agregarCliente();
                case 5 -> tienda.verSiguienteCliente();
                case 6 -> tienda.atenderSiguienteCliente();
                case 7 -> agregarProductoACarrito();
                case 0 -> System.out.println("\nGracias por usar el sistema. Adios!");
                default -> System.out.println("Opcion invalida. Intente de nuevo.");
            }
        } while (opcion != 0);
    }

    private static void agregarProductoInventario() {
        System.out.println("\n--- AGREGAR PRODUCTO AL INVENTARIO ---");
        
        String nombre = leerTextoNoVacio("Nombre del producto: ");
        double precio = leerDouble("Precio del producto: ");
        String categoria = leerTextoNoVacio("Categoria: ");
        String fechaVencimiento = leerTextoNoVacio("Fecha de vencimiento (dd/mm/aaaa): ");
        int cantidad = leerEntero("Cantidad en stock: ");
        
        Producto producto = new Producto(nombre, precio, categoria, fechaVencimiento, cantidad);
        tienda.agregarProductoAlInventario(producto);
        
        System.out.println("Producto agregado exitosamente!");
    }

    private static void buscarProducto() {
        System.out.println("\n--- BUSCAR PRODUCTO EN INVENTARIO ---");
        
        String nombre = leerTextoNoVacio("Ingrese el nombre del producto: ");
        Producto encontrado = tienda.buscarProductoEnInventario(nombre);
        
        if (encontrado == null) {
            System.out.println("Producto no encontrado en el inventario.");
        } else {
            System.out.println("\nProducto encontrado:");
            System.out.println(encontrado);
        }
    }

    private static void agregarCliente() {
        System.out.println("\n--- AGREGAR CLIENTE A LA COLA ---");
        
        String nombre = leerTextoNoVacio("Nombre del cliente: ");
        
        System.out.println("Seleccione el tipo de cliente:");
        System.out.println("1. Basico (Prioridad 1)");
        System.out.println("2. Afiliado (Prioridad 2)");
        System.out.println("3. Premium (Prioridad 3)");
        
        int prioridad = leerEntero("Ingrese la prioridad: ");
        
        Cliente cliente = new Cliente(nombre, prioridad);
        tienda.agregarClienteACola(cliente);
        
        System.out.println("Cliente agregado exitosamente!");
        System.out.println("Nota: El cliente tiene un carrito vacio. Use la opcion 7 para agregar productos.");
    }

    private static void agregarProductoACarrito() {
        System.out.println("\n--- AGREGAR PRODUCTO AL CARRITO ---");
        
        if (tienda.colaVacia()) {
            System.out.println("No hay clientes en la cola.");
            System.out.println("Primero debe agregar un cliente (opcion 4).");
            return;
        }
        
        Cliente cliente = tienda.verSiguienteCliente();
        
        System.out.println("\nAgregando productos al carrito de: " + cliente.getNombre());
        System.out.println("Ingrese el nombre del producto a agregar (o 'fin' para terminar):");
        
        boolean continuar = true;
        
        while (continuar) {
            String nombreProducto = leerTexto("Producto: ");
            
            if (nombreProducto.equalsIgnoreCase("fin")) {
                continuar = false;
                System.out.println("Productos agregados al carrito de " + cliente.getNombre());
                continue;
            }
            
            Producto producto = tienda.buscarProductoEnInventario(nombreProducto);
            
            if (producto == null) {
                System.out.println("Producto '" + nombreProducto + "' no encontrado en el inventario.");
                System.out.println("Intente con otro producto o escriba 'fin' para terminar.");
            } else {
                System.out.println("Producto encontrado: " + producto.getNombre() + 
                                 " - Precio: $" + producto.getPrecio() + 
                                 " - Stock disponible: " + producto.getCantidad());
                
                int cantidadDeseada = leerEntero("Cantidad a comprar: ");
                
                if (cantidadDeseada > producto.getCantidad()) {
                    System.out.println("Stock insuficiente. Solo hay " + producto.getCantidad() + " unidades disponibles.");
                    System.out.println("Desea comprar las " + producto.getCantidad() + " unidades disponibles? (s/n)");
                    String respuesta = leerTexto("Respuesta: ");
                    
                    if (respuesta.equalsIgnoreCase("s") || respuesta.equalsIgnoreCase("si")) {
                        cantidadDeseada = producto.getCantidad();
                    } else {
                        System.out.println("Producto no agregado al carrito.");
                        continue;
                    }
                }
                
                Producto productoCarrito = new Producto(
                    producto.getNombre(),
                    producto.getPrecio(),
                    producto.getCategoria(),
                    producto.getFechaVencimiento(),
                    cantidadDeseada
                );
                
                cliente.agregarAlCarrito(productoCarrito);
                
                System.out.println("Producto agregado al carrito: " + cantidadDeseada + 
                                 " x " + producto.getNombre());
                
                producto.setCantidad(producto.getCantidad() - cantidadDeseada);
                System.out.println("Stock actualizado. Quedan " + producto.getCantidad() + 
                                 " unidades de " + producto.getNombre());
            }
        }
    }

    private static int leerEntero(String prompt) {
        while (true) {
            System.out.print(prompt);
            String entrada = sc.nextLine().trim();
            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un numero entero. Intente nuevamente.");
            }
        }
    }

    private static double leerDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String entrada = sc.nextLine().trim().replace(',', '.');
            try {
                double valor = Double.parseDouble(entrada);
                if (valor < 0) {
                    System.out.println("Error: El valor no puede ser negativo.");
                    continue;
                }
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un numero valido. Intente nuevamente.");
            }
        }
    }

    private static String leerTextoNoVacio(String prompt) {
        while (true) {
            System.out.print(prompt);
            String texto = sc.nextLine().trim();
            if (!texto.isEmpty()) {
                return texto;
            }
            System.out.println("Error: El texto no puede estar vacio. Intente nuevamente.");
        }
    }

    private static String leerTexto(String prompt) {
        System.out.print(prompt);
        return sc.nextLine().trim();
    }
}
