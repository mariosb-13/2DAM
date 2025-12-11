# 10. Pedir un número n y mostrar un triángulo de asteriscos de altura n, es decir si
# introduce el 4 muestra:
# *
# **
# ***
# ****

num = int(input("Introduce un número "))

i=1
while i<=num:
    print("*"*i)
    i+=1

