#include <stdio.h>
#include <stdlib.h>
#include <iso646.h>
#define CNTL_Z '\032'  //EOF for DOS txt files
#define DLAN 50

int main()
{
	char file[DLAN];
	char ch;
	FILE *wp;
	long counter, end;
	puts("Opening FILE...");
	
	if( (wp = fopen("D:file1.txt","rb")) == NULL ) //open as read only binary
	{
		puts("ERROR: Cannot open the file");
		exit(1);
	}
	
	fseek(wp, 0L, SEEK_END); //go till end
	end = ftell(wp);
	for( counter = 1L; counter <=end ; counter++ )
	{
	fseek(wp, -counter, SEEK_END); //go back
	ch = getc(wp);	
	
	if(ch = not CNTL_Z and ch == not '\r') putchar(ch);
	}
return 0;
}
