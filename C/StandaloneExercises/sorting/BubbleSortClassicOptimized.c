#include <stdio.h>
//bubble sort, classic way using for j<n containing for i<n (see my comment in readme.md) OPTIMIZED by adding swap variable that is used to escape from the loop
int main()
{
	int arr[] = { 17, 10, 0, 33, 5, 99, 22, 104, 40, 3}; //array to sort
//	int arr[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
//	int arr[] = { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
	int n = 10; //number of arr elements
	int sorted = 1; //sorted counter (strarting from 1), (sorted if 'sorted == n')
	int i; //teration counter
	int temp; //temporary int
	int swap; //for optimization purposes
	
	printf("Unsorted: ");
	for( i = 0 ; i <n ; i++) printf("%d ", arr[i]); //prints array
	printf("\n");
	
	for( i = 0 ; i < (n - 1) ; i++ )//checks if sorting is needed
		{
				if( arr[i] < arr [i+1] ) sorted++;		
		}

if(sorted!=n)
{
	for( int j = 0; j < (n - 1) ; j++)
	{	
	swap = 0;
		for( i = 0 ; i < (n - 1) ; i++) //single sorting iteration
			{
					if( arr[i] > arr[i+1])
					{
						temp = arr[i];
						arr[i] = arr[i+1];
						arr[i+1] = temp;
						swap = 1;
					}
			}
	if (swap == 0) break;
	}
}	
	printf("Sorted:   ");
	for( i = 0 ; i <n ; i++) printf("%d ", arr[i]); //prints sorted array
	printf("\n");
	return 0;
}
