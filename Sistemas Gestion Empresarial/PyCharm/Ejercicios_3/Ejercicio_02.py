# 2. Calcular edad
# Crea un programa que pida la fecha de nacimiento al usuario y calcule la edad actual y cuántos días ha vivido. Nota: Módulo datetime
from datetime import datetime

fecha_actual = datetime.now()

day_nac = int(input("Introduce el dia de tu cumpleaños "))
month_nac = int(input("Introduce el mes(número) de tu cumpleaños "))
year_nac = int(input("Introduce el año de tu cumpleaños "))

print("Edad actual: ", fecha_actual.year - year_nac)