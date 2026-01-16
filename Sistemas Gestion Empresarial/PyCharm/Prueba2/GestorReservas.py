from Actividad import Actividad
from Reserva import Reserva
from Turista import Turista


class GestorReservas:
    turistas: Turista = []
    actividades: Actividad = []
    reservas: Reserva = []

    def __init__(self, turistas, actividades, reservas):
        self.turista = turistas
        self.actividades = actividades
        self.reservas = reservas

    def registrar_turista(self, email: str, nombre: str, telefono: str):
        flag = False
        for t in self.turistas:
            if t.email == email:
                flag = False
                print("dentro if")
            else:
                print("dentro else")
                flag = True
                Turista(email, nombre, telefono)

        if flag:
            print("Se ha registrado al turista exitosamente")
        else:
            print("ERROR: Ya hay un turista con ese correo registrado")

    def crear_actividad(self, titulo: str, fecha: str, precio: float, plazas: int):
        # Se establece el mismo numero de plazas disponibles que las totales ya que todavia no hay nadie inscrito
        actividad = Actividad(titulo, fecha, precio, plazas, plazas)
        print("Se ha creado una actividad nueva")

    def reservar_actividad(self, email: str, id_actividad: int, plazas: int):
        for act in GestorReservas.actividades:
            if act.id_actividad == id_actividad:
                flag = True
                reserva = Reserva(plazas, email, id_actividad)
            else:
                flag = False
        if flag:
            print("Se ha creado la reserva correctamente")
        else:
            print("ERROR: No se ha podido crear la reserva")

    def cancelar_reserva(self, id_reserva):
        for rev in GestorReservas.reservas:
            if rev.id_reserva == id_reserva:
                flag = True

                # Cancelar Reserva
            else:
                flag = False

        if flag:
            print("La reserva se ha cancelado exitosamente")
        else:
            print("ERROR: No se ha podido cancelar la reserva")

    def get_turistas(self, id_actividad: int):
        if id_actividad == -1:
            for turista in GestorReservas.turistas:
                print(turista)
        else:
            pass
            # for actividad in GestorReservas.actividades:

    def get_actividades(self, email: str):
        pass

    def get_reservas(self):
        for reservas in GestorReservas.reservas:
            print(reservas)

    def get_turistas_top_reservas(self):
        pass

    def get_turistas_top_gasto(self, email: str):
        pass
