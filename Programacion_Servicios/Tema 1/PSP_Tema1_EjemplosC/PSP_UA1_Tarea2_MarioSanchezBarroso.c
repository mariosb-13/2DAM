#include <stdio.h>
#include <unistd.h>   // Para fork()
#include <sys/types.h> // Para pid_t
#include <sys/wait.h>  // Para wait()

void MySignalHandler(int sig){
    printf("Signal number is %d \n ", sig);
}
int main() {
   signal(SIGIO, &MySignalHandler);
   while (1)
   {
    printf("zzzzZZZZzzz \n");
    sleep(2);
   }
   
}
