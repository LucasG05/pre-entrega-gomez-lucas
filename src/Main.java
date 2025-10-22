import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        ArrayList<Producto> productosDB = obtenerProductosDeportivos();
        int idSiguiente = productosDB.size() + 1;
        int opcionUsuario;

        System.out.println("==============================================");
        System.out.println("   🏆 Bienvenido/a a TiendaRed - Tienda Deportiva");
        System.out.println("==============================================");

        boolean continuar = true;
        while (continuar) {
            System.out.println("""
          \nSeleccioná una opción:
          0 - Salir del sistema
          1 - Registrar nuevo producto
          2 - Ver listado de productos
          3 - Buscar producto por nombre
          4 - Modificar precio de un producto
          5 - Eliminar producto
          6 - Crear pedido (en desarrollo)
          """);

            System.out.print("👉 Opción elegida: ");
            try {
                opcionUsuario = Integer.parseInt(entrada.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Ingresá un número válido.");
                continue;
            }

            switch (opcionUsuario) {
                case 1 -> {
                    crearProducto(idSiguiente, productosDB);
                    idSiguiente++;
                }
                case 2 -> listarProductos(productosDB);
                case 3 -> buscarProductoPorNombre(productosDB);
                case 4 -> editarProducto(productosDB);
                case 5 -> borrarProducto(productosDB);
                case 6 -> System.out.println("🏗️ Funcionalidad en proceso...");
                case 0 -> {
                    System.out.println("\nGracias por visitar TiendaRed 🏅 ¡Hasta pronto!");
                    continuar = false;
                }
                default -> System.out.println("❌ Opción inválida. Intentá nuevamente.");
            }
        }
    }

    // ================== CREAR PRODUCTO ==================
    public static void crearProducto(int id, ArrayList<Producto> productos) {
        Scanner entrada = new Scanner(System.in);
        System.out.println("\n--- Registrar nuevo producto ---");

        System.out.print("Nombre del artículo deportivo: ");
        String nombre = entrada.nextLine();

        double precio = 0;
        int stock = 0;

        try {
            System.out.print("Precio del producto ($): ");
            precio = Double.parseDouble(entrada.nextLine());

            System.out.print("Cantidad en stock: ");
            stock = Integer.parseInt(entrada.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("⚠️ Error: ingreso no válido. No se creó el producto.");
            return;
        }

        productos.add(new Producto(id, nombre, precio, stock));
        System.out.println("✅ Producto agregado correctamente.");
        pausa();
    }

    // ================== LISTAR PRODUCTOS ==================
    public static void listarProductos(ArrayList<Producto> productos) {
        System.out.println("\n=================== LISTA DE PRODUCTOS ===================");

        if (productos.isEmpty()) {
            System.out.println("⚠️ No hay artículos registrados actualmente.");
        } else {
            System.out.printf("%-5s %-35s %-10s %-10s%n", "ID", "NOMBRE", "PRECIO", "STOCK");
            System.out.println("----------------------------------------------------------");
            for (Producto producto : productos) {
                System.out.printf("%-5d %-35s $%-9.2f %-10d%n",
                        producto.id, producto.nombre, producto.precio, producto.stock);
            }
        }

        System.out.println("==========================================================");
        pausa();
    }

    // ================== BUSCAR POR NOMBRE ==================
    public static void buscarProductoPorNombre(ArrayList<Producto> productos) {
        Scanner entrada = new Scanner(System.in);
        System.out.print("\n🔎 Ingrese el nombre o parte del nombre del producto: ");
        String busqueda = entrada.nextLine();
        ArrayList<Producto> encontrados = new ArrayList<>();

        for (Producto producto : productos) {
            if (coincide(producto.nombre, busqueda)) {
                encontrados.add(producto);
            }
        }

        listarProductos(encontrados);
    }

    // ================== EDITAR PRECIO ==================
    public static void editarProducto(List<Producto> productos) {
        Producto producto = obtenerProductoPorId(productos);
        if (producto == null) {
            System.out.println("⚠️ No se encontró un producto con ese ID.");
            return;
        }

        Scanner entrada = new Scanner(System.in);
        System.out.println("\nProducto seleccionado: " + producto.nombre);
        System.out.printf("Precio actual: $%.2f%n", producto.precio);
        System.out.print("Nuevo precio: ");

        try {
            double nuevoPrecio = Double.parseDouble(entrada.nextLine());
            if (nuevoPrecio < 0) {
                System.out.println("⚠️ El precio no puede ser negativo.");
                return;
            }
            producto.precio = nuevoPrecio;
            System.out.println("✅ Precio actualizado correctamente.");
        } catch (NumberFormatException e) {
            System.out.println("⚠️ Valor inválido. No se modificó el precio.");
        }

        pausa();
    }

    // ================== BORRAR PRODUCTO ==================
    public static void borrarProducto(List<Producto> productos) {
        Producto producto = obtenerProductoPorId(productos);
        if (producto == null) {
            System.out.println("⚠️ No se encontró un producto con ese ID.");
            return;
        }

        productos.remove(producto);
        System.out.println("🗑️ Producto eliminado correctamente.");
        pausa();
    }

    // ================== UTILIDADES ==================
    public static Producto obtenerProductoPorId(List<Producto> productos) {
        Scanner entrada = new Scanner(System.in);
        System.out.print("\nIngrese el ID del producto: ");
        int id;
        try {
            id = Integer.parseInt(entrada.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("⚠️ Ingresá un número válido.");
            return null;
        }

        for (Producto producto : productos) {
            if (producto.coincideId(id)) {
                return producto;
            }
        }
        return null;
    }

    public static boolean coincide(String textoCompleto, String textoParcial) {
        return textoCompleto.toLowerCase().contains(textoParcial.toLowerCase().trim());
    }

    public static void pausa() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("\nPresioná ENTER para continuar...");
        entrada.nextLine();
        for (int i = 0; i < 15; ++i) {
            System.out.println();
        }
    }

    // ================== PRODUCTOS POR DEFECTO ==================
    public static ArrayList<Producto> obtenerProductosDeportivos() {
        ArrayList<Producto> productos = new ArrayList<>();

        productos.add(new Producto(1, "Pelota de fútbol Adidas", 25999.99, 15));
        productos.add(new Producto(2, "Botines Nike Phantom GX", 89999.50, 8));
        productos.add(new Producto(3, "Camiseta Argentina 2024", 74999.00, 20));
        productos.add(new Producto(4, "Guantes de arquero Puma Ultra Grip", 45999.00, 10));
        productos.add(new Producto(5, "Short deportivo Under Armour", 21999.99, 25));
        productos.add(new Producto(6, "Zapatillas running Asics Gel Nimbus", 114999.00, 5));
        productos.add(new Producto(7, "Cono de entrenamiento naranja", 4999.00, 30));
        productos.add(new Producto(8, "Remera térmica Reebok", 18999.00, 18));
        productos.add(new Producto(9, "Canillera Adidas", 12999.00, 22));
        productos.add(new Producto(10, "Mochila deportiva Wilson", 31999.99, 12));

        return productos;
    }
}
