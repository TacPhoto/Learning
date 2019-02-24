#include <stdio.h>
#include <ctype.h>
//totall basics, do not waste your time reading :P I've just wanted to memorize some ctype.h functions better ;)
int main()
{
	int i = 0;
	char ch[] = "tak1";
	
	puts(ch); //print ch
	
	for( i = 0 ; i < 4 ; i++) //print upper case ch
	{
	putchar(toupper(ch[i]));
	}
	puts("");
	puts(ch); //new line

	for( i = 0 ; i < 4 ; i++) //print upper case ch
	{
	if(isalpha(ch[i])) printf("%c s is an alpha character!\n", ch[i]);
	if(!isalpha(ch[i])) printf("%c s is not an alpha character!\n", ch[i]);
	if(isdigit(ch[i])) printf("%c s is a digit!\n", ch[i]);
	}

	if(islower(ch[0])) printf("%c is a lower letter!\n", ch[0]);
	if(ispunct(',')) printf("',' is a punctuation mark!\n");
	if(isblank(' ')) puts("Space is a blank character!");
	if(isspace(' ')) puts("Space is a space ;)");
	
	return 0;
}
