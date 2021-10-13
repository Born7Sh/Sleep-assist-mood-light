#include <DHT.h>
#include <Wire.h>
#include <BH1750.h>
#include <Adafruit_NeoPixel.h>
#define DHTPIN A1
#define DHTTYPE DHT11

#define PIN 6

//네오픽셀을 사용하기 위해 객체 하나를 생성한다. 
//첫번째 인자값은 네오픽셀의 LED의 개수
//두번째 인자값은 네오픽셀이 연결된 아두이노의 핀번호
//세번째 인자값은 네오픽셀의 타입에 따라 바뀌는 flag

Adafruit_NeoPixel strip = Adafruit_NeoPixel(16, PIN, NEO_GRB + NEO_KHZ800);

DHT dht(DHTPIN, DHTTYPE);

BH1750 lightMeter;

void setup(){
  Serial.begin(9600);
  Wire.begin();
  lightMeter.begin();
  strip.begin(); //네오픽셀을 초기화하기 위해 모든LED를 off시킨다
  strip.show();
}


int data,h,t,bright;

void loop(){
  while(Serial.available()){
    delay(2000);
    
    data = Serial.read();
    Serial.print(data);
    if(data == 69){ //WarmWhite
      strip.setBrightness(50);
      colorWipe(strip.Color(255, 100, 0), 40);
      strip.show();
    }
    if(data == 82){ //RED
      colorWipe(strip.Color(255, 0, 0), 50);
      
    }
    if(data == 66){ //BLUE
      colorWipe(strip.Color(0, 0, 255), 50);
    }
    if(data == 71){ //GREEN
      colorWipe(strip.Color(0, 255, 0), 50);
    }
    if(data == 80){ //PINK
      colorWipe(strip.Color(255, 0, 255), 50);
    }
    if(data == 87){ //WHITE
      colorWipe(strip.Color(255, 255, 255), 50);
    }
    if(data == 89){ //YELLOW
      colorWipe(strip.Color(255, 255, 0), 50);
    }
    if(data == 83){ //SKY
      colorWipe(strip.Color(0, 255, 255), 50);
    }
    if(data == 65){ //RAINBOW
      rainbow(20);
    }
    if(data == 76){ //BLACK
      colorWipe(strip.Color(0, 0, 0), 50);
    }
    if(data == 79){ //POWER ON
      colorWipe(strip.Color(255, 255, 0), 50);
      strip.setBrightness(30);
    }
    if(data == 70){ //POWER OFF
      strip.clear();
      strip.show();
    }
    if(data == 77){ //Bright255
      strip.setBrightness(255);
      colorWipe(strip.Color(255, 100, 0), 40);
      strip.show();
    }
    if(data == 48){ //Bright100
      strip.setBrightness(100);
      colorWipe(strip.Color(255, 100, 0), 40);
      strip.show();
    }

    if(data == 84){
      h = dht.readHumidity();     //습도
      t = dht.readTemperature();  //온도
      Serial.print(h);
      Serial.print(" ");
      Serial.print(t);
      Serial.print(" ");
      int cdsValue = analogRead(A4);  
      int lux = lightMeter.readLightLevel();
      Serial.println(lux);        //조도
    }

  }
}

//모든 LED를 출력가능한 모든색으로 한번씩 보여주는 동작을 한번하는 함수
void rainbow(uint8_t wait) {
  uint16_t i, j;

  for(j=0; j<256; j++) {
    for(i=0; i<strip.numPixels(); i++) {
      strip.setPixelColor(i, Wheel((i+j) & 255));
    }
    strip.show();
    delay(wait);
  }
}

void colorWipe(uint32_t c, uint8_t wait) {
  for(uint16_t i=0; i<strip.numPixels(); i++) {
      strip.setPixelColor(i, c);
      strip.show();
      delay(wait);
  }
}

//NeoPixel에 달린 LED를 각각 다른색으로 시작하여 다양한색으로 5번 반복한다
void rainbowCycle(uint8_t wait) {
  uint16_t i, j;

  for(j=0; j<256*5; j++) { 
    for(i=0; i< strip.numPixels(); i++) {
      strip.setPixelColor(i, Wheel(((i * 256 / strip.numPixels()) + j) & 255));
    }
    strip.show();
    delay(wait);
  }
}

//255가지의 색을 나타내는 함수
uint32_t Wheel(byte WheelPos) {
  if(WheelPos < 85) {
   return strip.Color(WheelPos * 3, 255 - WheelPos * 3, 0);
  } else if(WheelPos < 170) {
   WheelPos -= 85;
   return strip.Color(255 - WheelPos * 3, 0, WheelPos * 3);
  } else {
   WheelPos -= 170;
   return strip.Color(0, WheelPos * 3, 255 - WheelPos * 3);
  }
}
