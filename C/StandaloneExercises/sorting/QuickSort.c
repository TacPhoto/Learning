#include <stdio.h>
#include <stdlib.h>
//quicksort
void quicksort(int *arr, int left, int right)
{
    int v=arr[(left+right)/2];
    int i,j,x;
    i=left;
    j=right;
    do
    {
        while(arr[i]<v) i++;
        while(arr[j]>v) j--;
        if(i<=j)
        {
            x=arr[i];
            arr[i]=arr[j];
            arr[j]=x;
            i++;
            j--;
        }
    }
    while(i<=j);
    if(j>left) quicksort(arr,left, j);
    if(i<right) quicksort(arr, i, right);
}

int main()
{
	int arr[] = { 17, 10, 0, 33, 5, 99, 22, 104, 40, 3}; //array to sort
//	int arr[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
//	int arr[] = { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
	int n = 10; //number of arr elements
	int sorted = 1; //sorted counter (strarting from 1), (sorted if 'sorted == n')
	int i, j; //teration counter
	int temp; //temporary int
	
	printf("Unsorted: ");
	for( i = 0 ; i <n ; i++) printf("%d ", arr[i]); //prints array
	printf("\n");
	
	for( i = 0 ; i < (n - 1) ; i++ )//checks if sorting is needed
		{
				if( arr[i] < arr [i+1] ) sorted++;		
		}
	
	if(sorted!=n) quicksort(arr, 0, n-1); //call quicksort

	sorted = 1; //reset counter
	
	printf("Sorted:   ");
	for( i = 0 ; i <n ; i++) printf("%d ", arr[i]); //prints sorted array
	printf("\n");
	
	system("pause");
	return 0;
}
