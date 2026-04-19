package restaurante.servicio;

import restaurante.modelo.*;
import java.util.List;
import java.util.ArrayList;

public class MenuRestaurante
{
    private final List<Producto> productos = new ArrayList<>();

    public MenuRestaurante() {
        // Inicialización de la carta
        productos.add(new Producto("Bandeja Paisa", 32000));
        productos.add(new Producto("Sancocho de Gallina", 28000));
        productos.add(new Producto("Arepa con Huevo", 8000));
        productos.add(new Producto("Jugo Natural", 7000));
        productos.add(new Producto("Gaseosa", 4500));
        productos.add(new Producto("Cerveza Poker", 6000));
        productos.add(new Producto("Agua Panela", 3500));
        productos.add(new Producto("Arroz con Pollo", 25000));
    }

    public List<Producto> getProductos() { return productos; }
}
