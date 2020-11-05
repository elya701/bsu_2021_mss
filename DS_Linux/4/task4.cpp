#include <iostream>
#include <stdio.h>
#include <cstdlib>
#include <sys/time.h>
#include <unistd.h>

using namespace std;
int main () {

    struct timeval current_time;
    int x, a, b, different, different_2, first_task_usec, second_task_usec;
    float different_single_1, different_single_2;
    
    printf ("\nMin number:") ;
    cin >> a ;
    printf ("Max number:") ;
    cin >> b ;
    
    gettimeofday(&current_time, NULL);
    first_task_usec = current_time.tv_usec;
    
    for(int i = 0; i < 100; i++) {
        x = a + rand()%(b-a+1);
    }
    
    gettimeofday(&current_time, NULL);
    different = current_time.tv_usec - first_task_usec;

    printf("\nFirst task: %d USEC", different);
    
    gettimeofday(&current_time, NULL);
    second_task_usec = current_time.tv_usec;
    for(int i = 0; i < 100; i++) {
        getpid();
    }
    
    gettimeofday(&current_time, NULL);
    different_2 = current_time.tv_usec - second_task_usec;
    
    printf("\nSecond task: %d USEC", different_2);
    
    printf("\nDifferent between First Task and Second Task: %d USEC", different - different_2);
    
    different_single_1 = (float)different / 100;
    different_single_2 = (float)different_2 / 100;
    printf("\n\n\nSpeed (one iteration) for First Task: %lf USEC", different_single_1);
    
    printf("\nSpeed (one iteration) for Second Task: %lf USEC", different_single_2);
    
    printf("\nDifferent (one iteration) between First Task and Second Task: %lf USEC\n\n", different_single_1 - different_single_2);
}
