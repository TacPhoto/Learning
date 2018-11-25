#include <stdio.h>
#include <stdlib.h>

char * funk(char * tekst, int n)
{
	*(tekst + n) = '\0';
	return tekst;
}
int main(void)
{
	char tekst[256] = "Lorem ipsum dolor, alfa beta gamma, hodor hodor hodor"; //Tekst
	printf("%s", funk(tekst, 9)); //wypisz 9 pierwszych znakow tekstu
	system("pause");
	return 0;
}
