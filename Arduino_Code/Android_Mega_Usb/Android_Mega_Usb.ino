
const int ledPin = 13;      // the internal LED pin
byte brightness=0;
void setup()
{
  // initialize the serial communication:
  Serial.begin(9600);
  // initialize the ledPin as an output:
  pinMode(ledPin, OUTPUT);
}

void loop() {
  

  // check if data has been sent from the computer:
 if (Serial.available()) {
    // read the most recent byte (which will be from 0 to 255):
    brightness = Serial.read();
    Serial.println((brightness-48)*28);
    // set the brightness of the LED:
    analogWrite(ledPin, (brightness-48)*28);
    //delay(2);
  }
}

