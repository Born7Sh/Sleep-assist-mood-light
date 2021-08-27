#include <DHT.h>
#define DHTPIN A1
#define DHTTYPE DHT11

DHT dht(DHTPIN, DHTTYPE);

void setup(){
   Serial.begin(9600); 
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
    Serial.println(t);
    
    if(data=='1'){ 

    }
    

  }


}
