//counts from 9 to 0, then blinks alternately red and yellow diodes, everything looped
//I used a horizontally pinned  display, you may be a bit confused after looking into 'segCode' array
//want to see the result? visit https://youtu.be/A4yXwrYrLzA
int segPins[] = {2, 3, 4, 5, 6, 7, 8};

byte segCode[10][7] = { //information for displaying numbers 0-9
//  2  3  4  5  6  7  8 
  { 1, 1, 0, 1, 1, 1, 1},  // 0
  { 0, 0, 0, 0, 0, 1, 1},  // 1
  { 1, 1, 1, 0, 1, 1, 0},  // 2
  { 1, 0, 1, 0, 1, 1, 1},  // 3
  { 0, 0, 1, 1, 0, 1, 1},  // 4
  { 1, 0, 1, 1, 1, 0, 1},  // 5
  { 1, 1, 1, 1, 1, 0, 1},  // 6
  { 0, 0, 1, 0, 1, 1, 1},  // 7
  { 1, 1, 1, 1, 1, 1, 1},  // 8
  { 1, 0, 1, 1, 1, 1, 1},  // 9
};

void turnOffEdges() //turns all the edges off but thos not affect the display's dot
}
 for (int i=0; i < 7; i++)
  {
    digitalWrite(segPins[i], 0);
  }
}

void displayDigit(int digit) //displays a digit based on int i value
{
  for (int i=0; i < 7; i++)
  {
    digitalWrite(segPins[i], segCode[digit][i]);
  }
}

void diodesBlink() //alternately blinking diodes (red and tellow in my case)
{
 digitalWrite(10, HIGH);
 delay(300);
 digitalWrite(10, LOW);
 digitalWrite(11, HIGH);
 delay(300);
 digitalWrite(11, LOW);
}

void setup()
{
  for (int i=0; i < 7; i++)
  {
    pinMode(segPins[i], OUTPUT); //set edge pins
  }
  pinMode(9, OUTPUT); //set dot pin
  pinMode(10, OUTPUT); //set yellow diode
  pinMode(11, OUTPUT); //set red diode
 }
//////////////**********************//////////////
//////////////**********************//////////////
void loop()
{
   digitalWrite(9, HIGH); //turn on the dot
   for (int n = 9; n >= 0; n--)    //display digits 9 - 0
  {
     displayDigit(n);
     delay(300); //set any delay you wish
  }
  turnOffEdges(); //turns the display edges off
  for(int n = 0; n < 3 ; n++) //blinking diodes
  {
    diodesBlink();
  }
}
