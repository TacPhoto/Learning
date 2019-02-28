#include <stdio.h>
#include <iso646.h>
//using iso646 notation makes code so much clearer, oh what a blessing!
int main()
{
	if( 1 == 2 or 0 == 0) puts("first");
	if( 1==1 and 2 == 2) puts("second");
	if( 1 == not 0) puts("third");
return 0;
}
