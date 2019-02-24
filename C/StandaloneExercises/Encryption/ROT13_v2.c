#include <stdio.h>
#include <ctype.h>
//ROT13 encryption
int main()
{
	char ch;
	printf("Encrypted using ROT13: ");
	while ( (ch = getchar()) != '\n' )
	{
		if (isalpha(ch)) //encrypt letters only
			putchar(ch + 13);
		else
			putchar(ch);
	}
	putchar(ch); //put a new line
	return 0;
}
