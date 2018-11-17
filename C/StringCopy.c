#include <stdio.h>
#include <string.h>
#include <stdlib.h>
int main() {
	char s[250]; //zrodlowy string
	char t[250]; //finalny string
	printf("Podaj tresc stringa do skopiowania");
	memset(t, '\0', sizeof(t)); //z dokumentacji :Wypełnia kolejne bajty w pamięci ustaloną wartością. 
	gets(s);
	strcpy(t, s);

	printf("Skopiowany string : %s\n", t);

	return(0);
}
