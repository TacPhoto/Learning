#define _CRT_SECURE_NO_WARNINGS //prevents 'use scanf_s warning' in Visual Studio
#include <stdio.h> 
#include <stdlib.h> 
#include <string.h>
////////
///CODE STILL NOT WORKING PROPERLY
///CORRECTING IN PROGRESS
////////
int search( char arr[], int fileLen, char phrase[] ) //count all occurances of the phrase in arr
{
    int i, matchCount = 0; //iteration counter, matching characters counter
    int count = 0; //occarances counter
    for( i = 0; i <( fileLen - strlen( phrase ) + 1 ); i++ )
    {
       
        if( arr[ i ] == phrase[ matchCount ] )
        {
            matchCount++;
            if( matchCount ==( strlen( phrase ) ) )
            {
                count++;
                matchCount = 0; //reset matching characters number
            }
        }
        else
        {
            matchCount = 0;
        }
    }
    return count;
}
////////
int main() {
	////////VARIABLES (not including phrase)
	int count = 0;
	int i = 0;
	char buf[256]; //buffer for test input
	char arr[512]; //	char arr[512];
	int fileLen = 512; //array (arr) size
	int length; //lenght of a phrase
	////////OPEN FILE
	FILE *source;
	source = fopen("D:file1.txt", "r"); //open source
	while (fgets(arr, fileLen, source) != NULL); //read file.
	////////ASK USER FOR TEXT TO FIND
	printf("what phrase should i look for?\n\n");
	scanf("%s", buf);
	length = strlen(buf);
	printf("Length of the typed phrase: %d,TEST\n", length); //////TEST
	////////WRITE FROM BUFFER TO PHRASE
	char *phrase = malloc(sizeof(char) * (length+1)); //allocate memory fot phrase
	strncpy(phrase, buf, sizeof(phrase)-1); //uses strncpy the safe way
	phrase[sizeof(phrase) - 1 ] = '\0';
	printf("You have typed: %s,TEST\n", phrase); //////TEST
	////////SEARCHING FOR THE PHRASE
	if (length <= fileLen)
	{
		count = search(arr, fileLen, phrase);
		printf("I have found such phrase %d times - TEST\n", search(arr, fileLen, phrase)); //////TEST
		printf("I have found such phrase %d times.\n", count);
	}
	else {
		printf("The phrase you have entered is longer than number of characters in the source file. Phrase was found 0 times.\n");
	}
	////////CLOSE
	fclose(source);
	system("pause");
	return 0;
}

