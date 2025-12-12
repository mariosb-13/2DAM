import time
from typing import final

input("Pulsa enter para empezar a contar")
inicio = time.time();
input("Pulsa enter para acabar de contar")
final = time.time();

print("Tiempo transcurrido", final - inicio)
