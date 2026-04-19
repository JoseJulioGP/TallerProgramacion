package restaurante.vista;

import restaurante.logica.*;
import restaurante.modelo.*;

public class FacturaImpresor
{
    private static final String NOMBRE_RESTAURANTE = "RESTAURANTE EL BUEN SABOR";
    private static final String DIRECCION = "Calle 15 #8-32, Valledupar";
    private static final String NIT = "900.123.456-7";
    private static final String FORMATO_ITEM = "%-20s x%-6d $%,.0f%n";

    public void imprimirFactura(Factura factura) {
        imprimirEncabezado();
        System.out.printf("FACTURA No. %03d%n", factura.getNumeroFactura());
        System.out.println("----------------------------------------");

        for (ItemPedido item : factura.getItems()) {
            System.out.printf(FORMATO_ITEM,
                    item.getProducto().getNombre(),
                    item.getCantidad(),
                    item.calcularSubtotal());
        }

        System.out.println("----------------------------------------");
        System.out.printf("Subtotal: %20s$%,.0f%n", "", factura.calcularSubtotal());

        if (factura.calcularDescuento() > 0) {
            System.out.printf("Descuento (5%%): %14s$%,.0f%n", "", -factura.calcularDescuento());
        }

        System.out.printf("IVA (19%%): %19s$%,.0f%n", "", factura.calcularIva());
        System.out.printf("Propina (10%%): %15s$%,.0f%n", "", factura.calcularPropina());
        System.out.println("----------------------------------------");
        System.out.printf("TOTAL: %23s$%,.0f%n", "", factura.calcularTotal());
    }

    public void imprimirEncabezado() {
        System.out.println("========================================");
        System.out.println("       " + NOMBRE_RESTAURANTE);
        System.out.println("    " + DIRECCION);
        System.out.println("    NIT: " + NIT);
        System.out.println("========================================");
    }
}
