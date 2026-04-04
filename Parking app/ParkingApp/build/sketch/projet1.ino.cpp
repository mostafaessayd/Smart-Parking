#include <Arduino.h>
#line 1 "C:\\Users\\DZ Laptops\\Documents\\Arduino\\projet1\\projet1.ino"
#define trigPin 9
#define echoPin 10

#define redLed 6
#define greenLed 7

long duration;
int distance;
int occupied = 0;

#line 11 "C:\\Users\\DZ Laptops\\Documents\\Arduino\\projet1\\projet1.ino"
void setup();
#line 21 "C:\\Users\\DZ Laptops\\Documents\\Arduino\\projet1\\projet1.ino"
void loop();
#line 11 "C:\\Users\\DZ Laptops\\Documents\\Arduino\\projet1\\projet1.ino"
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

  // تحديث حالة LED وعدد الأماكن المشغولة
  if (distance < 10) {
    digitalWrite(redLed, HIGH);   // مشغول
    digitalWrite(greenLed, LOW);
    occupied = 1;  // مكان واحد مشغول
  } else {
    digitalWrite(redLed, LOW);
    digitalWrite(greenLed, HIGH); // فارغ
    occupied = 0;  // مكان فارغ
  }

  // إرسال عدد الأماكن المشغولة للكمبيوتر
  Serial.print("Occupied: ");
  Serial.println(occupied);

  delay(500);
}
