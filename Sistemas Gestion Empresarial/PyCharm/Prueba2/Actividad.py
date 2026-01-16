class Actividad:
    id_actividad:int = 0

    def __init__(self, titulo:str, fecha:str, precio:float, plazas_totales:int, plazas_disponibles:int):
        Actividad.id_actividad += 1
        self.titulo = titulo
        self.fecha = fecha
        self.precio = precio
        self.plazas_totales = plazas_totales
        self.plazas_disponibles = plazas_disponibles
