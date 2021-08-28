from flask import Flask
import time
import serial

app = Flask(__name__)
#arduino = serial.Serial('/dev/ttyACM0',9600)

@app.route('/temp')
def temp():
    arduino = serial.Serial('/dev/ttyACM0',9600)
    var = '0'
    humidity = 0
    temperature = 0
    lux = 0.0

    string = []

    var = var.encode('utf-8')
    arduino.write(var)
    for i in range (0, 3):
        data = arduino.readline() 
        print(data)
        if i == 2:    #온습도 센서가 2초 정도의 딜레이 후에 정상적으로 반응하는데 3번째 정도면 값을 받을 수 있음.
            string = str(data)
            string = string.split()     
            string[0] = string[0][2:]   #쓰레기 값 제거
            string[2] = string[2][:-5]  #쓰레기 값 제거
            print(string)
            humidity = string[0]
            temperature = string[1]
            lux = string[2]
            print(temperature)
            print(humidity)    
            print(lux)  
            arduino.close()
    return 'ok'

@app.route('/red')
def red():
    arduino = serial.Serial('/dev/ttyACM0',9600)
    var = 'R'
    var = var.encode('utf-8')
    arduino.write(var)
    data = arduino.readline() 
    print(data)
    arduino.close()
    return 'ok'

@app.route('/blue')
def blue():
    arduino = serial.Serial('/dev/ttyACM0',9600)
    var = 'B'
    var = var.encode('utf-8')
    arduino.write(var)
    arduino.close()
    return 'ok'

@app.route('/green')
def green():
    arduino = serial.Serial('/dev/ttyACM0',9600)
    var = 'G'
    var = var.encode('utf-8')
    arduino.write(var)
    arduino.close()
    return 'ok'

@app.route('/pink')
def pink():
    arduino = serial.Serial('/dev/ttyACM0',9600)
    var = 'P'
    var = var.encode('utf-8')
    arduino.write(var)
    arduino.close()
    return 'ok'

@app.route('/white')
def white():
    arduino = serial.Serial('/dev/ttyACM0',9600)
    var = 'W'
    var = var.encode('utf-8')
    arduino.write(var)
    arduino.close()
    return 'ok'

@app.route('/yellow')
def yellow():
    arduino = serial.Serial('/dev/ttyACM0',9600)
    var = 'Y'
    var = var.encode('utf-8')
    arduino.write(var)
    arduino.close()
    return 'ok'

@app.route('/sky')
def sky():
    arduino = serial.Serial('/dev/ttyACM0',9600)
    var = 'S'
    var = var.encode('utf-8')
    arduino.write(var)
    arduino.close()
    return 'ok'

@app.route('/rainbow')
def rainbow():
    arduino = serial.Serial('/dev/ttyACM0',9600)
    var = 'A'
    var = var.encode('utf-8')
    arduino.write(var)
    arduino.close()
    return 'ok'

@app.route('/black')
def black():
    arduino = serial.Serial('/dev/ttyACM0',9600)
    var = 'L'
    var = var.encode('utf-8')
    arduino.write(var)
    arduino.close()
    return 'ok'

@app.route('/poweron')
def poweron():
    arduino = serial.Serial('/dev/ttyACM0',9600)
    var = 'O'
    var = var.encode('utf-8')
    arduino.write(var)
    arduino.close()
    return 'ok'

@app.route('/poweroff')
def poweroff():
    arduino = serial.Serial('/dev/ttyACM0',9600)
    var = 'F'
    var = var.encode('utf-8')
    arduino.write(var)
    arduino.close()
    return 'ok'

@app.route('/brightup')
def brightup():
    arduino = serial.Serial('/dev/ttyACM0',9600)
    var = 'U'
    var = var.encode('utf-8')
    arduino.write(var)
    arduino.close()
    return 'ok'

@app.route('/brightdown')
def brightdown():
    arduino = serial.Serial('/dev/ttyACM0',9600)
    var = 'D'
    var = var.encode('utf-8')
    arduino.write(var)
    arduino.close()
    return 'ok'



app.run(host='0.0.0.0',port='80')
