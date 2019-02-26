#include <stdio.h>
#include <stdlib.h>
#include "list.h"

///*Lokal Function Prototypes*///

static void CopyToNode(Id id, Node * pnode);

///*Interface Functions*///
void InitList(List * plist)
{
	*plist = NULL;
}

bool EmptyList(const List * plist)
{
	if(*plist == NULL)
		return true;
	else
		return false;	
}

bool FullList(const List * plist)
{
	Node *ptr;
	bool full;
	ptr = (Node *) malloc(sizeof(Node));
	if(ptr == NULL)
		full = true;
	else
		full = false;
	free(ptr);
	return full;
}

unsigned int ListLength(const List * plist)
{
	unsigned int counter = 0;
		Node * p = *plist;
	while(p != NULL)
	{
		++counter;
		p = p->next;
	}
	return counter;
}

bool InsertEnd(Id id, List * plist)
{
	Node * pnew;
	Node * seek = *plist;
	pnew = (Node *) malloc(sizeof(Node));
	
	if(pnew == NULL)
		return false;
	CopyToNode(id, pnew);
	pnew->next = NULL;
	
	if(seek == NULL)
		*plist = pnew;
	else
	{
		while(seek->next != NULL)
			seek = seek->next;
		seek->next = pnew;
	}
	return true;
}

void ApllyToAll(const List * plist, void (* pfun)(Id id) )
{
	Node * ptr = *plist;
	while(ptr != NULL)	
	{
		(*pfun)(ptr->id); //runs function specified by *pfun
		ptr = ptr->next;
	}
}

void ClearList(List * plist)
{
	Node *ptr;
	while(*plist != NULL)
	{
		ptr = (*plist); //save node adress
		free(*plist); //free node
		*plist = ptr; //go to next
	}
}

///*Local Function Declarations*///

static void CopyToNode(Id id, Node * pnode)
{
	pnode->id = id;
}
