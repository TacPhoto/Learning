#include <stdio.h>
#include <stdlib.h> 
////*****OPERATING ON NEGATIVE SIGNED BINARIES TO BE IMPLEMENTED!!!*****////
int main()
{
	int decNum = 0, i = 0, result;
	int binNum[16] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,}; //16 bit array for the binary value
	printf("Input decimal number you would like to convert to binary (16 bit))?\n");
	scanf("%d", &decNum); //ask user for the decimal number
	
	result = decNum;
    while (result > 0) { //convert decimal to binary, treats negative number as positive
        binNum[i] = result % 2; 
        result = result / 2; 
        i++; 
        if( i == 16) break;
    } 
    
	printf("Decimal %d is binary ", decNum);
	for( i = 15 ; i >= 0; i-- ) printf("%d", binNum[i]); //list the binary
	printf("\n\n");
  
	system("pause");
	return 0;
}
