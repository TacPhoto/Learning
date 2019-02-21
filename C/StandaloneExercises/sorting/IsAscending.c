#include <stdio.h>
#include <stdlib.h>
//returns sorted = 1; if arr[] is sorted ascending
//2 additional arr[] arrays in comments can be used to test results
int main()
{
	int arr[] = { 17, 10, 0, 33, 5, 99, 22, 104, 40, 3}; //array to sort, should return NOT ascending
//	int arr[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};	//should return ascending
//	int arr[] = { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1}; //should return NOT ascending
	int n = 10; //number of arr elements
	int sorted = 1; //suppose arr is already sorted
	
	for( int i = 0 ; i < n ; i++ )
	{
		if( arr[i] > arr[i+1] ) sorted = 0;
	}
	
	switch(sorted)
	{
		case 1:
			printf("arr is sorted ascending\n");
			break;
		case 0:
		printf("arr is NOT sorted ascending\n");
		break;
	}
	
	system("pause");
	return 0;
}
