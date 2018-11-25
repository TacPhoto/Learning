#include <stdio.h>
#include <stdlib.h>
#include <conio.h>

void bezw(int a){
    if(a<0){
        a=-a;
        printf("\nwynik=%d\n",a);
        return;
    }
}
void kwad(int a){
	a=a*a;
	printf("\nwynik=%d\n",a);
    return;
}


void  steruj(int a, int wyb){
    if(wyb==1) bezw(a);
    if(wyb==2) kwad(a);
}


int main() {
    int wyb=0;
    int a=0;
  printf("wybierz 1 aby uzyskac wartosc bezwzgledna liczby, 2 aby podniesc ja do kwadratu\n");
  do {
      scanf("%d",&wyb);
  }while(wyb!=1 && wyb!=2);
    printf("podaj liczbe na ktorej wykonana zostanie operacja \n");
    scanf("%d",&a);
    printf("\n");
    steruj(a,wyb);

    
 
    system("pause");
    return 0;
}
