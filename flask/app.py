from flask import Flask
import time
import serial

app = Flask(__name__)
arduino = serial.Serial('/dev/ttyACM0',9600)

@app.route('/temp')
def temp():
    var = '0'
    humidity = 0
    temperature = 0
    lux = 0.0

    string = []

    var = var.encode('utf-8')
    arduino.write(var)
    for i in range (0, 4):
        data = arduino.readline() 
        print(data)
        if i == 3:    #온습도 센서가 2초 정도의 딜레이 후에 정상적으로 반응하는데 3번째 정도면 값을 받을 수 있음.
            string = str(data)
            string = string.split()
            string[0] = string[0][2:]
            string[2] = string[2][:-5]
            print(string)
            humidity = string[0]
            temperature = string[1]
            lux = string[2]
    print(temperature)
    print(humidity)    
    print(lux)  
    return 'ok'



app.run(host='0.0.0.0',port='80')
