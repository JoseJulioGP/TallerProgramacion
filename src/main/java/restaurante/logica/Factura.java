package restaurante.logica;

import restaurante.modelo.*;
import java.util.List;

public class Factura
{
    private static final double TASA_IVA = 0.19;
    private static final double TASA_PROPINA = 0.10;
    private static final double TASA_DESCUENTO = 0.05;
    private static final double UMBRAL_PROPINA = 50000.0;
    private static final int MIN_PRODUCTOS_DESCUENTO = 3;

    private final List<ItemPedido> items;
    private final int numeroFactura;

    public Factura(List<ItemPedido> items, int numeroFactura) {
        this.items = items;
        this.numeroFactura = numeroFactura;
    }

    public double calcularSubtotal() {
        return items.stream().mapToDouble(ItemPedido::calcularSubtotal).sum();
    }

    public double calcularDescuento() {
        // Se aplica descuento si hay más de 3 tipos de productos diferentes [cite: 20]
        return (items.size() > MIN_PRODUCTOS_DESCUENTO) ? calcularSubtotal() * TASA_DESCUENTO : 0;
    }

    public double calcularIva() {
        // El IVA se calcula sobre el subtotal tras el descuento [cite: 21]
        return (calcularSubtotal() - calcularDescuento()) * TASA_IVA;
    }

    public double calcularPropina() {
        // La propina aplica si el subtotal supera los $50.000 [cite: 22]
        return (calcularSubtotal() > UMBRAL_PROPINA) ? calcularSubtotal() * TASA_PROPINA : 0;
    }

    public double calcularTotal() {
        return calcularSubtotal() - calcularDescuento() + calcularIva() + calcularPropina();
    }

    public int getNumeroFactura() { return numeroFactura; }
    public List<ItemPedido> getItems() { return items; }
}
