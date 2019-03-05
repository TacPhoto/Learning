#include <stdio.h>
#include <string.h> 
//naive pattern searching algorithm

void search(char* pat, char* txt);

int main()
{
	char source[] = "SOMETEXTAAABAACEDEAABABBAABAABTEXTHERE";
	char pattern[] = "ABA";
//	char final[] = "TEXT";
	
	search(pattern, source);
	
	return 0;	
}

void search(char* pattern, char* txt)
{
	int M = strlen(pattern); //pattern length
	int N = strlen(txt); //text length
//	int i; //iteration counters
	
	for(int i = 0; i <= (N - M) ; i++)
	{
		int j;
		for( j = 0; j < M ; j++)
			if(txt[i + j] != pattern[j]) break;
		
		if(j == M) printf("Pattern found at index %d\n", i);
	}
}
