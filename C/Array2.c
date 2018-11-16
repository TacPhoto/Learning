#include <stdio.h>
#include <stdlib.h>

int main() {
	//zad6, srednia liczb naturalnych wpisanych w scanf. wyswietla srednia oraz ilosc jedynek
	int ile; //ilosc ocen w tablicy
	int i; //licznik tablicy
	int nr; //nr pozycji w tablicy na potrzeby komunikatu podczas wpisywania danych do niej
	int suma=0; //suma ocen
	int srednia=0; //srednia z ocen
	int jed=0; //ilosc jedynek wsrod ocen
	printf("Ile ocen chcesz wpisac?\n");
	scanf("%d", &ile);

	int*tab = (int*)malloc(sizeof(int)*ile); //tablica z ocenami

	for (i = 0; i < ile; i++) { //petla wpisujaca dane do tablicy
		nr = i + 1;
		printf("Wpisz ocene nr %d\n", nr);
		scanf("%d", &tab[i]);
	}
	/*testowe wyswietlanie zawartosci tablicy
	for (i = 0; i < ile; i++){
	printf("%d \n",tab[i]);} */

	for (i = 0; i < ile; i++) { //zliczanie sumy ocen
		suma += tab[i];
	}
	srednia = suma / ile; // obliczenie sredniej
	for (i = 0; i < ile; i++) {
		if (tab[i] == 1) jed++;
	}
	printf("Srednia z twoich ocen wynosi %d oraz masz %d jedynek\n", srednia, jed);

	free(tab);
	system("pause");
	return 0;
}
