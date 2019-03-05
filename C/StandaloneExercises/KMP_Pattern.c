#include <stdio.h>
#include <string.h> 
//KMP pattern searching algorithm

void search(char* pat, char* txt);
void calcLPS(char *pattern, int M, int *lps);
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
	int i = 0, j = 0; //iteration counters
	int lps[M]; //holds longest prefix sufix
	
	calcLPS(pattern, M, lps);
	
	while( i < N )
	{
		if(pattern[j] == txt[i])
		{
			i++;
			j++;
		}
		if(j == M)
		{
		printf("Pattern found at index %d\n", i);
		j = lps[j-1];
		}
		else if( i < N && pattern[j] != txt[i])
		{
			if(j != 0) j = lps[j-1];
			else i++;
		}	
	}
}

void calcLPS(char *pattern, int M, int *lps)
{
	int len = 0;
	lps[0] = 0;
	int i = 1;
	
	while(i < M)
	{
		if(pattern[i] == pattern[len])
		{
			len++;
			lps[i] = len;
			i++;
		}
		else
		{
			if(len != 0)
			{
				len = lps[len - 1];
			}
			else
			{
				lps[i] = 0;
				i++;
			}
		}
	}
}
