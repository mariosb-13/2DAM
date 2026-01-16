class Turista:

    def __init__(self, email:str, nombre:str, telefono:str):
        self.email = email
        self.nombre = nombre
        self.telefono = telefono

    def __str__(self):
        print("Turista :", self.nombre,self.email,self.telefono)