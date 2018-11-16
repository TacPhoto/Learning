#include <stdio.h>
#include <stdlib.h>

int main() {
	//program zlicza sume liczb naturalnych mniejszych lub rownych podanej, uzyc scanf
	int a; //liczba ktora bedziemy wpisywac
	int suma=0; //suma tych liczb
	int i = 0; //licznik
	printf("Witaj, podaj liczbe naturalna na podstawie ktorej oblicze sume wszystkich liczb naturalnych mniejszych od niej\n");
	scanf("%d", &a);
	a--;          //zmniejszamy a o jeden poniewaz nie dodajemy a do sumy a liczbe o 1 mniejsza, sumowaniem pomniejszonego o 1 a oraz kolejnych pomniejszem zajmie sie funkcja
	do{ //funkcja zatrzymuje sie gdy a=0 poniewaz wykorzystamy wtedy wszystkie liczby nat. a liczba 0 nie wplywa na wynik
		suma += a;
		a--;
	}while( a > 0 );
	printf("Suma: %d \n",suma);


	system("pause");
	return 0;
}
