from itertools import count
from uuid import MAX

from Cliente import Cliente
from Pedido import Pedido

listaClientes = []

print("Selecciona una opción de las siguientes")
print("1. Añadir Cliente")
print("2. Añadir pedido cliente(id_cliente, precio_total)")
print("3. Mostrar clientes (id, nombre, telefono, email, nº pedidos, total_pagado)")
print("4. Mostrar clientes resumen (id, nombre, nº pedidos, total_pagado)")
print("5. Detalles de cliente por nombre (id, nombre, telefono, email, nº pedidos, total_pagado)")
print("6. Detalles del cliente con mayor gasto (id, nombre, telefono, email, nº pedidos, total_pagado)")
print("7. Salir")

opc = int(input("Selecciona una opción: "))

match opc:
    case 1:
        print("===Añadir Cliente===")
        nombre = input("Introduzca el nombre: ")
        telf = int(input("Introduzca el teléfono: "))
        email = input("Introduzca el email:")

        cliente = Cliente(nombre=nombre, telefono=telf, email=email, pedidos=0)
        listaClientes.append(cliente)

        print("Cliente creado correctamente")
    case 2:
        print("===Añadir Pedido al Cliente===")
        id_cliente = input("Introduzca el id del cliente: ")
        for cliente in listaClientes:
            if (cliente.id == id_cliente):
                precio_total = int(input("Introduzca el precio_total del pedido: "))
                cliente.pedidos = Pedido(id_cliente, precio_total)
                print("Pedido añadido al cliente")
    case 3:
        for cliente in listaClientes:
            print(cliente.id, cliente.nombre, cliente.telefono, cliente.email, cliente.pedidos)
    case 4:
        for cliente in listaClientes:
            print(cliente.id, cliente.nombre, cliente.email, cliente.pedidos)
    case 5:
        nombre_pedido = input("Introduzca el nombre del cliente a buscar: ")
        for cliente in listaClientes:
            if (nombre_pedido == cliente.nombre):
                print("¡Se encontro el cliente!")
                print(cliente.id, cliente.nombre, cliente.telefono, cliente.email, cliente.pedidos,
                      cliente.pedidos.precio_total)
    case 6:
        for cliente in listaClientes:
            print(cliente.id, cliente.nombre, cliente.telefono, cliente.email, cliente.pedidos,
                  cliente.pedidos.precio_total)