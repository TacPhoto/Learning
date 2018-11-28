#include <stdio.h>
#include <stdlib.h>
#include <conio.h>

int main() {
    FILE *ws=fopen("b:\\bin.rec","w"); //otwiera plik do zapisu

    const n = 81; //dlugosc chara
    char a[n];
    fgets (a ,n+1, stdin); //pobiera char od usera

    fputs(a, ws);	//wpisuje do pliku			
	fclose(ws); //zamknij plik

	system("pause");	
	return 0;
}
