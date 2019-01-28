#include <stdio.h>
#include <stdlib.h>
void exp(int a,int n) {
	int buf = a; //buffer
	if( n == 0 )
	{ 
	buf = 1;
	}else{
	for( int i = 1 ; i<n ; i++ ) buf = buf * a; //calculate
	}
	printf("result= %d", buf); //print the result
}
int main() {
	int n = 0;//exponent
	int a = 3;//number, does not have to be necessarily hardcoded ;)
	printf("What exponent do you want to use on num 3?\n");
	scanf("%d", &n); //gets user input
	printf("\n");
	exp(a,n); //launch exponentiation
	system("pause");
	return 0;
}
//system("pause") may not work on linux, use getch or sth simmilar instead
