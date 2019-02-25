#include <stdio.h>
#include <stdlib.h> //for file handling
#include <string.h> //fot strcpy() and strcat()
//simple file compressing program
//NOT WORKING YET!
char compress(char source[], char result[]);

int main()
{
	char buf[256]; //text buffer
	int i =0; //iteration counter
	int length; //contains text length
	
	puts("Enter a line of text, avoid using non letters");
	scanf("%s", buf);
	length = strlen(buf);
//	printf("text: %s   length:  %d\n", buf, length); //TEST

	char * source = malloc( sizeof( char ) * ( length + 1 ) ); //allocate memory fot phrase
    strncpy( source, buf, length ); //uses strncpy the safe way
    source[ length ] = '\0'; //makes sure there is end of a string
//	printf("You have typed: %s,TEST\n", source ); //////TEST	

	char * result = malloc( sizeof( char ) * ( length + 1 ) ); //allocate memory fot phrase

	compress(source, result);


	return 0;
}

char compress(char source[], char result[])
{	
	int i = 0;
	int count = 0;
	char buf[256];
	char temp = source[0];
	char num;
	int length = strlen(source);
//	printf("source %s", source); //TEST
	
	while( i < length )
	{
		num ="";
		count = 0;
		while(temp == source[i+count])
		{
			count++;
		}
		if( count!=0 )
		{
		num = count;
		result[i] = num;
		i++;
		result[i] = source[i-1];
		i += count - 1;
		}
		else
		{
			i++;
		}
	puts(result);
	return result;
	}
	
	
	
}
