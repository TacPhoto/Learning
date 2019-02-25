#include <stdio.h>
#include <stdlib.h> //for file handling
#include <string.h> //fot strcpy() and strcat()
//simple file compressing program
//WORKING BUT TO BE CORRECTED A BIT
void compress(char source[], char result[]);

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

	char * result = malloc( sizeof( char ) * ( 2* length + 1 ) ); //allocate memory fot phrase

	if(length != 0 || length != 1)compress(source, result);


	return 0;
}

void compress(char source[], char result[])
{	
	int i = 0, j = 0;
	int count = 1;
	char num = 0;
	int length = strlen(source);
	
//	puts(source);//TEST
//	puts(result);//TEST

	for( i = 0 ; i < (length) ; i++ )
	{
		if(source[i] == source[i+1])
		{
			count++;
		}
		else
		{		
		result[j] = source[i];
		result[j+1] = count + '0';
		count = 1;
		j += 2;
		}
//		puts(result); //TEST
	}
	
	puts(result);
	
	return;
}
