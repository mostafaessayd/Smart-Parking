#include <Arduino.h>
#line 1 "C:\\Users\\DZ Laptops\\Documents\\Arduino\\projet\\projet.ino"
#define trigPin 9
#define echoPin 10

#define redLed 6
#define greenLed 7

long duration;
int distance;

#line 10 "C:\\Users\\DZ Laptops\\Documents\\Arduino\\projet\\projet.ino"
void setup();
#line 20 "C:\\Users\\DZ Laptops\\Documents\\Arduino\\projet\\projet.ino"
void loop();
#line 10 "C:\\Users\\DZ Laptops\\Documents\\Arduino\\projet\\projet.ino"
void setup() {
  pinMode(trigPin, OUTPUT);
  pinMode(echoPin, INPUT);

  pinMode(redLed, OUTPUT);
  pinMode(greenLed, OUTPUT);

  Serial.begin(9600);
}

void loop() {
  // إرسال إشارة
  digitalWrite(trigPin, LOW);
  delayMicroseconds(2);
  
  digitalWrite(trigPin, HIGH);
  delayMicroseconds(10);
  
  digitalWrite(trigPin, LOW);

  // استقبال الإشارة
  duration = pulseIn(echoPin, HIGH);

  // حساب المسافة
  distance = duration * 0.034 / 2;

  Serial.print("Distance: ");
  Serial.println(distance);

  // التحكم في LED
  if (distance < 10) {
    digitalWrite(redLed, LOW);   // مشغول
    digitalWrite(greenLed, HIGH);
  } else {
    digitalWrite(redLed, HIGH);
    digitalWrite(greenLed, LOW); // فارغ
  }

  delay(500);
} 

