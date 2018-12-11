#include <stdlib.h>
#include <stdio.h>
////MACROS BELOW
#define add(a,b) (a+b)
#define sub(a,b) (a-b)
#define mul(a,b) (a*b)
#define div(a,b) (a/b)
#define mod(a,b) (a%b)
////FUNCTIONS BELOW
void control() { //controls whole calc
    int kind; //represents kind of calculation
    int repeat=0; //if int repeat = 1, user will be asked to chose again
    int a,b;
	do { //ASKS FOR A CALCULATION TYPE
        repeat = 0; //sets repeat=0 preventing running loop forever
        printf("What kind of calculation would you like to perform?\n");
        printf("0.Add\n1.Subtract\n2.Multiply\n3.Divide\n4.Modulo\n\nConfirm by pressing ENTER\n");
        scanf("%d", &kind); //asks user to chose kind of calculation
        if (kind != 0 && kind != 1 && kind != 2 && kind != 3 && kind != 4) {//checks if user used a number (int) from 0 to 5
            printf("You have pressed wrong key, try again\n");
            repeat = 1;
        }
    } while (repeat == 1); 
    system("cls");
	printf("REMEMBER: Use integers only. Program does not calculate floats nor doubles in order to avoid code complication\n");
	printf("Please, type first number and confirm with ENTER\n\n");
	scanf("%d", &a);
	do{//PREVENTS DIVIDING BY ZERO
		repeat = 0;
		printf("Please, type second number and confirm with ENTER\n");
		scanf("%d", &b);
		if(b == 0){
			printf("You cannot divide by zero, try again.\n");
			repeat = 1;
		}
	}while(kind == 3 && repeat==1);	
	printf("\n\nRESULT=  ");
    switch (kind) //PRINTS RESULT
    {
        case 0:
            printf("%d",add(a,b));
            break;
        case 1:
            printf("%d",sub(a,b));
            break;
        case 2:
            printf("%d",mul(a,b));
            break;
        case 3:
            printf("%d",div(a,b));
            break;
        case 4:
            printf("%d",mod(a,b));
            break;
    }
}
int askClose(int *close) { //asks if user wants to close app
    *close = 0;
    char key;
    printf("\n===================================================================================\n");
    printf("\n\n            Press any key to continue or press ESC to close\n\n");
    printf("\n===================================================================================\n");
    key = getch();
    if (key == 27)*close = 1;
    //printf("key checked\n"); //test, check if askClose has finished
}
////PROGRAM BODY BELOW
int main(){
    SetConsoleTitleA("Console Calculator Using Preprocesor Macros"); //changes console title
    int close = 0; //keeps program running until: int close = 1
    do {//MAIN PROGRAM LOOP
        control();
        askClose(&close);
        system("cls");
    } while (close != 1);
    //printf("loop end\n"); //test, check if console is going to close
    system("pause");
    return 0;
}
