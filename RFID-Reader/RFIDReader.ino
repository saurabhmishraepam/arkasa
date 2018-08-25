/**
 * By Sana Parveen created on 25/8/2018
 * 
 * The following code reads and displays the RFID TAG using an RFID Reader Module EM 18
 */
int count = 0;
char input[12] = {0};  
void setup()
{
  Serial.begin(9600);
}
void loop()
{
  //reinitialize the values

  for(count=0; count<12; count++) 
  {
      input[count]= 'F';
  }
  count = 0; 
  if(Serial.available())
  {
    while(Serial.available() && count < 12) 
    {
      char ch = Serial.read();
      input[count] = ch;
      count++;
      delay(5);
    }
    if(count == 12)
    {
      Serial.print(input);
    }
    
  }
}
