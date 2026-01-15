from Ejercicio_05_Carrito import Carrito
from Ejercicio_05_Catalogo import Catalogo
from Ejercicio_05_Producto import Producto

salir = False
catalogo = Catalogo()
carrito = Carrito()

while not salir:
    print("\n--- MENU DE OPCIONES ---")
    print("1. Crear Producto")
    print("2. Mostrar Catálogo")
    print("3. Mostrar productos disponibles (Stock > 0)")
    print("4. Aumentar stock")
    print("5. Disminuir stock")
    print("6. Mostrar información de un producto (ID)")
    print("7. Buscar Producto (Nombre)")
    print("8. Añadir producto al carrito")
    print("9. Quitar producto del carrito")
    print("10. Mostrar Carrito y Total")
    print("11. Importar Productos")
    print("12. Exportar productos CSV")
    print("13. Exportar productos JSON")
    print("14. Cambiar stock masivo")
    print("15. Cargar Carrito")
    print("16. Salir")

    try:
        opcion = int(input("Selecciona una opcion: "))
    except ValueError:
        print("Por favor, introduce un número válido.")
        continue

    if opcion == 1:
        nombre = input("Introduce el nombre del producto: ")
        try:
            precio = float(input("Introduce el precio del producto: "))
            stock = int(input("Introduce el stock inicial: "))
            producto = Producto(nombre, precio, stock)
            catalogo.add(producto)  # CORREGIDO: Pasar la instancia, no la clase
        except ValueError:
            print("Error: Precio o stock deben ser números.")

    elif opcion == 2:
        catalogo.mostrar()

    elif opcion == 3:
        catalogo.mostrar(min_stock=1)

    elif opcion == 4:
        try:
            pid = int(input("Introduce el ID del producto: "))
            cantidad = int(input("Introduce la cantidad a añadir: "))
            p = catalogo.get_producto_by_id(pid)
            if p:
                p.add_stock(cantidad)
            else:
                print("Producto no encontrado.")
        except ValueError:
            print("Error: Introduce números válidos.")

    elif opcion == 5:
        try:
            pid = int(input("Introduce el ID del producto: "))
            cantidad = int(input("Introduce la cantidad a disminuir: "))
            p = catalogo.get_producto_by_id(pid)
            if p:
                p.del_stock(cantidad)
            else:
                print("Producto no encontrado.")
        except ValueError:
            print("Error: Introduce números válidos.")

    elif opcion == 6:
        try:
            pid = int(input("Introduce el ID del producto: "))
            p = catalogo.get_producto_by_id(pid)
            if p:
                p.mostrar_info()
            else:
                print("Producto no encontrado.")
        except ValueError:
            print("Error: El ID debe ser un número.")

    elif opcion == 7:
        fragmento = input("Introduce parte del nombre: ")
        encontrado = False
        for p in catalogo.get_productos():
            # CORREGIDO: Usar 'in' para buscar substring
            if fragmento.lower() in p.nombre.lower():
                p.mostrar_info()
                encontrado = True
        if not encontrado:
            print("No se encontraron coincidencias.")

    elif opcion == 8:
        try:
            pid = int(input("Introduce el ID del producto: "))
            cantidad = int(input("Introduce la cantidad: "))

            p = catalogo.get_producto_by_id(pid)
            if p:
                carrito.add_item(p, cantidad)
            else:
                print("Producto no encontrado en el catálogo.")
        except ValueError:
            print("Error: Introduce números válidos.")

    elif opcion == 9:
        try:
            pid = int(input("Introduce el ID del producto a quitar: "))
            cantidad = int(input("Introduce la cantidad a quitar: "))

            p = catalogo.get_producto_by_id(pid)
            if p:
                carrito.del_item(p, cantidad)
            else:
                print("Producto no válido.")
        except ValueError:
            print("Error: Introduce números válidos.")

    elif opcion == 10:
        carrito.mostrar()
        carrito.total()

    elif opcion == 11:
        archivo = open('productos.csv', 'r')
        while True:

            linea_producto = archivo.readline()
            print(linea_producto)
            if not linea_producto:
                break
            linea_producto = linea_producto.split(',')
            nombre = linea_producto[0]
            precio = float(linea_producto[1])
            stock = int(linea_producto[2])
            producto = Producto(nombre, precio, stock)
            catalogo.add(producto)
        archivo.close()
    elif opcion == 12:

        archivoEscrito = open('producto_escrito.csv', 'w')

        for p in catalogo.get_productos():
            archivoEscrito.write(f'{p.id},{p.nombre},{p.precio},{p.stock}\n')
    elif opcion == 13:
        pass
    elif opcion == 14:
        pass
    elif opcion == 15:
        pass
    elif opcion == 16:
        salir = True
        print("Saliendo del programa...")
    else:
        print("Opción no reconocida.")
