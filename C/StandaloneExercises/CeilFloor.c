#include <stdio.h>
#include <stdlib.h>
#include <math.h>
 
int main(void)
{
    double a, wceil, wfloor; // zmienne
 
    printf("Podaj liczbe\n");
    scanf("%lf", &a);
    wfloor = floor(a); // licz wynik dla floor
	wceil = ceil(a); //licz wynik dla ceil
    printf("'floor' z \"%lf\" wynosi : %lf\n", a, wfloor);
 	printf("'ceil' z \"%lf\" wynosi : %lf\n", a, wceil);
    return 0;
}


