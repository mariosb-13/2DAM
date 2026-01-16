import json
from sqlite3 import Date

from GestorReservas import GestorReservas


def opcion_menu():
    print("""
    --- MENÚ GESTIÓN DE ACTIVIDADES ---
    1. Registrar turista
    2. Crear actividad
    3. Reservar actividad
    4. Cancelar reserva
    5. Mostrar turistas
    6. Mostrar actividades
    7. Mostrar turistas por actividad
    8. Mostrar actividades de un turista
    9. Mostrar turista(s) con más reservas
    10. Mostrar turista(s) con mayor gasto
    11. Exportar actividades a CSV
    12. Salir
    """)
    return int(input("Introduce una opción válida: "))


with open("datos.json") as fichero:
    menu = json.load(fichero)

print(type(menu))
print(menu)

for clave in menu:
    print("La clave es:", clave, "y su valor es:", menu[clave])

if opcion_menu() == 1:
    email = input("Introduzca email: ")
    nombre = input("Introduzca nombre: ")
    telf = input("Introduzca teléfono: ")

    GestorReservas.registrar_turista(GestorReservas, email, nombre, telf)
elif opcion_menu() == 2:
    titulo = input("Introduzca titulo: ")
    precio = input("Introduzca precio: ")
    plazas = input("Introduzca plazas: ")
    fecha = Date.today()
    GestorReservas.crear_actividad(titulo, precio, fecha, plazas)
elif opcion_menu() == 3:
    email = input("Introduzca email: ")
    plazas = input("Introduzca plazas: ")
    GestorReservas.reservar_actividad(email, plazas)
elif opcion_menu() == 4:
    id_reserva = input("Introduzca el id de la reserva: ")
    GestorReservas.cancelar_reserva(id_reserva)
elif opcion_menu() == 5:
    id_reserva = input("Introduzca el id de la reserva: ")
    print("Mostrando todos los turistas")
    GestorReservas.get_turistas(id_reserva)
elif opcion_menu() == 6:
    email = input("Introduzca email: ")
    print("Mostrando actividades")
    GestorReservas.get_actividades(email)
elif opcion_menu() == 7:
    pass
elif opcion_menu() == 8:
    pass
elif opcion_menu() == 9:
    pass
elif opcion_menu() == 10:
    pass
elif opcion_menu() == 11:
    pass
elif opcion_menu() == 12:
    pass
else:
    print("Opción no reconocida.")
