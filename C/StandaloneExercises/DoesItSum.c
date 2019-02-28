#include <stdio.h>
#include <stdbool.h>
#define SIZE 6 //remember to set this number to actual arr size
//Given a list of numbers and a number k, print whether any two numbers from the list add up to k.
bool test(int *arr, int k);

int main()
{
	int arr[]={5, 10, 2, 15, 7, 23};
	int k = 17; //let's hardcode the k
//	int k = 18;	//for testing purposes
	
	printf("Does any given numbers sum to %d?\n", k);
	printf("%s", test(arr, k) ? "true" : "false");
	
	getchar();//pause
	return 0;
}

bool test(int *arr, int k)
{
	int i,j; //iteration counters
	for( i = 0 ; i < SIZE ; i++ )
	{
		for( j = 0 ; j < ( SIZE ); j++ )
		{
			if( arr[i] == ( k - arr[j] ) )	
			return true;
		}
	}
	return false;
}
