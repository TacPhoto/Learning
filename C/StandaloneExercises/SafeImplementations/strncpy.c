/* based od Gynvael's stream ;) - thanks! 
prevents buffer overflow */
// #include <string.h> //needed to use strncpy

char string1[n]=""; //n-element string1

strncpy(string1, "some_text_to_put_into_string1", sizeof(string1)-1); //uses strncpy the safe way
string1[sizeof(string1) - 1 ] = '\0'; //makes sure string1 is not mising EOF
