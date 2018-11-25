#include <stdio.h>
#include <stdlib.h>

int main() {
	int i;

	for (int i = 1; i <= 10; i++) { //listuje od 0 do 10
		printf("%d ", i);
	}

	printf("\n");

	for (int i = 10 ; i > 0; i--) { //listuje od 10 do 0
		printf("%d ", i);
	}



	system("pause");
	return 0;
}
