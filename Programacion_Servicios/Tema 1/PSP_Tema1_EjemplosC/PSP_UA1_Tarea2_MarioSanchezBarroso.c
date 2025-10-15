#include <stdio.h>
#include <unistd.h>
#include <signal.h>
#include <stdlib.h>

void MySignalHandler(int sig) {
    char respuesta;
    printf("\nHe recibido la señal %d\n", sig);
    printf("¿Quieres terminar el proceso? (s/n): ");
    scanf(" %c", &respuesta);

    if (respuesta == 's' || respuesta == 'S') {
        printf("Finalizando el proceso...\n");
        exit(0);
    } else {
        printf("Continuando la ejecución...\n");
    }
}

int main() {
    signal(SIGUSR1, MySignalHandler);
    while (1) {
        printf("Proceso activo...\n");
        sleep(2);
    }
    return 0;
}
