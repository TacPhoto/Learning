//two red and yellow alternately blinking diodes
void setup() {
  pinMode(8, OUTPUT); //red led output
  pinMode(9, OUTPUT); //yellow red output
}
void loop() {
  digitalWrite(8, HIGH);   //red led on
  delay(400);                    
  digitalWrite(8, LOW);    //red led off
  delay(400);    
  digitalWrite(9, HIGH);   //yellow red on
  delay(400); 
  digitalWrite(9, LOW);    //yellow red off
}
