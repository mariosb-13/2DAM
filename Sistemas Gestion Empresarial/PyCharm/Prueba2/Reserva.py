class Reserva:
    id_reserva: int = 0

    def __init__(self, plazas_reservadas: int, email_turista: str, id_actividad: int):
        Reserva.id_reserva += 1
        self.plazas_reservadas = plazas_reservadas
        self.email_turista = email_turista
        self.id_actividad = id_actividad

    def __str__(self):
        print("Reserva: ", self.plazas_reservadas, self.email_turista, self.id_actividad)
