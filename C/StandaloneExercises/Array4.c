#include <stdio.h>
#include <stdlib.h>
//////=====================================================//////
//int t[n], find n if t[n]=x, write -1 if such n does not exist// 
//////=====================================================//////
int main(){
int t[9]={1000, 45, 7, 23, 5, 78, 65, 9, 0};
int x = 45;
int found = 0; //flag: 0 - x was not found //// 1 - x was found
for( int i = 0 ; i < 9; i++ ){ //seek for x int t[]
	if( x == t[i] ){
		printf("Found it! %d index is %d\n", x, i); //print if x was found
		found = 1; //set found flag
		break;
	}	
}
if( found == 0 ) printf("-1\n"); //check if x was found and print -1 if not
	system("pause");
	return 0;
}
