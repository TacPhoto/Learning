#include <stdio.h>
#include <stdlib.h>
//////==========================================//////
//ASKS USER FOR INTEGERS, THEN FINDS THE HIGHEST ONE// 
//////==========================================//////
int main(){
	int buf[256]; //buffer
	int i,j ; //counters
	int max; //copy of the highest number of arr
	//INPUT NUMBERS
	printf("Insert an integer to array and press enter. Stop by entering 0 or repeating the process 256 times.\n");
	for( i = 0 ; i<256 ; i++){
		scanf("%d",&buf[i]);
		if( buf[i] == 0 )break;
	}
	//ALOCATING ARRAY
	int *arr;
	arr = (int *)malloc( i * sizeof(int));
	//REWRITE BUFFER TO ARR
	for( j = 0 ; j<i ; j++){
		arr[j] = buf[j];
	}
	//FIND MAX OF ARR
	max = arr[0];
	for( j = 0 ; j<i ; j++){
		if( max < arr[j]) max = arr[j];	
		printf("Number: %d Max: %d\n",arr[j], max); //test	
	}	
	//PRINT RESULT AND CLOSE
	printf("The highest number found in the array is: %d\n", max);
	free(arr);
	system("pause");
	return 0;
}
