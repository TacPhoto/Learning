#include <stdio.h>
#define SPACE ' '
//ROT13 encryption
int main()
{
	char ch;
	ch = getchar();
	printf("Encrypted using ROT13: ");
	while (ch != '\n') //till the end of the line
	{
		if (ch == SPACE) //do not modify the spaces
			putchar(ch);
		else
			putchar(ch + 13); //encrypt using ROT13
			ch = getchar();
	}
	putchar(ch); //put a new line
	
	return 0;
}
