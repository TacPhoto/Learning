#include <stdio.h>
#include <string.h> 
//simple file compressing program using RLE algorithm
//WORKS CORRECTLY, never outputs a phrase longer than original
void compress(char source[], char result[]);

int main()
{
	char buf[256]; //text buffer
	int i = 0; //iteration counter
	int length; //contains text length

	puts("Enter a line of text, avoid using non letters");
	fgets(buf, 256, stdin);
	length = strlen(buf);

	char * source = malloc(sizeof(char) * (length + 1)); //allocate memory fot phrase
	memset(source, 0, (length));
	strncpy(source, buf, length); //uses strncpy the safe way
	source[length] = '\0'; //makes sure there is end of a string

	char * result = malloc(sizeof(char) * (2 * length + 1)); //allocate memory fot phrase
	memset(result, 0, (2 * length));
	result[length] = '\n';


	if (length != 0 && length != 1)compress(source, result); //runs compression


	free(result);
	free(source);
	getchar(); //pause
	return 0;
}

void compress(char source[], char result[])
{
	int i = 0, j = 0;
	int count = 1;
	char num = 0;
	int length = strlen(source);

	for (i = 0; i <= (length); i++) //RLE compression
	{
		if (source[i] == source[i + 1])
		{
			count++;
		}
		else
		{
			result[j] = source[i];
			result[j + 1] = count + '0'; //tricky thing that treats count as a character ;)
			count = 1;
			j += 2;
		}
		num++;
	}

	length = num;
	
//	printf("num= %d\n", num);//TEST
	printf("-------------------\nFirst stage compression result: ");
	for (i = 0; i <= num; i++) putchar(result[i]); //print result
	puts("");
	

	int realLength = 0; //real length of a result without '1' characters
	for (i = 0; i < length; i++)//check length of a result not including '1'
	{
		if (result[i] != '1') realLength++;
	}
//	printf("length = %d\n", realLength); //TEST
	char * final = malloc(sizeof(char) * (realLength + 1)); //allocate memory fot phrase
	memset(final, 0, (realLength + 1));
	

	j = 0; //set additional iteration counter for the loop below to 0
	for (i = 0; i <= length; i++) //deletes '1' from the string
	{
		if (result[i] != '1')
		{
			final[j] = result[i];
			j++;
		}
	}
	final[realLength + 1] = '\n'; //make sure final string has EOF

	printf("-------------------\nSecond stage compression result: ");
	for (i = 0; i <= realLength; i++) putchar(final[i]); //print result

	free(final);
	return;
}
