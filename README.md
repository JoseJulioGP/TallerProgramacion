# 🍽️ Restaurante El Buen Sabor - Refactorización a Código Limpio

Este proyecto consiste en la refactorización integral de un sistema de facturación para el restaurante "El Buen Sabor". El código original, aunque funcional, presentaba múltiples "malas prácticas" que lo hacían difícil de mantener y escalar. Siguiendo los principios de **Robert C. Martin (Clean Code)** y la guía del **Ing. Alfredo Bautista**, el sistema ha sido transformado en un software profesional orientado a objetos.

## 🛠️ Problemas Identificados (Deuda Técnica)
Antes de la refactorización, el sistema sufría de:
- **Arrays Paralelos:** Los datos de productos, precios y cantidades estaban dispersos en arreglos independientes, dificultando la integridad de la información.
- **God Object (Datos.java):** Una clase con variables globales `static` que rompía el encapsulamiento.
- **Magic Numbers:** Uso de valores literales como `0.19` o `50000` sin contexto claro.
- **Efectos Secundarios:** Métodos que realizaban cambios de estado ocultos (ej. `validar()` modificaba totales).
- **Código Espagueti:** Lógica de negocio, cálculos e impresión mezclados en un solo flujo.

## 🚀 Proceso de Refactorización (Niveles 1-5)

### 1. Nombres Significativos
Se eliminaron variables crípticas como `p[]`, `nom[]` y `c[]`. Ahora se utilizan nombres descriptivos como `precio`, `nombreProducto` y `cantidad`.

### 2. Funciones Limpias y SRP
Se aplicó el **Principio de Responsabilidad Única (SRP)**. Las funciones ahora son pequeñas y realizan una sola tarea:
- `Factura.java` solo se encarga de los cálculos matemáticos.
- `FacturaImpresor.java` solo se encarga de la salida por consola.

### 3. Eliminación de Comentarios Redundantes
Se eliminó el código muerto y los comentarios que explicaban el "qué" (ej. `// fin del if`). El código actual es **autodocumentado**.

### 4. Diseño Orientado a Objetos (Eliminación de Datos.java)
Se implementó un modelo de dominio real:
- **Producto:** Encapsula nombre y precio.
- **ItemPedido:** Asocia un producto con una cantidad específica.
- **Factura:** Orquesta la lógica de cobro.

### 5. Constantes y Configuración
Se centralizaron las reglas de negocio mediante constantes:
- `TASA_IVA = 0.19`
- `UMBRAL_PROPINA = 50000`
- `TASA_DESCUENTO = 0.05`

## 🏗️ Arquitectura del Proyecto (Capas)
El proyecto está organizado en paquetes para asegurar la separación de preocupaciones:

- `restaurante.modelo`: Clases de datos (`Producto`, `ItemPedido`).
- `restaurante.logica`: Reglas de negocio y cálculos (`Pedido`, `Factura`).
- `restaurante.servicios`: Gestión de la carta del restaurante (`MenuRestaurante`).
- `restaurante.vista`: Interfaz de usuario e impresión (`FacturaImpresor`).
- `restaurante`: Clase `Main` (Orquestador).

## 📝 Preguntas de Reflexión

### 1. ¿Qué parte fue más difícil de descifrar del código original?
Lo más complejo fue rastrear la relación entre los **arrays paralelos**. Al no existir un objeto que agrupara los datos de un producto, cualquier cambio en el índice de un arreglo ponía en riesgo la estabilidad de todo el sistema.

### 2. ¿Cuál de las 34 malas prácticas te pareció la más peligrosa?
Los **efectos secundarios ocultos** en funciones de validación. En un entorno real, una función que parece solo "revisar" pero que "modifica" datos globales puede causar errores financieros catastróficos que son casi imposibles de rastrear en producción.

### 3. ¿Qué pasaría si el sistema tuviera 50.000 líneas?
En el código sucio, agregar un nuevo impuesto o cambiar el nombre del restaurante requeriría buscar y reemplazar en cientos de archivos con riesgo de error humano. En el código refactorizado, solo se cambiaría **una constante** en un solo lugar.

### 4. ¿Cómo conectas este ejercicio con casos reales como Knight Capital?
Knight Capital perdió $440M por código muerto y configuraciones obsoletas que no se limpiaron. Este taller demuestra que el código "que funciona" no es suficiente; si el código no es **limpio y entendible**, se convierte en una bomba de tiempo operativa.

## 💻 Ejecución
Para ejecutar el proyecto, asegúrate de tener instalado el JDK 17 o superior.
1. Clona el repositorio.
2. Navega hasta la carpeta `src`.
3. Compila: `javac restaurante/Main.java`
4. Ejecuta: `java restaurante.Main`

---
**Autor:** [Jose Julio Gomez Palmera]  
**Materia:** Programación de Computadores III - Universidad Popular del Cesar  
**Docente:** Ing. Alfredo David Bautista Romero
