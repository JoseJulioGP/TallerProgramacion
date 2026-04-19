package restaurante.logica;

import restaurante.modelo.*;
import java.util.List;
import java.util.ArrayList;

public class Pedido
{
    private final List<ItemPedido> items = new ArrayList<>();

    public void agregarProducto(Producto producto, int cantidad) {
        items.add(new ItemPedido(producto, cantidad));
    }

    public boolean estaVacio() {
        return items.isEmpty();
    }

    public void limpiar() {
        items.clear();
    }

    public List<ItemPedido> getItems() {
        return new ArrayList<>(items);
    }
}
