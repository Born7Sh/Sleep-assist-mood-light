import speech_recognition as sr
import os
import pymysql
import config
import datetime
from mpyg321.mpyg321 import MPyg321Player
from gtts import gTTS

# 녹음을 3초동안 계속 반복하고 원하는 단어가 나오면 4초간 명령을 받아들임.

while True:
    os.system('arecord -d 3 -f cd -Dhw:1 test.wav')
    r = sr.Recognizer()

    with sr.AudioFile("test.wav")as source:
        audio_text = r.record(source)

    print(audio_text)
    try:
        text = r.recognize_google(audio_text, language = "ko-KR")
        print(text)
        if text == '라즈베리' or text == '라즈배리':
            tts = gTTS( text='부르셨어요?', lang='ko', slow=False )
            filename= 'ex_ko.mp3'
            tts.save(filename) 
            os.system('sudo mpg321 -q '+filename)   #sudo 권한이 없으면 소리재생 불가
        
            os.system('arecord -d 3 -f cd -Dhw:1 test.wav')
            r = sr.Recognizer()

            with sr.AudioFile("test.wav")as source:
                audio_text = r.record(source)
            text = r.recognize_google(audio_text, language = "ko-KR")
            print(text)

            if text == '안녕하세요':
                tts = gTTS( text='안녕하세요. 반가워요.', lang='ko', slow=False )
                filename= 'ex_ko.mp3'
                tts.save(filename) 
                os.system('sudo mpg321 -q '+filename)
            if text == '날씨' or text == '오늘 날씨 알려줘':
                # database = pymysql.connect(host=str(config.host),user=str(config.user),db=str(config.database),password=str(config.password), charset='utf8')
                # curs=database.cursor()
                # sql = '''SELECT * FROM current_weather ORDER BY id DESC LIMIT 11'''
                # curs.execute(sql)
                # result = cursor.fetchall()

                database.commit()
                database.close()    
                tts = gTTS( text='안녕하세요. 반가워요.', lang='ko', slow=False )
            if text.find('온도') != -1:
                database = pymysql.connect(host=str(config.host),user=str(config.user),db=str(config.database),password=str(config.password), charset='utf8')
                curs=database.cursor()
                sql = '''SELECT temperature, humidity, bright FROM hardware_control_status where email = %s limit 1'''
                curs.execute(sql,(config.email))
                result = curs.fetchall()
                voice_text = '현재 방 온도는 ' + str(int(result[0][0])) + '도, 습도는 ' +  str(result[0][1]) + ', 조도는 ' +  str(result[0][2]) + '입니다. '
                database.commit()
                database.close()    
                tts = gTTS( text=voice_text, lang='ko', slow=False )
                filename= 'temperature.mp3'
                tts.save(filename) 
                os.system('sudo mpg321 -q '+filename)
            if text == '불 켜줘':
                #기능
                tts = gTTS( text='네 알겠습니다.', lang='ko', slow=False )
                filename= 'ok.mp3'
                tts.save(filename) 
                os.system('sudo mpg321 -q '+filename)
            if text == '불 꺼줘':
                #기능
                tts = gTTS( text='네 알겠습니다.', lang='ko', slow=False )
                filename= 'ok.mp3'
                tts.save(filename) 
                os.system('sudo mpg321 -q '+filename)


    except:
        print('Canceled')        
 