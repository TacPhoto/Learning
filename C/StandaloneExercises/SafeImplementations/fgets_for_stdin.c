/* prevents buffer overflow 
this implementation should not be used to read from files. fgets will treat end of file the same way it would treat reading file error
feof() or ferror() are necessary to write secure implementation of fgets reading from file
*/
// #include <stdio.h> //needed to use fgets

char string[n]=""; //n-element string1
string = fgets (napis, sizeof(string), stdin);
string[sizeof(string) - 1 ] = '\0'; //makes sure string is not mising EOF
