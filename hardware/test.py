#-*- coding:utf-8 -*-

import speech_recognition as sr
import os
import pymysql
import config
from datetime import datetime
from mpyg321.mpyg321 import MPyg321Player
from gtts import gTTS


def weather_pty(pty):
    if pty == 0:
        weather = '맑은 날씨가'
    elif pty == 1:
        weather = '비가'
    elif pty == 2:
        weather = '눈이나 비가'
    elif pty == 3:
        weather = '눈이'
    elif pty == 4:
        weather = '소나기가'
    elif pty == 5:
        weather = '빗방울이 '
    elif pty == 6:
        weather = '빗방울이나 눈날림이'
    elif pty == 7:
        weather = '눈날림이'
    return weather

def google_tts(voice_text,filename):
    tts = gTTS( text=voice_text, lang='ko', slow=False )
    tts.save(filename) 
    os.system('sudo mpg321 -q '+filename)



# 녹음을 3초동안 계속 반복하고 원하는 단어가 나오면 4초간 명령을 받아들임.
while True:
    database = pymysql.connect(host=str(config.host),user=str(config.user),db=str(config.database),password=str(config.password), charset='utf8')
    curs=database.cursor()
    sql = '''SELECT end, alarm FROM sleep where email = %s ORDER BY start DESC LIMIT 1 '''
    curs.execute(sql,(config.email))
    result = curs.fetchall()

    if (result[0][0] != None) and (result[0][1] == 0):
        #기상 시 오늘의 날씨를 알려주는 기능
        sql = 'select * from todays_weather where date_time >= now() order by insert_time desc, id limit 12;'
        curs.execute(sql)
        result1 = curs.fetchall()
        text = '오늘은 비가 오지 않을 것으로 예상됩니다.'
        for i in range(0,len(result1)):
            if result1[i][4] != 0:     #맑음이 아니면
                text = '오늘은 비가 올 수 있으니 날씨를 확인하시기 바랍니다.'
                break

        # 캘린더에 있는 내용을 아침에 일어났을 때 알려주는 기능
        sql = '''SELECT title from calendar where email = %s and start between %s and %s '''
        text1 = str(datetime.today())
        text1 = text1[0:10] + " 00:00:00"
        text2 = text1[0:10] + " 23:59:59"
        curs.execute(sql,(config.email,text1,text2))
        result2 = curs.fetchall()

        calendar = ''
        if result2[0] != None:
            calendar = '오늘의 일정으로는 '
            for i in range(0, len(result2)):
                calendar = calendar + str(result2[i][0]) + ', '
            calendar = calendar + '있습니다.'


        voice_text = config.nickname + '님 좋은 아침입니다. ' + text + calendar + '오늘도 좋은 하루 보내세요.'
        tts = gTTS( text=voice_text, lang='ko', slow=False )
        filename= 'ex_wakeup.mp3'
        tts.save(filename) 
        os.system('sudo mpg321 -q '+filename)
        sql = '''update sleep set alarm = 1  where end = %s and email = %s'''
        curs.execute(sql,(str(result[0][0]),config.email))
        database.commit()
    
    database.close() 
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
            if text == '일기':
                tts = gTTS( text='일기장에 기록할 내용을 15초 이내로 말해주세요.', lang='ko', slow=False )
                filename= 'ex_diary.mp3'
                tts.save(filename) 
                os.system('sudo mpg321 -q '+filename)

                os.system('arecord -d 15 -f cd -Dhw:1 test.wav')
                r = sr.Recognizer()

                with sr.AudioFile("test.wav")as source:
                    audio_text = r.record(source)
                text = r.recognize_google(audio_text, language = "ko-KR")
                print(text)
                database = pymysql.connect(host=str(config.host),user=str(config.user),db=str(config.database),password=str(config.password), charset='utf8')
                curs=database.cursor()
                sql = '''insert into diary(description, email) values(%s, %s);'''
                curs.execute(sql,(text, config.email))
                database.commit()
                database.close()
                tts = gTTS( text='저장했습니다.', lang='ko', slow=False )
                filename= 'ex_save.mp3'
                tts.save(filename) 
                os.system('sudo mpg321 -q '+filename)

            if text == '안녕하세요':
                tts = gTTS( text='안녕하세요. 반가워요.', lang='ko', slow=False )
                filename= 'ex_ko.mp3'
                tts.save(filename) 
                os.system('sudo mpg321 -q '+filename)
            if text == '현재 날씨' or text == '현재 날씨 알려줘' or text == '현재날씨':
                database = pymysql.connect(host=str(config.host),user=str(config.user),db=str(config.database),password=str(config.password), charset='utf8')
                curs=database.cursor()
                sql = 'SELECT * FROM current_weather ORDER BY date_time DESC LIMIT 1;'
                curs.execute(sql)
                result = curs.fetchall()
                weather =  weather_pty(result[0][3])

                voice_text = '현재 날씨는 ' + weather + ' 계속되고 있고, 온도는 ' +  str(int(result[0][1])) + ', 습도는 ' +  str(result[0][2]) +'입니다.'
                print(voice_text)

                #database.commit()
                database.close()    

                google_tts(voice_text,'weather_now.mp3')
            if text == '오늘 날씨' or text == '오늘 날씨 알려줘' or text == '오늘날씨':
                database = pymysql.connect(host=str(config.host),user=str(config.user),db=str(config.database),password=str(config.password), charset='utf8')
                curs=database.cursor()
                sql = 'select * from todays_weather where date_time >= now() order by insert_time desc, id limit 10;'
                curs.execute(sql)
                result = curs.fetchall()

                weather_forecast = []
                voice_text = ''

                # 맑은 날씨가 아닌 값 찾기
                for i in range(0,len(result)):
                    if result[i][4] != 0:     #맑음이 아니면
                        weather_forecast.append(result[i][1])
                        weather_forecast.append(result[i][4])
                        break

                min = result[0][2]
                max = result[0][2]
                #일교차 계산
                for i in range(0,len(result)):
                    if min > result[i][2]:
                        min = result[i][2]
                    if max < result[i][2]:
                        max = result[i][2]

                if (min+10) <= max: #일교차가 크고
                    if weather_forecast == []: #맑은 날씨
                        voice_text = '오늘의 날씨는 맑겠습니다. 최저기온 ' + str(int(min)) + ' 최고기온' + str(int(max)) +'도가 되겠습니다. 일교차가 크니 감기 조심하시기 바랍니다.'
                    else:
                        time = str(weather_forecast[0])
                        weather_forecast[0] = time[-8:-6] + '시 부터'
                        voice_text = '오늘의 날씨는' +  weather_forecast[0] + weather_pty(weather_forecast[1]) + ' 예정되어 있습니다.' + '최저기온 ' + str(min) + ' 최고기온' + str(max) +'도가 되겠습니다. 일교차가 크니 감기 조심하시기 바랍니다.'
                else:
                    if weather_forecast == []: #맑은 날씨
                        voice_text = '오늘의 날씨는 맑겠습니다. 최저기온 ' + str(int(min)) + ' 최고기온' + str(int(max)) +'도가 되겠습니다.'
                    else:
                        time = str(weather_forecast[0])
                        weather_forecast[0] = time[-8:-6] + '시 부터'
                        voice_text = '오늘의 날씨는' +  weather_forecast[0] + weather_pty(weather_forecast[1]) + ' 예정되어 있습니다.' + ' 최저기온 ' + str(min) + ' 최고기온' + str(max) +'도가 되겠습니다.'

                print(voice_text)
                google_tts(voice_text,'todays_weather.mp3')
                database.close() 

            if text.find('온도') != -1:
                database = pymysql.connect(host=str(config.host),user=str(config.user),db=str(config.database),password=str(config.password), charset='utf8')
                curs=database.cursor()
                sql = '''SELECT temperature, humidity, bright FROM hardware_control_status where email = %s limit 1'''
                curs.execute(sql,(config.email))
                result = curs.fetchall()
                voice_text = '현재 방 온도는 ' + str(int(result[0][0])) + '도, 습도는 ' +  str(result[0][1]) + ', 조도는 ' +  str(result[0][2]) + '입니다. '
                #database.commit()
                database.close()    
                google_tts(voice_text,'temperature.mp3')

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
 

