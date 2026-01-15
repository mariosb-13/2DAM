class Producto:
    _pid_counter = 0  # Contador estático de la clase

    def __init__(self, nombre, precio, stock=0):
        self.pid = Producto._pid_counter
        Producto._pid_counter += 1
        self.nombre = nombre
        self.precio = precio
        self.stock = stock

    def mostrar_info(self):
        # Retornamos string o imprimimos directamente, pero es mejor imprimir limpio
        print(f"ID: {self.pid} | Nombre: {self.nombre} | Precio: {self.precio} | Stock: {self.stock}")

    def add_stock(self, cantidad):
        if cantidad <= 0:
            print("La cantidad debe ser mayor a 0.")
        else:
            self.stock += cantidad
            print(f"Stock actualizado. Nuevo stock: {self.stock}")

    def del_stock(self, cantidad):
        if cantidad <= 0:
            print("La cantidad debe ser mayor a 0.")
        elif self.stock < cantidad:
            print(f"No hay suficiente stock para eliminar. Stock actual: {self.stock}")
        else:
            self.stock -= cantidad
            print(f"Stock reducido. Nuevo stock: {self.stock}")

    def hay_stock(self, cantidad):
        return self.stock >= cantidad

    def __str__(self):
        return f'{self.pid}, {self.nombre}, {self.precio}, {self.stock}'