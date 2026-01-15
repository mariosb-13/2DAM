class Catalogo:

    def __init__(self):
        self.productos = []

    def add(self, producto):
        if self.get_producto_by_id(producto.pid):
            print("El producto ya existe en el catálogo.")
            return
        self.productos.append(producto)
        print("Producto añadido al catálogo.")

    def get_productos(self):
        return self.productos

    def mostrar(self, min_stock=0):
        print(f"--- Listado de Productos (Stock >= {min_stock}) ---")
        encontrados = False
        for p in self.productos:
            if p.stock >= min_stock:
                p.mostrar_info()
                encontrados = True
        if not encontrados:
            print("No hay productos que cumplan el criterio.")

    def get_producto_by_id(self, pid):
        for p in self.productos:
            if p.pid == pid:
                return p
        return None  # Devuelve None si no lo encuentra al finalizar el bucle