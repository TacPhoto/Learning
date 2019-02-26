#include <stdio.h>
#include <stdlib.h>
#define LINE puts("*****************************")
//Does not work yet, I do not know why, I only know there is a memory leak
struct Node //linked list node
{
	int data;
	struct Node *next;
};

void push(struct Node** head_ref, int new_data); //insert new node as a head
void append(struct Node** head_ref, int new_data); //append a new node to the end of the list
void printList(struct Node *node); //prints the list
void showMenu();
/*************************************************/
int main()
{
	char choice;
	int dataInput;
	struct Node* myList = NULL;  //initiate an empty list
	
	while(choice != '9')
	{
	system("cls");
	LINE;
	printList(myList);
	showMenu();
	
	scanf("%d", choice);
	if(choice == '9') break;

	LINE;
	puts("Enter number:");
	scanf("%d", &dataInput);
	puts(dataInput);//test
	Sleep(1000);//delete later
	LINE;
	
	switch(choice)
	{
		case 1:
			append(&myList, dataInput);
			break;
		case 2:
			push(&myList, dataInput);
			break;
	}
	}
	printList(myList);
	return 0;
}
/*************************************************/
void push(struct Node** head_ref, int new_data) 
{
	struct Node* new_node = (struct Node*) malloc(sizeof(struct Node)); //allocate a new node
	
	new_node->data = new_data; //assign data to node
	
	new_node->next = (*head_ref); //assign 'next' pointer to head
	
	(*head_ref) = new_node; //move head to the new node
}

void append(struct Node** head_ref, int new_data)
{
	struct Node* new_node = (struct Node*) malloc(sizeof(struct Node)); //allocate a new node
	
	struct Node *last = *head_ref; //it will be used later
	
	new_node->data = new_data; //assign data to node
	
	new_node->next = NULL; //assign NULL pointer to the new end of the list
	
	if(*head_ref == NULL) //makes sure there is only one nullptr assigned to actual end of the list
	{
		*head_ref = new_node;
		return;
	}
	
	while (last->next != NULL) //else traverse till the last node
        last = last->next; 
   
    last->next = new_node; //Change the next of last node
    return;     
}

void printList(struct Node *node)
{
	while (node != NULL) //iterate untill the end of the list by recognizing nullptr at the last node
  { 
     printf(" %d ", node->data); //print 'data' section from the list
     node = node->next; //set current node status to the next node
  } 
}

void showMenu()
{
	LINE;
	puts("1 to add a new node to the end of the list");
	puts("2 to add a new node to the beginning of the list");
	puts("9 exit");
	LINE;	
}

