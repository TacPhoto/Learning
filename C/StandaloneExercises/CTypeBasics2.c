#include <stdio.h>
#include <ctype.h>
//prints few rows and columns of a specified character, try and run it your self
void show(char c, int height, int width)
{
	int row, column; //counters
	
	for( row = 1 ; row <= height ; row ++)
	{
		for( column = 1 ; column <= width ; column ++)	putchar(c); //print line
		puts(""); //empty line
	}
}

int main(void)
{
	int ch, rows, columns;
	
	puts("Enter a character and two integers");
	
	while( (ch = getchar()) != EOF )
	{
		if(!isspace(ch))
		{
			if( scanf("%d %d", &rows, &columns) != 2 ) break; //scanf()==2 if you user did not type integers as asked
			show(ch, rows, columns); //prints character in rows and columns as specified by user
			puts("And again! (simulate EOF by CTRL Z to stop)");
		}
	}
	return 0;
}
