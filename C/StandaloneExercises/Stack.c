/*Special thanks to pekfos from cpp0x forum for helping me and finding major bugs :) */
#include <stdio.h>
#include <stdlib.h>
//STACK EXERCISE
void add( int * size, int stack[] ) { //adds another int to the stack
    //printf( "TEST: add() is running\n" );
    if( * size >= 4 ) {
        printf( "FULL" );
        system("pause");
    } else {
        printf( "Enter an integer to add to the stack: " );
        ++*size;
        scanf( "%d", & stack[ * size ] );
        printf( "\n" );
        //printf( "TEST: add() added a value\n" );
    }
}

void delete( int * size ) { //deletes last int from a stack
    if( * size >= 1 ) {
        printf( "The last integer has been deleted\n" );
        --*size;
    } else {
        printf( "STACK IS EMPTY\n" );
        system( "pause" );
       
    }
}

void showSize( int size ) {
    printf( "The stack containt %d elements\n", size );
    system( "pause" );
}

void isempty( size ) {
    printf( "The stack is not empty, number of elements: %d", size );
    system( "pause" );
}


int main() {
    int run = 1; //keeps program running if set to 1;
    int stack[ 5 ]; // stack[0] is counter, stack[1]-stack[5] contain stack data
    int control, i;
    int size = 0;
    do { //main program loop
        system( "cls" ); //clear the window
        printf( "THE STACK EXERCISE\n" );
        printf( "------------------\n" );
        printf( "-----CONTENT:-----\n" );
        printf( "------------------\n" );
       
        for( i = size; i >= 1; i-- ) { //prints the stack
            printf( "-%d-\n", stack[ i ] );
        }
       
        if( size == 0 ) printf( "EMPTY\n" ); //printf empty if stack is empty
       
        printf( "------------------\n" );
        printf( "------------------\n" );
       
        printf( "\n-------MENU-------\n1.Add element\n2.Delete last element\n3.Print number of elements\n" );
        printf( "4.Check if stack is empty\n5.Close app\n" );
       
        printf( "USER INPUT: " );
        scanf( "%d", & control );
        //printf("TEST: CONTROL SCANF WORKS");
        switch( control ) { //control switch
        case 1:
            add( & size, stack ); //printf( "TEST:1" );
            break;
        case 2:
            delete( & size ); //printf("TEST:2");
            break;
        case 3:
            showSize( size ); //printf("TEST:3");
            break;
        case 4:
            isempty( size ); //printf("TEST:4");
            break;
        case 5:
            run = 5;
            printf( "\n>>>CLOSING APPLICATION<<<\n" );
            sleep(1);
            break;
        }
    } while( run == 1 );
   
    return 0;
}
