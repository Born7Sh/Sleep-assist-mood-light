#include <DHT.h>
#include <Wire.h>
#include <BH1750.h>
#define DHTPIN A1
#define DHTTYPE DHT11

DHT dht(DHTPIN, DHTTYPE);
BH1750 lightMeter;
void setup(){
  Serial.begin(9600);
  Wire.begin();
  lightMeter.begin();
}


int data,h,t;

void loop(){
  while(1){
    delay(2000);
    data = Serial.read();
    h = dht.readHumidity();
    t = dht.readTemperature();
    Serial.print(h);
    Serial.print(" ");
    Serial.print(t);
    Serial.print(" ");
    int cdsValue = analogRead(A4);
    int lux = lightMeter.readLightLevel();
    Serial.println(lux);

    if(data=='1'){ 
    }
  }
}
