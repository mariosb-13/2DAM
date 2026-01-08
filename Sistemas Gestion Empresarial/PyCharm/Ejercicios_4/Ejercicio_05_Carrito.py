from Ejercicio_05_Producto import Producto

class Carrito:
    def __init__(self):
        self.items = {}

    def add_item(self, producto, cantidad):
        if not producto.hayStock(cantidad):
            print("No hay stock suficiente")
        elif self.items[producto]:
            if producto.hayStock(cantidad + self.items[producto]):
                self.items.get[producto] += cantidad
            else:
                print("No hay stock suficiente")
        else:
            self.items[producto] = cantidad

    def del_item(self, producto, cantidad):
        if self.items[producto] < cantidad:
            print("No se puede retirar mas cantidad de la que hay en el carrito")
        elif self.items[producto] > cantidad:
            self.items[producto] -= cantidad
        else:
            self.items.pop(producto)

    def mostrar(self):
        for producto, valor in self.items.items():
            print(producto.mostrarInfo(), valor)

    def total(self):
        precioTotal = 0
        for producto, valor in self.items.items():
            precioTotal += valor * producto.precio
        print("Coste total:", precioTotal)

    def vaciar(self):
        self.items.clear()
        print("Carrito vaciado")
