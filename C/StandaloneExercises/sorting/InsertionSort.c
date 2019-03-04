#include "pch.h"
#include <stdio.h>
#include <stdlib.h>


//insertion sort
int main()
{
	int arr[] = { 17, 10, 0, 33, 5, 99, 22, 104, 40, 3 }; //array to sort
 //	int arr[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
 //	int arr[] = { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
	int n = 10; //number of arr elements

	int sorted = 1; //sorted counter (strarting from 1)
	int i, j; //teration counter
	int temp;

	printf("Unsorted: ");
	for (i = 0; i < n; i++) printf("%d ", arr[i]); //prints array
	printf("\n");

	for (i = 0; i < (n - 1); i++)//checks if sorting is needed
	{
		if (arr[i] < arr[i + 1]) sorted++;
	}

	if (sorted != n)
	{
		for (i = 1; i < n; i++)
		{
			temp = arr[i]; //make copy of value
			j = i - 1;

			while (j >= 0 && arr[j] > temp) //move arr elements greater than 'temp'
			{
				arr[j + 1] = arr[j];
				j = j - 1;
			}
			arr[j + 1] = temp; //insert value
		}
	}
	sorted = 1; //reset counter


	printf("Sorted:   ");
	for (i = 0; i < n; i++) printf("%d ", arr[i]); //prints sorted array
	printf("\n");
	system("pause");
	return 0;
}
