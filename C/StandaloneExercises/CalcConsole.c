/////////////////////////////////
////Simple Console Calculator////
/////////////////////////////////
//reminder: ESC is ASCII 27
#include <stdio.h>
#include <Windows.h>

////calculating functions
double factCalc(double a) { //a true factorial calculating function
	int i; //counter
	double result;
	for (i = 1; i <= a; i++)
		a = a * i;
	return result;


}
void addCalc() {
	////user input section below
	system("cls"); //clean window
	double a, b, result;
	printf("Please, type first number and confirm with ENTER\n");
	scanf("%f", &a);
	printf("Please, type second number and confirm with ENTER\n");
	scanf("%f", &b);
	////calculating section below
	result = a + b;
	printf("\n\nResult= %f", result);
}
void subtractCalc() {
	////user input section below
	system("cls"); //clean window
	double a, b, result;
	printf("Please, type first number and confirm with ENTER\n");
	scanf("%f", &a);
	printf("Please, type second number and confirm with ENTER\n");
	scanf("%f", &b);
	////calculating section below
	result = a - b;
	printf("\n\nResult= %f", result);
}
void multiplyCalc() {
	////user input section below
	system("cls"); //clean window
	double a, b, result;
	printf("Please, type first number and confirm with ENTER\n");
	scanf("%f", &a);
	printf("Please, type second number and confirm with ENTER\n");
	scanf("%f", &b);
	////calculating section below
	result = a * b;
	printf("\n\nResult= %f", result);
}
void divideCalc() {
	////user input section below
	system("cls"); //clean window
	double a, b, result;
	printf("Please, type first number and confirm with ENTER\n");
	scanf("%f", &a);
	printf("Please, type second number and confirm with ENTER\n");
	scanf("%f", &b);
	////calculating section below
	result = a / b;
	printf("\n\nResult= %f", result);
}
void moduloCalc() {
	////user input section below
	system("cls"); //clean window
	int a, b, result;
	printf("Please, type first number and confirm with ENTER\n");
	scanf("%d", &a);
	printf("Please, type second number and confirm with ENTER\n");
	scanf("%d", &b);
	////calculating section below
	result = a % b;
	printf("\n\nResult= %d", result);
}
void fact() { //asks for a number, runs factCalc and displays factorial result on screen
	system("cls"); //clean window
	int a, result;
	printf("Please, type number and confirm with ENTER\n");
	scanf("%d", &a);
	result = factCalc(a); //calculates factorial
	printf("\n\nResult= %d", result);
}

////controling functions
void control() { //controls whole calc
	int kind; //represents kind of calculation
	int repeat; //if int repeat = 1, user will be asked to chose again
	do {
		repeat = 0; //sets repeat=0 preventing running loop forever
		printf("What kind of calculation would you like to perform?\n");
		printf("0.Add\n1.Subtract\n2.Multiply\n3.Divide\n4.Modulo\n5.Factorial\n\nConfirm by pressing ENTER\n");
		scanf("%d", &kind); //asks user to chose kind of calculation
		if (kind != 0 && kind != 1 && kind != 2 && kind != 3 && kind != 4 && kind != 5) {//checks if user used a number (int) from 0 to 5
			printf("You have pressed wrong key, try again\n");
			repeat = 1;

		}
	} while (repeat == 1);

	switch (kind) //runs a propper calc function according to user's choice
	{
	case 0:
		addCalc();
		break;
	case 1:
		subtractCalc();
		break;
	case 2:
		multiplyCalc();
		break;
	case 3:
		divideCalc();
		break;
	case 4:
		moduloCalc();
		break;
	case 5:
		fact();
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

////main function
int main() {
	SetConsoleTitleA("Console Calculator"); //changes console title
	int close = 0; //keeps program running until: int close = 1
	do {//main calc loop
		control();
		askClose(&close);
	} while (close != 1);
	//printf("loop end\n"); //test, check if console is going to close

	system("pause");
	return 0;
}
