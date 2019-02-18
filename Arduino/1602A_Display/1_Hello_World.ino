#include <LiquidCrystal.h>

LiquidCrystal lcd(12, 11, 5, 4, 3, 2); //set the lcd display pins

void setup() {a to
  lcd.begin(16, 2); //initialize the display, set dimensions to 16x2
  lcd.print("Seems to"); //print some text
  lcd.setCursor(0, 1); //set cursor to second row
  lcd.print("work!"); //print some text again but this time in another row
}

void loop() { //contains blinking cursor :)
 delay(300);
 lcd.noCursor(); //hide cursor
 delay(300);
 lcd.cursor();  //show cursor
}
