class Carrito:
    def __init__(self):
        self.items = {}  # Diccionario: {ObjetoProducto: cantidad}

    def add_item(self, producto, cantidad):
        if not producto.hay_stock(cantidad):
            print("No hay stock suficiente en el almacén.")
            return

        # Verificar si ya está en el carrito para sumar cantidad
        cantidad_actual_en_carrito = self.items.get(producto, 0)

        # Validar si hay stock para la nueva suma total (lo que ya tengo + lo nuevo)
        if not producto.hay_stock(cantidad + cantidad_actual_en_carrito):
            print("No hay stock suficiente para añadir esa cantidad extra.")
            return

        if producto in self.items:
            self.items[producto] += cantidad
        else:
            self.items[producto] = cantidad
        print("Producto añadido al carrito.")

    def del_item(self, producto, cantidad):
        if producto not in self.items:
            print("Este producto no está en el carrito.")
            return

        if self.items[producto] < cantidad:
            print("No puedes retirar más cantidad de la que tienes en el carrito.")
        elif self.items[producto] == cantidad:
            self.items.pop(producto)  # Eliminar del todo si la cantidad es exacta
            print("Producto eliminado del carrito.")
        else:
            self.items[producto] -= cantidad
            print("Cantidad reducida en el carrito.")

    def mostrar(self):
        if not self.items:
            print("El carrito está vacío.")
            return

        print("--- Contenido del Carrito ---")
        for producto, cantidad in self.items.items():
            subtotal = producto.precio * cantidad
            print(f"{producto.nombre} | Cantidad: {cantidad} | Precio Unit: {producto.precio} | Subtotal: {subtotal}")

    def total(self):
        precio_total = 0
        for producto, cantidad in self.items.items():
            precio_total += cantidad * producto.precio
        print(f"Coste total del carrito: {precio_total:.2f}")

    def vaciar(self):
        self.items.clear()
        print("Carrito vaciado.")