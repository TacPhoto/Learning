#ifndef _LIST_H_
#define _LIST_H_

#include <stdbool.h> //for bool usage, avalaible since C99
///*important definitions*///
#define SIZE 45 //max movie title string length

struct movie
{
	char title[SIZE];
	int rating;
};

typedef struct movie Id;

typedef struct node
{
	Id id;
	struct node * next;
} Node;

typedef Node * List;

///*Function Prototypes*///
/*plist is a pointer to the list*/
void InitList(List * plist); //initiates an empty list

bool EmptyList(const List * plist); //true if the list is empty, false if not

bool FullList(const List * plist); //true if the list is full, false if not

unsigned int ListLength(const List * plist); //number of list's positions

bool InsertEnd(Id id, List * plist); //insert a new node at the end of the list, true if possible, false if not

void ApllyToAll(const List * plist, void (* pfun)(Id id) ); //run function specified by pfun on every one of the nodes

void ClearList(List * plist); //clear list, free the memory, plist = NULL

#endif
