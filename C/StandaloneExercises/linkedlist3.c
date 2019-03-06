#include <stdio.h>
#include <stdlib.h>
#include "list.h"

void PrintMovie(Id id);

int main(void)
{
	List movies;
	Id temp;
	InitList(&movies);
	
	if(FullList(&movies))
	{
		fprintf(stderr,"Memory Full\n");
		exit(1);
	}
	
	puts("Enter first movie title:");
	while(gets(temp.title) != NULL && temp.title != '\0') //input data to the movies list
	{
		puts("Enter rating <0-10>:");
		scanf("%d", &temp.rating);
		while(getchar() != '\n')
			continue;
		if(InsertEnd(temp, &movies) == false)
		{
			fprintf(stderr,"Memory Allocation Error\n");
			break;
		}
		if(FullList(&movies))
		{
			puts("The list is already FULL");
			break;
		}
		puts("Enter the next title:");		
	}
	
	if(EmptyList(&movies))
	{
		puts("No movies");
	}
	else
	{
	puts("The Movie List:");
	ApplyToAll(&movies, PrintMovie);	
	}
	
	return 0;
}

void PrintMovie(Id id)
{
	printf("Movie: %s   Rating: %d\n", id.title, id.rating);
}