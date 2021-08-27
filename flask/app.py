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
    var = var.encode('utf-8')
    arduino.write(var)
    for i in range (0, 4):
        data = arduino.readline() 
        print(data)
        if i == 3:    #온습도 센서가 2초 정도의 딜레이 후에 정상적으로 반응하는데 3번째 정도면 값을 받을 수 있음.
            list(str(data))
            data.split()
            humidity = int(data[0:2])
            temperature = int(data[2:])
    #data = str(temperature) #+ ' ' + str(humidity)
    print(temperature)
    print(humidity)    
    return 'ok'



app.run(host='0.0.0.0',port='80')
