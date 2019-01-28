#include <stdio.h>
#include <stdlib.h>
////recursive exponentiation, not the best way to do it but a good way to exercise
int exp( int a, int n){
    if(n==0)return 1;
    return(a*exp(a,n-1));
}
int main()
{
	int a=3; //our number, does not have to be hardcoded
	int n=0; //exponent
	printf("Insert exponent\n");
	scanf("%d", &n); //gets user input for an exponent
    printf("%d", exp(a,n)); //print result
    system("pause");
    return 0;
}
//system("pause") may not work on linux. try using getch or any different pausing method
