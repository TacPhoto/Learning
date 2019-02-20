#include <stdio.h>
#include <stdlib.h>
int main()
{
	int decNum = 0, i = 0, result, sign = 0;
	int binNum[16] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,};
	printf("Input decimal number you would like to convert to binary (signed 16 bit))?\n");
	scanf("%d", &decNum);
	
	if( decNum < 0) //make decNum absolute
	{
	decNum = - decNum;
	sign = 1; //sign = 1 means decNum has a negative value
	}
	
	result = decNum;
	
    while (result > 0) { //convert decNum to unsigned binary
        binNum[i] = result % 2; 
        result = result / 2; 
        i++; 
        if( i == 16) break;
    } 
//	for( i = 15 ; i >= 0; i-- ) printf("%d", binNum[i]); printf("\n\n"); //TEST
	if( sign == 1 ) //convert unsigned binary to signed binary if decNum had a negative value
	{
		for( i = 0 ; i<16 ; i++ ) //negate binNum bytes
		{
			switch(binNum[i])
			{
			case 0:
				binNum[i] = 1;
				break;	
			case 1:
				binNum[i] = 0;
				break;
			}	
		}
//	for( i = 15 ; i >= 0; i-- ) rintf("%d", binNum[i]); printf("\n\n"); //TEST
		for( i = 0 ; i < 16 ; i++ ) //add 1 byte
		{
			if( binNum[i] == 0)
			{
				binNum[i] = 1;
				break;
			}
			if( binNum[i] == 1)
			{
				binNum[i] = 0;
			}	
		}
	}
		
	printf("Decimal %d is binary ", decNum);
	for( i = 15 ; i >= 0; i-- ) printf("%d", binNum[i]);
	printf("\n\n");

	system("pause");
	return 0;
}
