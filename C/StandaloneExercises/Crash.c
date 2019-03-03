#include <stdio.h>
#include <stdbool.h>
#define CRASH() do { ((void(*)())0)(); }while(false) 
  
int main() 
{ 
   CRASH(); 
   puts("still works"); //will not display
   return 0; 
} 

/* I've found the code on geeksforgeeks and I was curious is DevCpp would compile
it at if it would execute or not

at first it did not work due to my wrong compilator setings :P
then it compiled and returned 3221225477 error during execution
we needed stdbool.h for the use of 'false' ad a  do...while condition */
