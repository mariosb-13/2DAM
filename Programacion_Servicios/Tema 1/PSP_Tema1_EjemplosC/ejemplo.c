#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>  // Necesario para sleep()

int main(void) {
    printf("Hola, Mundo!\n");
    sleep(10);        // 🔹 Pausa de 10 segundos
    printf("Acabado!\n");
    return 0;
}
