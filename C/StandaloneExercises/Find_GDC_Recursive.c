#include <stdio.h>
#include <stdlib.h>
//find greatest common divisorusing recursive function
int euk(int a, int b)
{
    if (b==0) return a;
    else return euk(b,(a%b));
}
int main(){
	int a=7;
	int b=3;
	printf("GDC of %d and %d is %d\n", a, b, euk(a,b));
	system("pause");
	return 0;
}
