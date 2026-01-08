class Producto:
    pid = 0

    def __init__(self, nombre, precio, stock=0):
        self.nombre = nombre
        self.precio = precio
        self.stock = stock
        self.pid = Producto.pid
        Producto.pid += 1

    def mostrarInfo(self):
        print(self.pid, self.nombre, self.precio, self.stock)

    def addStock(self, cantidad):
        if (cantidad < 0):
            print("La cantidad indicada no puede ser 0")
        else:
            self.stock += cantidad
            print("Stock Actualizado")

    def eliminarStock(self, cantidad):
        if (self.stock < cantidad):
            print("La cantidad no puede ser mayor al stock actual, stock actual: ", self.stock)
        elif (cantidad < 0):
            print("La cantidad indicada no puede ser 0")
        else:
            self.stock -= cantidad

    def hayStock(self, cantidad):
        if (cantidad > self.stock):
            return False
        else:
            return True

    def __str__(self):
        return f'{self.pid}, {self.nombre}, {self.precio}, {self.stock}'
