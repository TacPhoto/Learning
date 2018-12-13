//Program creating a simple single linked list. No functions, just a simple training
#include<stdio.h> 
#include<stdlib.h> 

struct list
{
	int data;
	struct list *next;
};


int main()
{
	//DEFINE LIST ELEMENTS
	struct list* head = NULL;
	struct list* second = NULL;
	struct list* third = NULL;
	struct list* tail = NULL;
	struct list* temp; //temporary element
	//CREATE 4 NODES 
	head = (struct list*)malloc(sizeof(struct list));
	second = (struct list*)malloc(sizeof(struct list));
	third = (struct list*)malloc(sizeof(struct list));
	tail = (struct list*)malloc(sizeof(struct list));
	//SET NODES VALUES
	head->data = 1; 
	head->next = second; 
	second->data = 2;
	second->next = third;
	third->data = 3;
	third->next = tail;
	tail->data = 4;
	tail->next = NULL;
	//PRINTING:
	temp = head;
	while (temp != NULL)
	{
		printf("%d", temp->data);
		temp = temp->next;
	}
	//DELETE SECOND ELEMENT
	temp = second->next;
	second->data = temp->data;
	second->next = temp->next;
	//PRINTING:
	printf("\n\n\n");
	temp = head;
	while (temp != NULL)
	{
		printf("%d", temp->data);
		temp = temp->next;
	}
	//END
	system("pause");
	return 0;
}
