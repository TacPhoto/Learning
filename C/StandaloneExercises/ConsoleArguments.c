#include <stdio.h>
#define LINE puts("*************************")
//I was wondering what argc and argv are for

int main(int argc, char* argv[])
{
	int i; //iteration counter
	LINE;
	printf("argc = %d\n", argc);
	LINE;
	for( i = 0 ; i < argc ; i++) //print argc
	{
	printf("argv[%d] = %s\n", i, argv[i]);
	}
	
	return 0;	
}


/*
My observation: If you run the code directly without passing any arguments through
the console the only argument passed to the program's console will be
the executable file location

Another observation: It is a nice way to make a cmd style program which uses
arguments passed through console while running.
*/
