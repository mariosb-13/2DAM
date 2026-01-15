import json
import os
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
    print("11. Importar Productos (CSV)")
    print("12. Exportar productos (CSV)")
    print("13. Exportar productos (JSON)")
    print("14. Cambiar stock masivo (CSV)")
    print("15. Cargar Carrito (JSON)")
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
            catalogo.add(producto)
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
        # Importar CSV: nombre,precio,stock
        nombre_archivo = input("Introduce el nombre del archivo CSV a importar (ej. productos.csv): ")
        try:
            with open(nombre_archivo, 'r') as archivo:
                contador_importados = 0
                for linea in archivo:
                    linea = linea.strip()
                    if not linea: continue  # Saltar líneas vacías

                    partes = linea.split(',')
                    if len(partes) >= 3:
                        nombre = partes[0].strip()
                        try:
                            precio = float(partes[1])
                            stock = int(partes[2])
                            producto = Producto(nombre, precio, stock)
                            catalogo.add(producto)
                            contador_importados += 1
                        except ValueError:
                            print(f"Error en formato de números en la línea: {linea}")
                print(f"Proceso finalizado. {contador_importados} productos importados.")
        except FileNotFoundError:
            print("El archivo no existe.")
        except Exception as e:
            print(f"Ocurrió un error al leer el archivo: {e}")

    elif opcion == 12:
        # Exportar CSV: id,nombre,precio,stock
        nombre_archivo = input("Introduce el nombre del archivo para exportar (ej. export.csv): ")
        try:
            with open(nombre_archivo, 'w') as archivoEscrito:
                for p in catalogo.get_productos():
                    # Usamos p.pid, no p.id
                    archivoEscrito.write(f'{p.pid},{p.nombre},{p.precio},{p.stock}\n')
            print(f"Catálogo exportado exitosamente a {nombre_archivo}")
        except Exception as e:
            print(f"Error al escribir el archivo: {e}")

    elif opcion == 13:
        # Exportar JSON: Lista de objetos con id, nombre, precio, stock
        nombre_archivo = input("Introduce el nombre del archivo JSON para exportar (ej. productos.json): ")
        lista_exportar = []
        for p in catalogo.get_productos():
            diccionario_prod = {
                "id": p.pid,
                "nombre": p.nombre,
                "precio": p.precio,
                "stock": p.stock
            }
            lista_exportar.append(diccionario_prod)

        try:
            with open(nombre_archivo, 'w') as archivoJson:
                json.dump(lista_exportar, archivoJson, indent=4)
            print(f"Catálogo exportado exitosamente a {nombre_archivo}")
        except Exception as e:
            print(f"Error al generar el JSON: {e}")

    elif opcion == 14:
        # Stock Masivo CSV: id, nuevo_stock
        nombre_archivo = input("Introduce el archivo CSV de stock (ej. stocks.csv): ")
        try:
            with open(nombre_archivo, 'r') as archivo:
                for linea in archivo:
                    linea = linea.strip()
                    if not linea: continue

                    partes = linea.split(',')
                    if len(partes) >= 2:
                        try:
                            pid_buscar = int(partes[0])
                            nuevo_stock = int(partes[1])

                            p = catalogo.get_producto_by_id(pid_buscar)
                            if p:
                                p.stock = nuevo_stock  # Actualización directa
                                print(f"Stock actualizado para {p.nombre} (ID: {p.pid}) -> {p.stock}")
                            else:
                                print(f"Producto con ID {pid_buscar} no encontrado. No se insertó.")
                        except ValueError:
                            print(f"Error de formato numérico en línea: {linea}")
        except FileNotFoundError:
            print("El archivo no existe.")

    elif opcion == 15:
        # Cargar Carrito JSON: [{"id": 1, "cantidad": 3}, ...]
        # REQUISITO: Validación atómica. Si falla uno, no se carga nada. Si funciona, borra lo previo.
        nombre_archivo = input("Introduce el archivo JSON del carrito (ej. carrito.json): ")
        try:
            with open(nombre_archivo, 'r') as f:
                datos_carrito = json.load(f)

            # 1. Fase de Validación
            es_valido = True
            lista_temporal = []  # Guardamos tuplas (objeto_producto, cantidad)

            for item in datos_carrito:
                pid = item.get("id")
                cantidad = item.get("cantidad")

                # Buscamos producto
                p = catalogo.get_producto_by_id(pid)

                if p is None:
                    print(f"ERROR: El producto con ID {pid} no existe en el catálogo.")
                    es_valido = False
                    break

                if not p.hay_stock(cantidad):
                    print(
                        f"ERROR: El producto '{p.nombre}' (ID: {pid}) no tiene suficiente stock (Solicitado: {cantidad}, Disponible: {p.stock}).")
                    es_valido = False
                    break

                lista_temporal.append((p, cantidad))

            # 2. Fase de Ejecución
            if es_valido:
                carrito.vaciar()  # Borrar productos previos
                for prod, cant in lista_temporal:
                    # Usamos add_item. Como ya validamos stock y el carrito está vacío, no debería fallar.
                    # Nota: add_item hace print(), saldrán mensajes por pantalla.
                    carrito.add_item(prod, cant)
                print("Carga de carrito masiva completada exitosamente.")
            else:
                print("No se ha cargado el carrito debido a los errores mencionados.")

        except FileNotFoundError:
            print("El archivo no existe.")
        except json.JSONDecodeError:
            print("El archivo no tiene un formato JSON válido.")
        except Exception as e:
            print(f"Ocurrió un error inesperado: {e}")

    elif opcion == 16:
        salir = True
        print("Saliendo del programa...")
    else:
        print("Opción no reconocida.")