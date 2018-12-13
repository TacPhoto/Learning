#include <stdio.h>
#include <conio.h>
#include <stdlib.h>

//MONTH = 30 DAYS, YEAR = 365 DAYS
typedef struct {
	char *type;
	int day;
	int month;
	int year;
}date;
int sub(date a,date b) {
	int ta = 365 * a.year + 30 * a.month + a.day;
	int tb = 365 * b.year + 30 * b.month + b.day;
	int buf;
	if (tb > ta) { //Switch dates if second one is smaller than the rist one
		buf = ta;
		ta = tb;
		tb = buf;
	}
	int diff;
	diff = ta - tb;
	return diff;
}

int main() {
	
	date date1;
	date date2;
	int buffer;
	int error;
	//STRUCT NO 1
	date1.type = "Gregorian";
	do {
		if (error == 1)printf("\nError, insert number between 0 and 30\n");
		error = 0;
		printf("\nInput Day\n");
		scanf("%d", &buffer);
		if (buffer > 30 && buffer < 0)error = 1;
		if (error == 0)date1.day = buffer;
	} while (error == 1);
	do {
		if (error == 1)printf("\nError, insert number between 1 and 12\n");
		error = 0;
		printf("\nInput Month\n");
		scanf("%d", &buffer);
		if (buffer > 12 && buffer < 1)error = 1;
		if (error == 0)date1.month = buffer;
	} while (error == 1);
	do {
		if (error == 1)printf("\nError, insert number lower than 2018\n");
		error = 0;
		printf("\nInput Year\n");
		scanf("%d", &buffer);
		if (buffer > 2018)error = 1;
		if (error == 0)date1.month = buffer;
	} while (error == 1);
	//STRUCT NO 2
	date2.type = "Gregorian";
	do {
		if (error == 1)printf("\nError, insert number between 0 and 30\n");
		error = 0;
		printf("\nInput Day\n");
		scanf("%d", &buffer);
		if (buffer > 30 && buffer < 0)error = 1;
		if (error == 0)date2.day = buffer;
	} while (error == 1);
	do {
		if (error == 1)printf("\nError, insert number between 1 and 12\n");
		error = 0;
		printf("\nInput Month\n");
		scanf("%d", &buffer);
		if (buffer > 12 && buffer < 1)error = 1;
		if (error == 0)date2.month = buffer;
	} while (error == 1);
	do {
		if (error == 1)printf("\nError, insert number lower than 2018\n");
		error = 0;
		printf("\nInput Year\n");
		scanf("%d", &buffer);
		if (buffer > 2018)error = 1;
		if (error == 0)date2.month = buffer;
	} while (error == 1);

	int diff = sub(date1, date2);
	printf("Days Passed Between Dates: %d \n",-(diff-365));

	system("pause");
	return 0;
}
