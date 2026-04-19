package restaurante;

import java.util.Scanner;
import restaurante.modelo.*;
import restaurante.logica.*;
import restaurante.servicio.*;
import restaurante.vista.*;

public class Main {
    private final MenuRestaurante menu = new MenuRestaurante();
    private final Pedido pedidoActual = new Pedido();
    private final FacturaImpresor impresor = new FacturaImpresor();
    private final Scanner lector = new Scanner(System.in);
    private int contadorFacturas = 1;

    public static void main(String[] args) {
        new Main().ejecutar();
    }

    public void ejecutar() {
        int opcion;
        do {
            mostrarMenuPrincipal();
            opcion = leerEntero("Seleccione una opcion: ");
            procesarOpcion(opcion);
        } while (opcion != 0);
    }

    private void mostrarMenuPrincipal() {
        impresor.imprimirEncabezado();
        System.out.println("1. Ver carta");
        System.out.println("2. Agregar producto al pedido");
        System.out.println("3. Ver pedido actual");
        System.out.println("4. Generar factura");
        System.out.println("5. Nueva mesa");
        System.out.println("0. Salir");
        System.out.println("========================================");
    }

    private void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1 -> mostrarCarta();
            case 2 -> agregarProducto();
            case 3 -> mostrarResumenPedido();
            case 4 -> generarFactura();
            case 5 -> pedidoActual.limpiar();
            case 0 -> System.out.println("Gracias por su visita.");
            default -> System.out.println("Opcion no valida.");
        }
    }

    private void mostrarCarta() {
        System.out.println("\n#  Producto             Precio");
        for (int i = 0; i < menu.getProductos().size(); i++) {
            Producto p = menu.getProductos().get(i);
            System.out.printf("%d. %-20s $%,.0f%n", i + 1, p.getNombre(), p.getPrecio());
        }
    }

    private void agregarProducto() {
        mostrarCarta();
        int num = leerEntero("Numero del producto: ") - 1;
        if (num >= 0 && num < menu.getProductos().size()) {
            int cant = leerEntero("Cantidad: ");
            pedidoActual.agregarProducto(menu.getProductos().get(num), cant);
            System.out.println("Producto agregado.");
        }
    }

    private void generarFactura() {
        if (pedidoActual.estaVacio()) {
            System.out.println("Error: El pedido esta vacio.");
            return;
        }
        Factura factura = new Factura(pedidoActual.getItems(), contadorFacturas++);
        impresor.imprimirFactura(factura);
        pedidoActual.limpiar();
    }

    private void mostrarResumenPedido() {
        if (pedidoActual.estaVacio()) {
            System.out.println("El pedido esta vacio.");
            return;
        }
        for (ItemPedido item : pedidoActual.getItems()) {
            System.out.printf("%-20s x%d%n", item.getProducto().getNombre(), item.getCantidad());
        }
    }

    private int leerEntero(String mensaje) {
        System.out.print(mensaje);
        while (!lector.hasNextInt()) {
            lector.next();
            System.out.print("Por favor, ingrese un numero: ");
        }
        return lector.nextInt();
    }
}
