import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        ListaProductos lista = new ListaProductos();
        menu(lista);
        sc.close();
    }

    public static void menu(ListaProductos lista) {
        int opcion;

        do {
            System.out.println("\n===== MENÚ DE INVENTARIO =====");
            System.out.println("1. Insertar producto al inicio");
            System.out.println("2. Insertar producto al final");
            System.out.println("3. Buscar producto por nombre");
            System.out.println("4. Eliminar producto por nombre");
            System.out.println("5. Mostrar todos los productos");
            System.out.println("6. Reporte de costos");
            System.out.println("7. Agregar imagen a un producto");
            System.out.println("8. Mostrar imágenes de un producto");
            System.out.println("9. Modificar producto");
            System.out.println("0. Salir");
            opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1 -> insertar(lista, true);
                case 2 -> insertar(lista, false);
                case 3 -> {
                    String nombre = leerTextoNoVacio("Ingrese el nombre a buscar: ");
                    lista.imprimirResultadoBusqueda(nombre);
                }
                case 4 -> {
                    String nombre = leerTextoNoVacio("Ingrese el nombre a eliminar: ");
                    lista.eliminar(nombre);
                }
                case 5 -> lista.mostrarLista();
                case 6 -> lista.reporteCostos();
                case 7 -> {
                    String nombre = leerTextoNoVacio("Ingrese el nombre del producto: ");
                    NodoProducto nodo = lista.buscarPorNombre(nombre);
                    if (nodo == null) {
                        System.out.println("❌ Producto no encontrado.");
                    } else {
                        String ruta = leerTextoNoVacio("Ruta de la imagen (ej: imagenes/pan.jpg): ");
                        nodo.getProducto().agregarImagen(ruta);
                        System.out.println("✅ Imagen agregada a " + nodo.getProducto().getNombre());
                    }
                }
                case 8 -> {
                    String nombre = leerTextoNoVacio("Ingrese el nombre del producto: ");
                    NodoProducto nodo = lista.buscarPorNombre(nombre);
                    if (nodo == null) {
                        System.out.println("❌ Producto no encontrado.");
                    } else {
                        nodo.getProducto().mostrarImagenes();
                    }
                }
                case 9 -> {
                    String nombre = leerTextoNoVacio("Ingrese el nombre del producto a modificar: ");
                    NodoProducto nodo = lista.buscarPorNombre(nombre);
                    if (nodo == null) {
                        System.out.println("❌ Producto no encontrado.");
                    } else {
                        Producto p = nodo.getProducto();
                        System.out.println("Producto actual: " + p);
                        System.out.println("Ingrese los nuevos valores (deje vacío para mantener el actual):");

                        String nuevoNombre = leerTexto("Nuevo nombre: ");
                        if (!nuevoNombre.isBlank()) p.setNombre(nuevoNombre);

                        String nuevoPrecio = leerTexto("Nuevo precio: ");
                        if (!nuevoPrecio.isBlank()) {
                            try {
                                p.setPrecio(Double.parseDouble(nuevoPrecio.replace(',', '.')));
                            } catch (NumberFormatException e) {
                                System.out.println("❌ Precio inválido. Se mantiene el valor actual.");
                            }
                        }

                        String nuevaCategoria = leerTexto("Nueva categoría: ");
                        if (!nuevaCategoria.isBlank()) p.setCategoria(nuevaCategoria);

                        String nuevaFecha = leerTexto("Nueva fecha de vencimiento: ");
                        if (!nuevaFecha.isBlank()) p.setFechaVencimiento(nuevaFecha); // <-- nombre correcto

                        String nuevaCantidad = leerTexto("Nueva cantidad: ");
                        if (!nuevaCantidad.isBlank()) {
                            try {
                                p.setCantidad(Integer.parseInt(nuevaCantidad));
                            } catch (NumberFormatException e) {
                                System.out.println("❌ Cantidad inválida. Se mantiene el valor actual.");
                            }
                        }

                        System.out.println("✅ Producto actualizado.");
                    }
                }
                case 0 -> System.out.println("Saliendo del programa...");
                default -> System.out.println("Opción inválida, intente de nuevo.");
            }
        } while (opcion != 0);
    }

    /* -------------------------
       Entradas robustas (safe)
       ------------------------- */

    private static int leerEntero(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim();
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("❌ Debe ingresar un número entero. Intente nuevamente.");
            }
        }
    }

    private static double leerDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim().replace(',', '.'); // permite coma o punto
            try {
                return Double.parseDouble(s);
            } catch (NumberFormatException e) {
                System.out.println("❌ Debe ingresar un número (use punto o coma). Intente nuevamente.");
            }
        }
    }

    private static String leerTextoNoVacio(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim();
            if (!s.isEmpty()) return s;
            System.out.println("❌ El texto no puede estar vacío.");
        }
    }

    private static String leerTexto(String prompt) {
        System.out.print(prompt);
        return sc.nextLine().trim();
    }

    /* -------------------------
       Flujo de inserción común
       ------------------------- */

    private static void insertar(ListaProductos lista, boolean alInicio) {
        String nombre = leerTextoNoVacio("Nombre: ");
        double precio = leerDouble("Precio: ");
        String categoria = leerTextoNoVacio("Categoría: ");
        String fecha = leerTextoNoVacio("Fecha de vencimiento: ");
        int cantidad = leerEntero("Cantidad: ");

        if (alInicio) {
            lista.insertarInicio(nombre, precio, categoria, fecha, cantidad);
            System.out.println("✅ Producto insertado al INICIO.");
        } else {
            lista.insertarFinal(nombre, precio, categoria, fecha, cantidad);
            System.out.println("✅ Producto insertado al FINAL.");
        }
    }
}
