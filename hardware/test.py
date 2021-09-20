import speech_recognition as sr
import os
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
            os.system('sudo mpg321 -q '+filename)
        
            os.system('arecord -d 4 -f cd -Dhw:1 test.wav')
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
    except:
        print('Canceled')        
 