#include <stdio.h>
#include <ctype.h>
//totall basics, do not waste your time reading :P
int main()
{
	int a = 33, b = 42, c = -3, max;
	
	c = ( c < 0 ) ? -c : c;
	max = ( a > c ) ? a : b;
	
	printf("c = %d\n", c);
	printf("max = %d\n", max);
	//find total max:
	max = ( a > c ) ? a : c;
	max = ( max > b ) ? max : b;
	printf("total max = %d\n", max);
	
	return 0;
}
