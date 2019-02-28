#include <stdio.h>
//lets practise using static variables!
void disp(void);
int main()
{
	int counter;
	for( counter = 1; counter <= 3 ; counter++ )
	{
		puts("Begin iteration");
		disp();	
	}
		
return 0;
}

void disp(void)
{
	int cnst = 1;
	static int change = 1;
	printf("I have a constant value: %d I keep changing: %d\n", cnst++, change++);
}
