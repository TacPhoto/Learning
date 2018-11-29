#include <Windows.h>
#include <stdio.h>

int main(){
	int x = GetSystemMetrics(SM_CXSCREEN); //check screen x dimensions
	int y = GetSystemMetrics(SM_CYSCREEN); //check screen y dimensions
	printf("The screen size is: %dx%d\n", x, y); 
	system("pause");
	return 0;
}
