from flask import Flask
import time
import serial
import pymysql
import config

app = Flask(__name__)
#arduino = serial.Serial('/dev/ttyACM0',9600)

@app.route('/temp')
def temp():
    arduino = serial.Serial('/dev/ttyACM0',9600)
    var = 'T'
    humidity = 0
    temperature = 0
    lux = 0.0

    string = []

    var = var.encode('utf-8')
    arduino.write(var)
    #for i in range (0, 3):
    data = arduino.readline() 
    print(data)
        #if i == 2:    #온습도 센서가 2초 정도의 딜레이 후에 정상적으로 반응하는데 3번째 정도면 값을 받을 수 있음.
    string = str(data)
    string = string.split()     
    string[0] = string[0][4:]   #쓰레기 값 제거
    string[2] = string[2][:-5]  #쓰레기 값 제거
    print(string)
    humidity = int(string[0])
    temperature = int(string[1])
    lux = int(string[2])

    database = pymysql.connect(host=str(config.host),user=str(config.user),db=str(config.database),password=str(config.password), charset='utf8')
    curs=database.cursor()

    email = str(config.email)

    sql = '''INSERT INTO hardware_control_status(email, bright, humidity, temperature) values (%s,%s,%s,%s)'''

    curs.execute(sql,(email, lux,humidity,temperature))
    database.commit()
    database.close()                                     
    print(temperature)
    print(humidity)    
    print(lux)  
    arduino.close()
    return 'ok'

@app.route('/light')
def light():
    arduino = serial.Serial('/dev/ttyACM0',9600)
    database = pymysql.connect(host=str(config.host),user=str(config.user),db=str(config.database),password=str(config.password), charset='utf8')
    curs=database.cursor()
    sql = '''select alarm_time from sleep where email = %s order by start desc limit 1'''
    curs.execute(sql,(str(config.email)))
    result = curs.fetchall()
    hour1 = str(result[0])
    min1 = int(hour1[14:16])
    hour1 = int(hour1[11:13])
    now = time.strftime('%H%M%S')
    hour2 = int(now[0:2])
    min2 = int(now[2:4])
    if hour2 + 1 >= hour1:
        if min2 >= min1:
            var = 'M'
            var = var.encode('utf-8')
            arduino.write(var)
            arduino.close()
            return 'ok'
    if hour2 + 2 == hour1:
        var = '0'
        var = var.encode('utf-8')
        arduino.write(var)
        arduino.close()
        return 'ok'

    arduino.close()
    return 'ok'

@app.route('/red')
def red():
    arduino = serial.Serial('/dev/ttyACM0',9600)
    var = 'R'
    var = var.encode('utf-8')
    arduino.write(var)
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

@app.route('/warmwhite')
def warmwhite():
    arduino = serial.Serial('/dev/ttyACM0',9600)
    var = 'E'
    var = var.encode('utf-8')
    arduino.write(var)
    arduino.close()
    return 'ok'

@app.route('/warmwhite')
def warmwhite():
    arduino = serial.Serial('/dev/ttyACM0',9600)
    var = 'E'
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

@app.route('/bright50')
def bright50():
    arduino = serial.Serial('/dev/ttyACM0',9600)
    var = '5'
    var = var.encode('utf-8')
    arduino.write(var)
    arduino.close()
    return 'ok'

@app.route('/bright100')
def bright100():
    arduino = serial.Serial('/dev/ttyACM0',9600)
    var = '0'
    var = var.encode('utf-8')
    arduino.write(var)
    arduino.close()
    return 'ok'

@app.route('/bright255')
def bright255():
    arduino = serial.Serial('/dev/ttyACM0',9600)
    var = 'M'
    var = var.encode('utf-8')
    arduino.write(var)
    arduino.close()
    return 'ok'  

app.run(host='0.0.0.0',port='80')
