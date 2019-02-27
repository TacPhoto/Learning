#include <stdio.h>
#include <stdio.h>
#define endl puts("")
//don't laugh at me, I have never used strcat befor (or I have forgotten)
int main()
{
	char someText[256]="What's";
	char anotherText[]="up?"; 
	
	puts(someText);
	puts (anotherText);
	endl;
	
	strcat(someText," "); //please note this usage of strcat does not protect your code from overflow. I'm writting this code only to memorize strcat syntax
	strcat(someText, anotherText);
	
	puts("Let's see how it looks after concatenation");
	endl;
	puts(someText);
	puts(anotherText);
	
	return 0;
}
