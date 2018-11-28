#include <stdio.h>
#include <stdlib.h>
//funkcja poteguje rekurencyjnie
int pot( int liczba, int potega){
    if(potega==0){ //potegowanie przez 0 zawsze zwraca 1
        return 1;
    }
    return(liczba*pot(liczba,potega-1)); //poteguje
}
int main()
{
	int a=3; //liczba potegowana
	int n=0; //wartosc potegi
	printf("podaj wysokosc potegi do ktorej podniesiemy liczbe 3 \n");
	scanf("%d", &n); //pobiera liczbe od uzytkownika
    printf("%d", pot(a,n)); //wypisze wynik ktorego wartosc wyliczy funkcja
    system("pause");
    return 0;
}
